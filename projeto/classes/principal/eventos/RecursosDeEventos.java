package principal.eventos;

import comuns.Mensagens;
import dao.DAO;
import principal.Recurso;






public class RecursosDeEventos extends Recurso<Evento>{

	

	public RecursosDeEventos(){
		
		super("Tabela de Eventos", Evento.class);
	}
	
	
	
	
	public void adicionarComponentes(){
	
		super.adicionarComponentes();
	}
	
	


	@Override
	public void novo() {
	
		FormNovoEvento novo = new FormNovoEvento();
		novo.mostrar();
			
		motor.atualizar();
	}





	@Override
	public void alterar() {
		
		Evento selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado != null){
			
			FormAlterEvento alterForm = new FormAlterEvento( selecionado);
			alterForm.mostrar();
			
			motor.pesquisa();	
		}
		else
			Mensagens.msgDeErro("Selecione uma linha da tabela para alteração.");
	}





	@Override
	public void deletar() {
	
		Evento selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado == null)
			Mensagens.msgDeErro("Selecione uma linha da tabela abaixo para exclusão.");
		else{
				
			if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluír este item?")){	
				
				if( new DAO<Evento>(Evento.class).desativar(selecionado.getId_evento()))
					this.motor.modelo.atualiza();
			}
		}
	}
	
	
	
}

