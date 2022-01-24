package principal.ingressos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.campos.CampoLimitadoSoDigito;
import componentes.FormDeSelecao;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import dao.DAO;
import principal.empresa.Empresa;
import principal.entidades.Entidade;
import principal.eventos.Evento;
import principal.eventos.SubEvento;
import principal.ingressos.Ingresso;
import principal.lotes.Lote;
import principal.relatorios.BaseIngresso;
import principal.relatorios.FabricaDeRelatorios;
import principal.relatorios.ItemIngresso;



public class FormGerarIngressos extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoLimitado tf_evento;
private JComboBox<String> cb_subeventos;
private JComboBox<String> cb_lotes;

private CampoLimitadoSoDigito tf_quant_ingressos;
private CampoLimitadoSoDigito tf_quant_faltam;

private Evento evento;
	
private List<SubEvento> subeventos;

private List<Lote> lotes;




	

	public FormGerarIngressos() {
		
		super("Geração de Ingressos", 650, 400);
		
		this.lotes = new ArrayList<Lote>();
		
		this.subeventos = new ArrayList<SubEvento>();
		
		adicionarComponentes();
	}
	

	
	
	
	
	public void adicionarComponentes(){
	
		GridBagConstraints cons = new GridBagConstraints();  
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 2, 2);
			
		JPanel p1 = new JPanel(new GridBagLayout());
		this.add(p1, cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Evento:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_evento = new CampoLimitado(200), cons);
		this.tf_evento.setEditable(false);
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Dia de Evento:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.cb_subeventos = new JComboBox<String>(new String[]{}), cons);
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Lote:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.cb_lotes = new JComboBox<String>(new String[]{}), cons);
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>QTDe de Ingressos Restante:<font color=red>*</font></html>"), cons);
		
		p1.add(this.tf_quant_faltam = new CampoLimitadoSoDigito(4), cons);
		this.tf_quant_faltam.setEditable(false);
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>QTDe de Ingressos Total:<font color=red>*</font></html>"), cons);
		
		p1.add(this.tf_quant_ingressos = new CampoLimitadoSoDigito(4, "1"), cons);
		
		
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.anchor = GridBagConstraints.EAST;
		cons.ipadx = 25;
		JButton bt_gerar  = new JButton("Gerar Ingressos", new ImageIcon(getClass().getResource("/icons/gerar.png")));
		bt_gerar.setToolTipText("Gerar Ingressos.");
		this.add(bt_gerar, cons);
		
		
		bt_gerar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
				if(acaoPrincipal())	
				Mensagens.msgDeSucessoAoSalvar();
		}});
		
		
		
		this.tf_evento.addMouseListener( new MouseAdapter(){		
			@Override 
			public void mouseClicked(MouseEvent e) {
			
			if(!tf_evento.isEnabled())	
			return;
				
			cb_subeventos.requestFocusInWindow(); 	
			adicionarEvento();
		}});
		
		
	
			this.cb_subeventos.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					atualizaLotes();
				}
			});
			
			
			this.cb_lotes.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					setQuantIngressosRestante();
				}
			});
	}
	
	
	


	private void adicionarEvento(){
		
		Evento retorno = new Evento();	
			
		FormDeSelecao<Evento> selectionItemForm = 
								new FormDeSelecao<Evento>("Adicionar Evento", retorno, Evento.class, "evt.status>0");
		selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_evento() > 0){
									
			this.evento = new DAO<Evento>(Evento.class).get(retorno.getId_evento());

			this.tf_evento.setText(this.evento.getNome());
			
			this.subeventos.clear();
			
			this.cb_subeventos.removeAllItems();
			
			this.subeventos.addAll(new DAO<SubEvento>(SubEvento.class).get(null, 
																																	"svt.fk_evento="+evento.getId_evento(), 
																																	"svt.data DESC"));
			
			if(this.subeventos.size()>0){
				
				for(SubEvento aux: this.subeventos)
					this.cb_subeventos.addItem(Data.converteDataParaString(aux.getData()));
			}
		}
		
		atualizaLotes();
	}
	
	
	
	
	
	protected void atualizaLotes(){
	
		if(this.cb_subeventos.getSelectedIndex()>=0){
		
			this.lotes.clear();
			
			this.cb_lotes.removeAllItems();
			
			this.lotes.addAll(new DAO<Lote>(Lote.class).get(null, 
																											"lte.fk_subevento="+this.subeventos.get(this.cb_subeventos.getSelectedIndex()).getId_subevento()+
																											" and lte.data_limite_impressao>='"+Data.getDataAtualEUA()+"'", 
																													"lte.id_lote ASC"));
			
			if(this.lotes.size()>0){
				
				DAO<Entidade> entidade_dao = new DAO<Entidade>(Entidade.class);
				
				for(int i = 0; i<this.lotes.size(); i++){
					
					Lote aux = this.lotes.get(i);
					
					Entidade tipo = entidade_dao.get(aux.getFk_tipo());
	
					this.cb_lotes.addItem((i+1)+" "+(tipo!=null? tipo.getNome().toUpperCase():"")+" QTDE "+aux.getQuantidade()+" R$: "+Calculo.formataValor(aux.getPreco()));
				}
			}
		}
		
		setQuantIngressosRestante();
	}		
			
	
	
	


	@Override
	public boolean acaoPrincipal() {
		
		if(this.evento==null){
			
			Mensagens.msgDeErro("Selecione um evento.");
			return false;		
		}
		
		if(this.cb_subeventos.getSelectedIndex()<0){
			
			Mensagens.msgDeErro("O evento selecionado não possui dia definido.");
			return false;		
		}
		
		if(this.cb_lotes.getSelectedIndex()<0){
			
			Mensagens.msgDeErro("Selecione o lote.");
			return false;		
		}
		
		if(this.tf_quant_ingressos.getText().length()==0 || Integer.parseInt(this.tf_quant_ingressos.getText())<=0){
			
			Mensagens.msgDeErro("Informe a quantidade de ingressos.");
			this.tf_quant_ingressos.ativaModoDeErro();
			return false;		
		}
	
		Lote lote=  this.lotes.get(this.cb_lotes.getSelectedIndex());
		
		int quant=  Integer.parseInt(this.tf_quant_ingressos.getText());
		int quant_ingressos = new DAO<Ingresso>(Ingresso.class).getCont(null, "igs.fk_lote="+lote.getId_lote(), null);
		
		quant_ingressos = lote.getQuantidade() - quant_ingressos;
		
		if(quant > quant_ingressos){
			
			Mensagens.msgDeErro("Somente é possível gerar mais "+quant_ingressos+" para este lote.");
			this.tf_quant_ingressos.ativaModoDeErro();
			return false;		
		}
		
		DAO<Ingresso> ingresso_dao = new DAO<Ingresso>(Ingresso.class);
		DAO<Entidade> entidade_dao = new DAO<Entidade>(Entidade.class);
		
		//BaseIngresso base = new BaseIngresso();
		//base.setLista(new ArrayList<ItemIngresso>());
		
		List<ItemIngresso> lista = new ArrayList<ItemIngresso>();
		
		Random random = new Random();
		
		Empresa empresa = new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, null);
		
		SubEvento subevento = this.subeventos.get(this.cb_subeventos.getSelectedIndex());
		
		for(int i= 0 ; i< quant;i++){
			
			Ingresso ingresso = new Ingresso();
			
			ingresso.setFk_lote(lote.getId_lote());
			ingresso.setCodigo(	Comuns.addPaddingAEsquerda(random.nextInt(999)+"", 3, "0")+
												Comuns.addPaddingAEsquerda(random.nextInt(999)+"", 3, "0")+
												Comuns.addPaddingAEsquerda(random.nextInt(999)+"", 3, "0")+
												Comuns.addPaddingAEsquerda(random.nextInt(999)+"", 3, "0"));
			
			ingresso_dao.novo(ingresso);
			
			ItemIngresso item = new ItemIngresso();
			//base.getLista().add(item);
			lista.add(item);
			
			
			item.setCodigo(ingresso.getCodigo());
			item.setLogo_evento(Comuns.temConteudo(evento.getLogo()) && new File(evento.getLogo()).exists()?evento.getLogo():getClass().getResource("/icons/evento_padrao.png").toString());
			item.setCidade(evento.getCidade()+"/"+evento.getUf());
			item.setLocal(evento.getNome_local());
			item.setNome_evento(evento.getNome());
				
			item.setData(Data.converteDataParaString(subevento.getData()));
			item.setHora(Comuns.addPaddingAEsquerda(String.valueOf(subevento.getHora_inicio()), 2, "0")+":"+Comuns.addPaddingAEsquerda(String.valueOf(subevento.getMin_inicio()), 2, "0"));
			
			if(empresa!=null){
				
				item.setEmpresa(empresa.getNome_razao());
				item.setCnpj(empresa.getCpf_cnpj());
				item.setFones(empresa.getTel_1()+
												(Comuns.temConteudo(empresa.getTel_1()) && Comuns.temConteudo(empresa.getTel_2())?"/":"")+
													empresa.getTel_2());
				item.setSite_email(empresa.getEmail()+
													(Comuns.temConteudo(empresa.getEmail()) && Comuns.temConteudo(empresa.getSite())?"/":"")+
														empresa.getSite());
				item.setLogo_empresa(Comuns.temConteudo(empresa.getLogo()) && new File(empresa.getLogo()).exists()?empresa.getLogo():null);
			}	
			
			item.setLote(lote.getCodigo());
			
			if(lote.getTipo_preco()==1)
				item.setTipo_preco("INTEIRA");
			else if(lote.getTipo_preco()==2)
				item.setTipo_preco("MEIA ENTRADA");
			else if(lote.getTipo_preco()==3)
				item.setTipo_preco("CORTESIA");
			
			item.setPreco("R$: "+Calculo.formataValor(lote.getPreco()));
			
			item.setUrl_qrcode(ingresso.getCodigo());
			
			if(lote.getFk_tipo()>0){
			
				Entidade entidade = entidade_dao.get(lote.getFk_tipo());
				item.setTipo_lote(entidade!=null?entidade.getNome():"");
			}
		}
		
		if(lista.size()>0){
			
			FabricaDeRelatorios fabrica=  new FabricaDeRelatorios();
			fabrica.relatorioDeIngresso(lote, lista);
		}
		
		atualizaLotes();
		
		return true;
	}
	
	
	
	
	
	private void setQuantIngressosRestante(){
		
		if(this.cb_lotes.getSelectedIndex()>=0){
		
			Lote lote=  this.lotes.get(this.cb_lotes.getSelectedIndex());
			
			int quant_ingressos = new DAO<Ingresso>(Ingresso.class).getCont(null, "igs.fk_lote="+lote.getId_lote(), null);
			
			this.tf_quant_faltam.setText((lote.getQuantidade() - quant_ingressos)+"");
		}
		else
			this.tf_quant_faltam.setText("");
	}
	
	
	
	
	
	
}
