

package principal.usuarios;

import java.util.Date;

import dao.DAO;





public class FormNovoUsuario extends FormUsuarioBase{


private static final long serialVersionUID = 1L;



	public FormNovoUsuario(){
		
		this(null);
	}
	
	
	
	
	public FormNovoUsuario(Usuario usuario){
		
		super(usuario);	
		
		adicionarComponentes();
	}
	
	

	
	public boolean acaoPrincipal(){
		
		if(!validacao())
			return false;
		
		if(this.usuario==null)
			this.usuario = new Usuario();
		
		usuario.setNome(tf_nome.getText());
		usuario.setSenha(cript(String.valueOf(this.tf_senha.getPassword())));
		
		usuario.setStatus(1);
		usuario.setData_cadastro(new Date());
		
		int id_usuario = new DAO<Usuario>(Usuario.class).novo(usuario);
			
		if(id_usuario>0){
					
			usuario.setId_usuario(id_usuario);
			return true;
		}
		
		return false;
	}




	
	
}
