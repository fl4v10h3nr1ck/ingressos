package principal.usuarios;

import comuns.Mensagens;
import dao.DAO;
import principal.Recurso;







public class RecursosDeUsuarios extends Recurso<Usuario>{

	

	public RecursosDeUsuarios(){
		
		super("Tabela de Usuários", Usuario.class);
	}
	
	
	
	
	public void adicionarComponentes(){
	
		super.adicionarComponentes();
	}
	
	


	@Override
	public void novo() {
	
		FormNovoUsuario novo = new FormNovoUsuario();
		novo.mostrar();
			
		motor.atualizar();
	}





	@Override
	public void alterar() {
		
		Usuario selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado != null){
			
			FormAlterUsuario alterForm = new FormAlterUsuario( selecionado);
			alterForm.mostrar();
			
			motor.pesquisa();	
		}
		else
			Mensagens.msgDeErro("Selecione uma linha da tabela para alteração.");
	}





	@Override
	public void deletar() {
	
		Usuario selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado == null)
			Mensagens.msgDeErro("Selecione uma linha da tabela abaixo para exclusão.");
		else{
				
			if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluír este item?")){	
				
				if( new DAO<Usuario>(Usuario.class).desativar(selecionado.getId_usuario()))
					this.motor.modelo.atualiza();
			}
		}
	}
	
	
	
}

