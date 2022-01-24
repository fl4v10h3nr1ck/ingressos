package principal.lotes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import CamposDeTexto.campos.CampoData;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.campos.CampoLimitadoSoDigito;
import CamposDeTexto.campos.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.janelas.Dialogo;
import componentes.tabelas.TabelaPadrao;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import dao.DAO;
import principal.entidades.Entidade;
import principal.eventos.Evento;
import principal.eventos.SubEvento;
import principal.ingressos.Ingresso;



public class FormDeGestaoDeLotes extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoLimitado tf_evento;
private JComboBox<String> cb_subeventos;

private JComboBox<String> cb_setor_lote;
private JComboBox<String> cb_tipo_preco;
private CampoData tf_limite_impressao;
private CampoMoeda tf_preco;
private CampoLimitadoSoDigito tf_quant_ingressos;

private Evento evento;
	
private List<SubEvento> subeventos;

private List<Lote> lotes;

private List<Entidade> tipos_de_lote;

private TabelaPadrao tab_lotes;	



	

	public FormDeGestaoDeLotes() {
		
		super("Gestão de Lotes", 850, 600);
		
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
	
		JPanel p2 = new JPanel(new GridBagLayout());
		this.add(p2, cons);
		
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty  = 1;
		cons.weightx  = 1;
		
		JPanel p3 = new JPanel(new GridBagLayout());
		this.add(p3, cons);

	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Evento:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("<html>Dia de Evento:<font color=red>*</font></html>"), cons);
		
		cons.gridwidth = 1;
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_evento = new CampoLimitado(200), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p1.add(this.cb_subeventos = new JComboBox<String>(new String[]{}), cons);
		
		this.tf_evento.setEditable(false);
			
		cons.insets = new Insets(15, 2, 15, 2);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p2.add(new JLabel("<html><b>Novo Lote</b></html>"), cons);
		
		cons.gridwidth  = 1;	
		cons.weightx = 0.16;
		cons.insets = new Insets(2, 2, 0, 2);
		p2.add(new JLabel("<html>Setor:<font color=red>*</font></html>"), cons);
		p2.add(new JLabel("<html>Tipo:<font color=red>*</font></html>"), cons);
		p2.add(new JLabel("<html>Imprimir até:<font color=red>*</font></html>"), cons);
		p2.add(new JLabel("<html>QTDe de Ingressos:<font color=red>*</font></html>"), cons);
		p2.add(new JLabel("<html>Preço (R$):<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p2.add(new JLabel(""), cons);
		
		
		cons.gridwidth  = 1;
		cons.insets = new Insets(0, 2, 2, 2);
		p2.add(this.cb_setor_lote = new JComboBox<String>(this.getTiposDeLote()), cons);
		p2.add(this.cb_tipo_preco = new JComboBox<String>(new String[]{"Inteira", "Meia Entrada", "Cortesia"}), cons);
		p2.add(this.tf_limite_impressao = new CampoData(), cons);
		p2.add(this.tf_quant_ingressos = new CampoLimitadoSoDigito(4), cons);
		p2.add(this.tf_preco = new CampoMoeda(6), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		JButton bt_adicionar_lote  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/novo.png")));
		bt_adicionar_lote.setToolTipText("Adicionar novo lote.");
		p2.add(bt_adicionar_lote, cons);
			
		
		this.tf_evento.addMouseListener( new MouseAdapter(){		
			@Override 
			public void mouseClicked(MouseEvent e) {
			
			if(!tf_evento.isEnabled())	
			return;
				
			tf_quant_ingressos.requestFocusInWindow(); 	
			adicionarEvento();
		}});
		
		
		this.tab_lotes = new TabelaPadrao(new String[]{	"<html><b>CÓDIGO</b></html>",
																										"<html><b>TIPO</b></html>",
																										"<html><b>IMPRESSÃO</b></html>",
																										 "<html><b>QTDe</b></html>",
																										 "<html><b>Preço (R$)</b></html>"},
																			new int[]{150, 150, 150, 150, 150},
																				new int[]{0, 1, 2, 3, 4});
			
			cons.fill = GridBagConstraints.BOTH;
			cons.weighty  = 1;
			cons.weightx  = 1;
			this.tab_lotes.setBackground(Color.white);
			p3.add(new JScrollPane(this.tab_lotes), cons);
			
			cons.fill = GridBagConstraints.NONE;
			cons.anchor = GridBagConstraints.WEST;
			cons.ipadx = 15;
			cons.weighty  = 0;
			cons.weightx  = 0;
			cons.gridwidth  = 1;
			JButton bt_remover_lote  = new JButton("Remover", new ImageIcon(getClass().getResource("/icons/remover.png")));
			bt_remover_lote.setToolTipText("Remover o lote selecionado.");
			p3.add(bt_remover_lote, cons);
			
			
			this.cb_subeventos.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					atualizaTabelaDeLotes();
				}
			});
			
			
			this.cb_tipo_preco.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					if(cb_tipo_preco.getSelectedIndex()==2){
						
						tf_preco.setText("0,00");
						tf_preco.setEnabled(false);
					}
					else
						tf_preco.setEnabled(true);
				}
			});
			
			
			
			
			
			bt_adicionar_lote.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
					    	
					adicionarLote();
			}});
			

			bt_remover_lote.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
					    	
					removerLote();
			}});
	}
	
	
	


	private void adicionarEvento(){
		
		Evento retorno = new Evento();	
			
		FormDeSelecao<Evento> selectionItemForm = 
								new FormDeSelecao<Evento>("Adicionar Evento", retorno, Evento.class, "evt.status>0");
		selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_evento() > 0){
									
			this.evento = retorno;

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
		
		atualizaTabelaDeLotes();
	}
	
	
	
	
	
	protected void atualizaTabelaDeLotes(){
		
		this.tab_lotes.getModelo().setNumRows(0);
		
		this.lotes.clear();
		
		String[] dados = new String[5];
	
		if(evento!=null && this.cb_subeventos.getSelectedIndex()>=0){
		
			DAO<Lote> lote_dao = new DAO<Lote>(Lote.class);
			
			this.lotes.addAll(lote_dao.get(null, "lte.fk_subevento="+subeventos.get(this.cb_subeventos.getSelectedIndex()).getId_subevento(), "lte.fk_tipo ASC"));	
		}
		
		DAO<Entidade> entidade_dao = new DAO<Entidade>(Entidade.class);
			
		for(Lote aux: this.lotes){
				
			Entidade tipo = entidade_dao.get(aux.getFk_tipo());
				
			dados[0] = aux.getCodigo();
			dados[1] = tipo!=null? tipo.getNome():"";
			dados[2] = Data.converteDataParaString(aux.getData_limite_impressao());
			dados[3] = ""+aux.getQuantidade();
			dados[4] = Calculo.formataValor(aux.getPreco());
				
			this.tab_lotes.getModelo().addRow(dados);	
		}
	}		
			
	
	
	
	
	private String[] getTiposDeLote(){
		
		tipos_de_lote = new DAO<Entidade>(Entidade.class).get(null, "etd.status>0 and etd.tipo="+Comuns.ETD_TIPO_INGRESSOS, null);
		
		if(tipos_de_lote.size()==0)
			return new String[]{"..."};
	
		String[] lista = new String[tipos_de_lote.size()+1];
		lista[0] = "...";
		
		for(int i=0; i<tipos_de_lote.size(); i++)
			lista[i+1] = tipos_de_lote.get(i).getNome();
			
		return lista;
	}
	
	
	
	

	
	private void adicionarLote(){
		
		if(this.evento==null){
		
			Mensagens.msgDeErro("Selecione um evento.");
			return;		
		}
		
		if(this.cb_subeventos.getSelectedIndex()<0){
			
			Mensagens.msgDeErro("O evento selecionado não possui dia definido.");
			return;		
		}
		
		if(this.cb_setor_lote.getSelectedIndex()<=0){
			
			Mensagens.msgDeErro("Selecione o tipo de lote.");
			return;		
		}
		
		if(!this.tf_limite_impressao.validacao()){
			
			Mensagens.msgDeErro("Informe uma data limite para impressão válida.");
			this.tf_limite_impressao.ativaModoDeErro();
			return;		
		}
		
		if(Data.comparaDatas(subeventos.get(this.cb_subeventos.getSelectedIndex()).getData(), 
																				Data.converteStringParaData(this.tf_limite_impressao.getText()))<0){
			
			Mensagens.msgDeErro("A data limite para impressão não pode ser maior que a data do evento.");
			this.tf_limite_impressao.ativaModoDeErro();
			return;		
		}
		
		if(this.tf_quant_ingressos.getText().length()==0 || Integer.parseInt(this.tf_quant_ingressos.getText())<=0){
			
			Mensagens.msgDeErro("Informe a quantidade de ingressos.");
			this.tf_quant_ingressos.ativaModoDeErro();
			return;		
		}
	
		if(this.cb_tipo_preco.getSelectedIndex()!=2){
			
			if(Calculo.stringZero(this.tf_preco.getText())){
				
				Mensagens.msgDeErro("Informe o preço dos ingressos deste lote.");
				this.tf_preco.ativaModoDeErro();
				return;		
			}
		}
		
		
		Lote lote = new Lote();
		lote.setFk_subevento(subeventos.get(this.cb_subeventos.getSelectedIndex()).getId_subevento()); 
		lote.setCodigo(Comuns.addPaddingAEsquerda(""+(1+(new Random().nextInt(999999999))), 6, "0"));
		lote.setFk_tipo(this.tipos_de_lote.get(this.cb_setor_lote.getSelectedIndex()-1).getId_entidade());
		lote.setData_limite_impressao(Data.converteStringParaData(this.tf_limite_impressao.getText())) ;
		lote.setPreco(Calculo.getNumero(this.tf_preco.getText()));
		lote.setQuantidade(Integer.parseInt(this.tf_quant_ingressos.getText()));
		lote.setTipo_preco(this.cb_tipo_preco.getSelectedIndex()+1);
		
		int id_lote = new DAO<Lote>(Lote.class).novo(lote);
		
		if(id_lote<=0){
			Mensagens.msgDeErroAoSalvar();
			return;
		}
		
		atualizaTabelaDeLotes();
		
		this.cb_setor_lote.setSelectedIndex(0);
		this.tf_limite_impressao.setText("");
		this.tf_quant_ingressos.setText("");
		this.tf_preco.setText("");
		this.cb_tipo_preco.setSelectedIndex(0);
	}
	
	
	
	

	private void removerLote(){
	
		int selecionado= this.tab_lotes.getSelectedRow();
		
		if(selecionado >= 0){
		
			Lote lote = this.lotes.get(selecionado);
			
			int quant_ingressos = new DAO<Ingresso>(Ingresso.class).getCont(null, "igs.fk_lote="+lote.getId_lote(), null);
			
			if(quant_ingressos>0){
				
				Mensagens.msgDeErro("O lote selecionado já possui ingressos gerados, não pode ser removido.");
				return;	
			}
			
			this.lotes.remove(selecionado);
	
			new DAO<Lote>(Lote.class).removeSemConfirmacao(lote.getId_lote());
			
			this.atualizaTabelaDeLotes();
		}
		else{
			
			Mensagens.msgDeErro("Selecione uma linha da tabela de lotes para remoção.");
			return;	
		}
	}
	
	


	@Override
	public boolean acaoPrincipal() {
		
		return false;
	}
	
	
	
	
	
}
