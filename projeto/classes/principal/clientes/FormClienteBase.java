package principal.clientes;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CamposDeTexto.campos.CampoCNPJ;
import CamposDeTexto.campos.CampoCEP;
import CamposDeTexto.campos.CampoCPF;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.campos.CampoTEL;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;




public abstract class FormClienteBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome_razao;
public JComboBox<String> cb_tipo;
protected JTextField tf_cpf_cnpj;
protected CampoTEL tf_tel;

public CampoLimitado tf_logradouro;
public CampoLimitado tf_num;
public CampoLimitado tf_bairro;
public CampoLimitado tf_cidade;
public CampoCEP tf_cep;
public JComboBox<String> cb_uf;
public CampoLimitado tf_complemento;

protected Cliente cliente;
	
private JPanel p_cpf_cnpj;






	public FormClienteBase() {
		
		this(null);
	}

	
	
	
	
	
	public FormClienteBase(Cliente cliente) {
		
		super("Cadastro de Clientes", 750, 285);
	
		this.cliente = cliente;
	}
	

	
	
	
	
	public void adicionarComponentes(){
	
		GridBagConstraints cons = new GridBagConstraints();  
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 2, 2);
			
		JPanel p1 = new JPanel(new GridBagLayout());
		//p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.add(p1, cons);
	
		JPanel p2 = new JPanel(new GridBagLayout());
		this.add(p2, cons);
		
		JPanel p3 = new JPanel(new GridBagLayout());
		//p3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.add(p3, cons);
		
		this.p_cpf_cnpj = new JPanel(new GridBagLayout());
	
		
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = 1;	
		cons.weighty  = 0;
		cons.weightx = 0.4;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Nome/Razão Social:<font color=red>*</font></html>"), cons);
		cons.weightx = 0.2;
		p1.add(new JLabel("<html>Pessoa:<font color=red>*</font></html>"), cons);
		p1.add(new JLabel("<html>CPF/CNPJ:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("<html>TEL:<font color=red>*</font></html>"), cons);
		
	
		cons.gridwidth  = 1;
		cons.weightx = 0.4;
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_nome_razao = new CampoLimitado(200), cons);
		cons.weightx = 0.2;
		p1.add(this.cb_tipo = new JComboBox<String>(new String[]{"Física", "Jurídica"}), cons);
		p1.add(this.p_cpf_cnpj, cons);
		p1.add(this.tf_tel = new CampoTEL(), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		
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
	
		
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.anchor = GridBagConstraints.EAST;
		cons.ipadx = 25;
		JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
		bt_salvar.setToolTipText("Salvar cliente.");
		this.add(bt_salvar, cons);
			
		
			bt_salvar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
				if(acaoPrincipal()){	
						
				Mensagens.msgDeSucessoAoSalvar();
				dispose();
				}	
			}});
			
			
			
			this.cb_tipo.addItemListener(new ItemListener(){
	
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					setTipo();
				}
			});
			
			this.setTipo();
	}
	
	
	
	

	
	
	protected void setTipo(){
		
		if(this.p_cpf_cnpj !=null){

			this.p_cpf_cnpj.removeAll();
			
			GridBagConstraints cons = new GridBagConstraints();  
			
			cons.fill = GridBagConstraints.HORIZONTAL;
			cons.gridwidth  = GridBagConstraints.REMAINDER;	
			cons.weighty  = 0;
			cons.weightx = 1;
			cons.insets = new Insets(2, 2, 2, 2);
		
			if(this.cb_tipo.getSelectedIndex()==0)
				this.tf_cpf_cnpj = new CampoCPF();
			
			else
				this.tf_cpf_cnpj = new CampoCNPJ();
			
		
			this.p_cpf_cnpj.add(this.tf_cpf_cnpj, cons);
			
			this.p_cpf_cnpj.revalidate();
			this.p_cpf_cnpj.repaint();
			
			
			if(this.cliente!=null)
				this.tf_cpf_cnpj.setText(this.cliente.getCpf_cnpj());
		}
	}
	
	
	
	
	
	
	protected boolean validacao(){
	
		if(this.tf_nome_razao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome completo do cliente.");
		this.tf_nome_razao.ativaModoDeErro();
		return false;	
		}
		
		if(this.cb_tipo.getSelectedIndex()==0){
			if(!((CampoCPF)this.tf_cpf_cnpj).validacao()){
					
			Mensagens.msgDeErro("Informe um CPF válido para o cliente.");
			((CampoCPF)this.tf_cpf_cnpj).ativaModoDeErro();
			return false;	
			}
		}
		else{
			if(!((CampoCNPJ)this.tf_cpf_cnpj).validacao()){
				
				Mensagens.msgDeErro("Informe um CNPJ válido para o cliente.");
				((CampoCNPJ)this.tf_cpf_cnpj).ativaModoDeErro();
				return false;	
			}	
		}
		
		if(!this.tf_tel.validacao()){
				
		Mensagens.msgDeErro("Informe um telefone válido.");
		this.tf_tel.ativaModoDeErro();
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
			
		
		return true;
	}
	
	
	

	
}
