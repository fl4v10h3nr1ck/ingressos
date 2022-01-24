package principal.clientes;

import comuns.Mensagens;
import dao.DAO;
import principal.Recurso;






public class RecursosDeClientes extends Recurso<Cliente>{

	

	public RecursosDeClientes(){
		
		super("Tabela de Clientes", Cliente.class);
	}
	
	
	
	
	public void adicionarComponentes(){
	
		super.adicionarComponentes();
	}
	
	


	@Override
	public void novo() {
	
		FormNovoCliente novo = new FormNovoCliente();
		novo.mostrar();
			
		motor.atualizar();
	}





	@Override
	public void alterar() {
		
		Cliente selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado != null){
			
			FormAlterCliente alterForm = new FormAlterCliente( selecionado);
			alterForm.mostrar();
			
			motor.pesquisa();	
		}
		else
			Mensagens.msgDeErro("Selecione uma linha da tabela para alteração.");
	}





	@Override
	public void deletar() {
	
		Cliente selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado == null)
			Mensagens.msgDeErro("Selecione uma linha da tabela abaixo para exclusão.");
		else{
				
			if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluír este item?")){	
				
				if( new DAO<Cliente>(Cliente.class).desativar(selecionado.getId_cliente()))
					this.motor.modelo.atualiza();
			}
		}
	}
	
	
	
}

