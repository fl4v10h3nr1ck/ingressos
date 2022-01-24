package principal.usuarios;

import dao.DAO;




public class FormAlterUsuario extends FormUsuarioBase{




private static final long serialVersionUID = 1L;

	


	public FormAlterUsuario(Usuario usuario) {
	
		super(usuario);
		
		this.adicionarComponentes();
		
		this.setValores();
	}


	

	

	public void setValores(){
	
		this.tf_nome.setText(this.usuario.getNome());
	}
	
	
	
	
	
	public boolean acaoPrincipal(){

		if(!validacao())
			return false;
						
		usuario.setNome(tf_nome.getText());
		
		if(this.tf_senha.getPassword().length>0)
			usuario.setSenha(cript(String.valueOf(this.tf_senha.getPassword())));
	
		return new DAO<Usuario>(Usuario.class).altera(this.usuario);
	}
	
	
	
}
