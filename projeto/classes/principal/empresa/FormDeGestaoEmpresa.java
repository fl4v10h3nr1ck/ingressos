package principal.empresa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CamposDeTexto.campos.CampoCNPJ;
import CamposDeTexto.campos.CampoEmail;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.campos.CampoTEL;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import dao.DAO;



public class FormDeGestaoEmpresa extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoCNPJ tf_cnpj;

protected CampoTEL tf_tel_1;
protected CampoTEL tf_tel_2;
protected CampoLimitado tf_site;
protected CampoEmail tf_email;

protected CampoLimitado tf_logo;

protected Empresa empresa;
	




	public FormDeGestaoEmpresa() {
		
		super("Informações da Empresa", 750, 250);
		
		this.empresa = new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, null);
		
		this.adicionarComponentes();
	
		if(this.empresa != null ){
			
			tf_nome.setText(empresa.getNome_razao());
			tf_cnpj.setText(empresa.getCpf_cnpj());
			
			tf_tel_1.setText(empresa.getTel_1());
			tf_tel_2.setText(empresa.getTel_2());
			tf_site.setText(empresa.getSite());
			tf_email.setText(empresa.getEmail());
			
			this.tf_logo.setText(this.empresa.getLogo());
		}
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
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.75;
	p1.add(new JLabel("<html>Razão Social:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.25;
	p1.add(new JLabel("<html>CNPJ:<font color=red>*</font></html>"), cons);
		

	cons.gridwidth  = 1;	
	cons.insets = new Insets(0, 2, 2, 2);
	cons.weightx = 0.75;
	p1.add(this.tf_nome = new CampoLimitado(250), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.25;
	p1.add(this.tf_cnpj = new CampoCNPJ(), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.2;
	p2.add(new JLabel("<html>Tel:<font color=red>*</font></html>"), cons);
	p2.add(new JLabel("Tel (Op):"), cons);
	cons.weightx = 0.3;
	p2.add(new JLabel("Site:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel("E-mail:"), cons);
	
	cons.gridwidth  = 1;
	cons.weightx = 0.2;
	cons.insets = new Insets(0, 2, 2, 2);
	p2.add(this.tf_tel_1 = new CampoTEL(), cons);
	p2.add(this.tf_tel_2 = new CampoTEL(), cons);
	cons.weightx = 0.3;	
	p2.add(this.tf_site = new CampoLimitado(150), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(this.tf_email = new CampoEmail(150), cons);	
	
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new JLabel("LogoTipo do Evento:"), cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	p3.add(this.tf_logo = new CampoLimitado(350), cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar dados da empresa");
	this.add(bt_salvar, cons);
		
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

		this.tf_logo.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked( MouseEvent event ){
				    	
				String arquivo =  Comuns.selecionaArquivoDeImg();
				
				if(arquivo!=null)
					tf_logo.setText(arquivo);	
			}
		});
	
	}
	
	
	
	
	
	

	protected boolean validacao(){
	
		if(this.tf_nome.getText().length() == 0){
		
			Mensagens.msgDeErro("Informe a razão social da empresa.");
			this.tf_nome.ativaModoDeErro();
			return false;	
		}
		
		if(!this.tf_cnpj.validacao()){
				
			Mensagens.msgDeErro("Informe um número de CNPJ válido.");
			this.tf_cnpj.ativaModoDeErro();
			return false;	
		}
		
		if(!this.tf_tel_1.validacao()){
			
			Mensagens.msgDeErro("Informe um telefone 1 válido.");
			this.tf_tel_1.ativaModoDeErro();
			return false;	
		}
			
		if(this.tf_tel_2.getText().length() > 0){
			if(!this.tf_tel_2.validacao()){
			
				Mensagens.msgDeErro("Telefone 2 é opcional, caso queira informá-lo, informe um número válido.");
				this.tf_tel_2.ativaModoDeErro();
				return false;
			}
		}						
				
			
		if(this.tf_email.getText().length() > 0){
			if(!this.tf_email.validacao()){
			
				Mensagens.msgDeErro("Informe um endereço de e-mail válido.");
				this.tf_email.ativaModoDeErro();
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
	
	
	
	
	
	public  boolean acaoPrincipal(){
		
		if(!this.validacao())	
			return false;
		
		if(this.empresa==null)
			this.empresa = new Empresa();
		
		this.empresa.setNome_razao(this.tf_nome.getText());
		this.empresa.setCpf_cnpj(this.tf_cnpj.getText());
		this.empresa.setTel_1(this.tf_tel_1.getText());
		this.empresa.setTel_2(this.tf_tel_2.getText());
		this.empresa.setSite(this.tf_site.getText());
		this.empresa.setEmail(this.tf_email.getText());
		
		this.empresa.setLogo(Comuns.addNovaLogo(this.empresa.getLogo(), this.tf_logo.getText()));
		
		DAO<Empresa> dao =  new DAO<Empresa>(Empresa.class);
		
		boolean retorno;
		
		if(this.empresa.getId_empresa()<=0)
			retorno = dao.novo(this.empresa)>0?true:false;
		else
			retorno = dao.altera(this.empresa);
		
		return retorno;
	}
	
	
	
	
}
