package principal.eventos;


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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import CamposDeTexto.campos.CampoCEP;
import CamposDeTexto.campos.CampoData;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.campos.CampoLimitadoSoDigito;
import componentes.FormDeSelecao;
import componentes.janelas.Dialogo;
import componentes.tabelas.TabelaPadrao;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import principal.clientes.Cliente;




public abstract class FormEventoBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoLimitado tf_cliente;

protected CampoLimitado tf_local;
public CampoLimitado tf_logradouro;
public CampoLimitado tf_num;
public CampoLimitado tf_bairro;
public CampoLimitado tf_cidade;
public CampoCEP tf_cep;
public JComboBox<String> cb_uf;
public CampoLimitado tf_complemento;

protected Evento evento;
	
protected Cliente cliente;


public JRadioButton tipo_dia_unico;
public JRadioButton tipo_varios_dias_um_ingresso;
public JRadioButton tipo_varios_dias_varios_ingressos;

public ButtonGroup rd_grupo;

private JPanel p_info;

public CampoData tf_data;
public CampoLimitadoSoDigito tf_hora_inicio;
public CampoLimitadoSoDigito tf_min_inicio;
public CampoLimitadoSoDigito tf_hora_fim;
public CampoLimitadoSoDigito tf_min_fim;
public JButton bt_adicionar_subevento;
public JButton bt_remover_subevento;

protected List<SubEvento> subeventos;

protected TabelaPadrao tab_subs_adicionados;	

protected CampoLimitado tf_logo;



	public FormEventoBase() {
		
		this(null);
	}

	
	
	
	
	
	public FormEventoBase(Evento cliente) {
		
		super("Cadastro de Eventos", 750, 600);
	
		this.evento = cliente;
		
		this.subeventos = new ArrayList<SubEvento>();
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
		
		JPanel p3 = new JPanel(new GridBagLayout());
		this.add(p3, cons);
		
		JPanel p4 = new JPanel(new GridBagLayout());
		this.add(p4, cons);
		
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty  = 1;
		cons.weightx  = 1;
		
		this.p_info = new JPanel(new GridBagLayout());
		this.add(p_info, cons);
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;

		JPanel p5 = new JPanel(new GridBagLayout());
		this.add(p5, cons);
		
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Nome do Evento:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_nome = new CampoLimitado(200), cons);
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Cliente:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_cliente = new CampoLimitado(200), cons);
		this.tf_cliente.setEditable(false);
		
		
		
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weightx = 1;
		p2.add(new JLabel("<html>Local:<font color=red>*</font></html>"), cons);
		
		p2.add(this.tf_local = new CampoLimitado(150), cons);
		
		cons.gridwidth  = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		cons.weightx = 0.5;
		p2.add(new JLabel("<html>Logradouro:<font color=red>*</font></html>"), cons);
		cons.weightx = 0.25;
		p2.add(new JLabel("<html>Número:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weightx = 0.25;
		p2.add(new JLabel("<html>Bairro:<font color=red>*</font></html>"), cons);
		
	
		cons.gridwidth  = 1;
		cons.insets = new Insets(0, 2, 0, 2);
		cons.weightx = 0.5;
		p2.add(this.tf_logradouro = new CampoLimitado(200), cons);
		cons.weightx = 0.25;
		p2.add(this.tf_num = new CampoLimitado(18), cons);
		cons.weightx = 0.25;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p2.add(this.tf_bairro = new CampoLimitado(45), cons);
		
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		cons.gridwidth  = 1;
		p3.add(new JLabel("Complemento:"), cons);
		p3.add(new JLabel("<html>Cidade:<font color=red>*</font></html>"), cons);
		p3.add(new JLabel("<html>UF:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p3.add(new JLabel("CEP:"), cons);
		
		cons.gridwidth  = 1;
		cons.insets = new Insets(0, 2, 2, 2);
		p3.add(this.tf_complemento = new CampoLimitado(200), cons);	
		p3.add(this.tf_cidade = new CampoLimitado(45), cons);
		p3.add(this.cb_uf = new JComboBox<String>(Comuns.ufs), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p3.add(this.tf_cep = new CampoCEP(), cons);
	

		cons.gridwidth  = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p4.add(new JLabel("<html>Tipo de Evento:<font color=red>*</font></html>"), cons);
		
		cons.gridwidth  = 1;
		cons.insets = new Insets(0, 25, 0, 5);
		cons.weightx = 1;
		p4.add(tipo_dia_unico = new JRadioButton("Um único dia.", true), cons);
		p4.add(tipo_varios_dias_um_ingresso = new JRadioButton("Vários dias com um ingresso para cada dia."), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p4.add(tipo_varios_dias_varios_ingressos = new JRadioButton("Vários dias com ingresso único."), cons);
		
		cons.insets = new Insets(2, 2, 0, 2);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p5.add(new JLabel("LogoTipo do Evento:"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p5.add(this.tf_logo = new CampoLimitado(350), cons);
		
		
		rd_grupo = new ButtonGroup();
		
		rd_grupo.add(tipo_dia_unico);
		rd_grupo.add(tipo_varios_dias_um_ingresso);
		rd_grupo.add(tipo_varios_dias_varios_ingressos);
		
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.anchor = GridBagConstraints.EAST;
		cons.ipadx = 25;
		JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
		bt_salvar.setToolTipText("Salvar cliente.");
		this.add(bt_salvar, cons);
			
		
		this.tf_cliente.addMouseListener( new MouseAdapter(){		
			@Override 
			public void mouseClicked(MouseEvent e) {
			
			if(!tf_cliente.isEnabled())	
			return;
				
			tf_logradouro.requestFocusInWindow(); 	
			adicionarCliente();
		}});
		
		
		
			bt_salvar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
				if(acaoPrincipal()){	
						
				Mensagens.msgDeSucessoAoSalvar();
				dispose();
				}	
			}});
			
			
			ItemListener tipo_evento_listener = new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					setTipo();
				}
			};
			
			this.tipo_dia_unico.addItemListener(tipo_evento_listener);
			this.tipo_varios_dias_um_ingresso.addItemListener(tipo_evento_listener);
			this.tipo_varios_dias_varios_ingressos.addItemListener(tipo_evento_listener);
			
			this.tab_subs_adicionados = new TabelaPadrao(
																									new String[]{"<html><b>DATA</b></html>",
																												 "<html><b>Horário</b></html>"},
																									new int[]{250, 500},
																									new int[]{0, 1});
			
			
			
			this.bt_adicionar_subevento  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/novo.png")));
			this.bt_adicionar_subevento.setToolTipText("Adicionar dia de evento.");
			
			this.bt_adicionar_subevento.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
					    	
					adicionarSubEvento();
			}});
			
			this.bt_remover_subevento  = new JButton("Remover", new ImageIcon(getClass().getResource("/icons/remover.png")));
			this.bt_remover_subevento.setToolTipText("Remover dia de evento selecionado.");
			
			this.bt_remover_subevento.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
					    	
					removerSubEvento();
			}});
			
			

			this.tf_logo.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked( MouseEvent event ){
					    	
					String arquivo =  Comuns.selecionaArquivoDeImg();
					
					if(arquivo!=null)
						tf_logo.setText(arquivo);	
				}
			});
			
			
			
			this.setTipo();
	}
	
	
	

	
	
	protected void setTipo(){
		
		this.p_info.removeAll();
		
		GridBagConstraints cons = new GridBagConstraints();  
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 2, 2);
			
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setBackground(Color.white); 
		this.p_info.add(p1, cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = 1;	
		cons.weighty  = 0;
		cons.weightx = 0.2;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Data:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		cons.weightx = 0.8;
		p1.add(new JLabel("<html>Horário:<font color=red>*</font></html>"), cons);
		
		cons.gridwidth  = 1;
		cons.weightx = 0.2;
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_data = new CampoData(), cons);
		cons.weightx = 0.05;
		p1.add(this.tf_hora_inicio = new CampoLimitadoSoDigito(2), cons);
		p1.add(new JLabel(":", SwingConstants.CENTER), cons);
		p1.add(this.tf_min_inicio = new CampoLimitadoSoDigito(2), cons);
		p1.add(new JLabel("à", SwingConstants.CENTER), cons);
		p1.add(this.tf_hora_fim = new CampoLimitadoSoDigito(2), cons);
		p1.add(new JLabel(":", SwingConstants.CENTER), cons);
		p1.add(this.tf_min_fim = new CampoLimitadoSoDigito(2), cons);

		
		if(this.tipo_dia_unico.isSelected()){
		
			cons.weightx = 0.45;
			cons.gridwidth  = GridBagConstraints.REMAINDER;
			p1.add(new JLabel(""), cons);	
			
			cons.fill = GridBagConstraints.BOTH;
			cons.weighty  = 1;
			cons.weightx  = 1;
			this.tab_subs_adicionados.setBackground(Color.white); 
			this.p_info.add(new JLabel(""), cons);
		}
		else{
		
			cons.weightx =  0.35;
			p1.add(new JLabel(""), cons);
			
			cons.gridwidth  = GridBagConstraints.REMAINDER;
			cons.weightx = 0.1;
			p1.add(this.bt_adicionar_subevento, cons);
			
			cons.fill = GridBagConstraints.BOTH;
			cons.weighty  = 1;
			cons.weightx  = 1;
			this.tab_subs_adicionados.setBackground(Color.white);
			this.p_info.add(new JScrollPane(this.tab_subs_adicionados), cons);
			
			cons.fill = GridBagConstraints.NONE;
			cons.anchor = GridBagConstraints.WEST;
			cons.ipadx = 15;
			cons.weighty  = 0;
			cons.weightx  = 0;
			cons.gridwidth  = 1;
			this.p_info.add(this.bt_remover_subevento, cons);
			
			
			this.atualizaTabelaDeSubEventos();
		}
		
		this.p_info.revalidate();
		this.p_info.repaint();
	}
	
	
	
	
	
	private void adicionarSubEvento(){
		
		if(!this.validacaoSubEvento())
			return;
		
		SubEvento aux = new SubEvento();
		aux.setData(Data.converteStringParaData(this.tf_data.getText()));
		aux.setHora_inicio(Integer.parseInt(this.tf_hora_inicio.getText()));
		aux.setMin_inicio(Integer.parseInt(this.tf_min_inicio.getText().length()>0?this.tf_min_inicio.getText():"0"));
		aux.setHora_fim(Integer.parseInt(this.tf_hora_fim.getText()));
		aux.setMin_fim(Integer.parseInt(this.tf_min_fim.getText().length()>0?this.tf_min_fim.getText():"0"));
		this.subeventos.add(aux);
		
		atualizaTabelaDeSubEventos();
	}
	
	
	

	
	
	protected void atualizaTabelaDeSubEventos(){
			
		this.tab_subs_adicionados.getModelo().setNumRows(0);
		
		String[] dados = new String[2];
		
			for(SubEvento aux: this.subeventos){
		
				dados[0] = Data.converteDataParaString(aux.getData());
				dados[1] = Comuns.addPaddingAEsquerda(aux.getHora_inicio()+"", 2, "0")+" : "+Comuns.addPaddingAEsquerda(aux.getMin_inicio()+"", 2, "0")+" às "+
								Comuns.addPaddingAEsquerda(aux.getHora_fim()+"", 2, "0")+" : "+Comuns.addPaddingAEsquerda(aux.getMin_fim()+"", 2, "0");
			
				this.tab_subs_adicionados.getModelo().addRow(dados);	
			}
		
		this.tf_data.setText("");
		this.tf_hora_inicio.setText("");
		this.tf_min_inicio.setText("");
		this.tf_hora_fim.setText("");
		this.tf_min_fim.setText("");
	}		
			

	
	
	

	private void adicionarCliente(){
		
		Cliente retorno = new Cliente();	
			
		FormDeSelecao<Cliente> selectionItemForm = 
								new FormDeSelecao<Cliente>("Adicionar Cliente", retorno, Cliente.class, "clt.status>0");
		selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_cliente() > 0){
									
			this.cliente = retorno;

			this.tf_cliente.setText("("+this.cliente.getCpf_cnpj()+") "+this.cliente.getNome_razao());
		}
	}
	
	
	
	
	

	private void removerSubEvento(){
	
		int selecionado= this.tab_subs_adicionados.getSelectedRow();
		
		if(selecionado >= 0){
		
			this.subeventos.remove(selecionado);
	
			this.atualizaTabelaDeSubEventos();
		}
		else{
			
			Mensagens.msgDeErro("Selecione uma linha da tabela de datas para remoção.");
			return;	
		}
	}
	
	
	
	
	
	
	
	protected boolean validacaoSubEvento(){
		
		if(!this.tf_data.validacao()){
			
			Mensagens.msgDeErro("Informe uma data válida.");
			this.tf_data.ativaModoDeErro();
			return false;		
		}
		
		if(!this.tipo_dia_unico.isSelected()){
		
			for(SubEvento aux: this.subeventos){
				
				if(Data.comparaDatas(aux.getData(), Data.converteStringParaData(this.tf_data.getText()))==0){
					
					Mensagens.msgDeErro("A data informada já foi adicionada.");
					this.tf_data.ativaModoDeErro();
					return false;		
				}
			}
		}
		
		if(this.tf_hora_inicio.getText().length()==0){
			
			Mensagens.msgDeErro("Informe a hora inicial.");
			this.tf_data.ativaModoDeErro();
			return false;		
		}
		
		if(Integer.parseInt(this.tf_hora_inicio.getText())>23 || 
				Integer.parseInt(this.tf_min_inicio.getText().length()>0?this.tf_min_inicio.getText():"0")>59){
			
			Mensagens.msgDeErro("Informe uma horário de início válido.");
			this.tf_data.ativaModoDeErro();
			return false;		
		}
		
		if(this.tf_hora_fim.getText().length()==0){
			
			Mensagens.msgDeErro("Informe a hora final.");
			this.tf_data.ativaModoDeErro();
			return false;		
		}
		
		if(Integer.parseInt(this.tf_hora_fim.getText())>23 || 
				Integer.parseInt(this.tf_min_fim.getText().length()>0?this.tf_min_fim.getText():"0")>59){
			
			Mensagens.msgDeErro("Informe uma horário de fim válido.");
			this.tf_data.ativaModoDeErro();
			return false;		
		}
		
		return true;		
	}
	
	
	
	
	
	protected boolean validacao(){
	
		if(this.tf_nome.getText().length() == 0){
		
			Mensagens.msgDeErro("Informe o nome do evento.");
			this.tf_nome.ativaModoDeErro();
			return false;	
		}
		
		if(this.cliente ==null || this.cliente.getId_cliente()<=0){
			
			Mensagens.msgDeErro("Selecione um cliente.");
			this.tf_cliente.ativaModoDeErro();
			return false;	
		}
		
		if(this.tf_local.getText().length() == 0){
			
			Mensagens.msgDeErro("Informe o nome do local do evento.");
			this.tf_local.ativaModoDeErro();
			return false;	
		}
			
		if(this.tf_logradouro.getText().length() == 0){
			
			Mensagens.msgDeErro("Informe o logradouro do cliente.");
			this.tf_logradouro.ativaModoDeErro();
			return false;	
		}
			

		if(this.tf_num.getText().length() == 0){
				
			Mensagens.msgDeErro("Informe o número residencial do cliente.");
			this.tf_num.ativaModoDeErro();
			return false;	
		}
			
		if(this.tf_bairro.getText().length() == 0){
				
			Mensagens.msgDeErro("Informe o bairro do cliente.");
			this.tf_bairro.ativaModoDeErro();
			return false;	
		}
			
		if(this.tf_cidade.getText().length() == 0){
				
			Mensagens.msgDeErro("Informe a cidade do cliente.");
			this.tf_cidade.ativaModoDeErro();
			return false;	
		}
			
		if(this.cb_uf.getSelectedIndex()<=0){
				
			Mensagens.msgDeErro("Selecione uma UF.");
			return false;	
		}
		
		if(this.tipo_dia_unico.isSelected())
			return validacaoSubEvento();
		else{
			
			if(this.subeventos.size()<=0){
				
				Mensagens.msgDeErro("Informe ao menos um dia de evento.");
				return false;	
			}
		}
		
		if(this.tf_logo.getText().length() > 0){
			if(!new File(this.tf_logo.getText()).exists()){
				
			Mensagens.msgDeErro("O arquivo selecionado como logotipo não existe.");
			this.tf_logo.ativaModoDeErro();
			return false;	
			}
		}
		
		return true;
	}
	
	
	
	
	
}
