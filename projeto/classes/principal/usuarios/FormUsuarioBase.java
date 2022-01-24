package principal.usuarios;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import CamposDeTexto.campos.CampoLimitado;
import componentes.janelas.Dialogo;
import comuns.Mensagens;




public abstract class FormUsuarioBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected JPasswordField tf_senha;
protected JPasswordField tf_senha_repete;



protected Usuario usuario;
	



	public FormUsuarioBase() {
		
		this(null);
	}

	
	
	
	
	
	public FormUsuarioBase(Usuario cliente) {
		
		super("Cadastro de Usuários", 750, 150);
	
		this.usuario = cliente;
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
		cons.gridwidth  = 1;	
		cons.weighty  = 0;
		cons.weightx = 0.4;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Nome:<font color=red>*</font></html>"), cons);
		cons.weightx = 0.2;
		p1.add(new JLabel("<html>Senha:<font color=red>*</font></html>"), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("<html>Repita a Senha:<font color=red>*</font></html>"), cons);
	
	
		cons.gridwidth  = 1;
		cons.weightx = 0.5;
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_nome = new CampoLimitado(200), cons);
		cons.weightx = 0.25;
		p1.add(this.tf_senha = new JPasswordField(), cons);
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		p1.add(this.tf_senha_repete = new JPasswordField(), cons);
		
		
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.anchor = GridBagConstraints.EAST;
		cons.ipadx = 25;
		JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
		bt_salvar.setToolTipText("Salvar Usuário.");
		this.add(bt_salvar, cons);
			
		
			bt_salvar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
				if(acaoPrincipal()){	
						
				Mensagens.msgDeSucessoAoSalvar();
				dispose();
				}	
			}});
	}
	
	
	
	
	
	protected boolean validacao(){
	
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome  de usuário.");
		this.tf_nome.ativaModoDeErro();
		return false;	
		}
		
		if(this.tf_nome.getText().contains(" ")){
			
			Mensagens.msgDeErro("Nome  de usuário não pode conter espaços em branco.");
			this.tf_nome.ativaModoDeErro();
			return false;	
		}

		if(this.usuario==null || (this.usuario!=null && this.tf_senha.getPassword().length > 0)){
			
			if(this.tf_senha.getPassword().length <6){
				
				Mensagens.msgDeErro("Informe uma senha de pelo menos 6 dígitos.");
				return false;	
			}
		
			if(this.tf_senha_repete.getPassword().length <6){
				
				Mensagens.msgDeErro("As senha informadas não são iguais.");
				return false;	
			}
			
			
			if(String.valueOf(this.tf_senha.getPassword()).compareTo(String.valueOf(this.tf_senha_repete.getPassword()))!=0){
				
				Mensagens.msgDeErro("As senha informadas não são iguais.");
				return false;	
			}
		}
		
		return true;
	}
	
	
	

	protected String cript(String string){
	
		MessageDigest algorithm= null;
		byte messageDigest[] = null;
			
			try { algorithm = MessageDigest.getInstance("SHA-256"); }catch (NoSuchAlgorithmException e) {return "";}
		
			try {messageDigest = algorithm.digest(string.getBytes("UTF-8")); } catch (UnsupportedEncodingException e)  {return "";}
			 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest)
		hexString.append(String.format("%02X", 0xFF & b));
			
		return hexString.toString();	
	}
	



	
}
