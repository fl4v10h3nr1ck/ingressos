package principal.entidades.tipo_evento;

import comuns.Mensagens;
import dao.DAO;
import principal.Recurso;
import principal.entidades.Entidade;







public class RecursosDeTipoEvento extends Recurso<Entidade>{

	

	public RecursosDeTipoEvento(){
		
		super("Tabela de Setores de Eventos", Entidade.class);
	}
	
	
	
	
	public void adicionarComponentes(){
	
		super.adicionarComponentes();
	}
	
	


	@Override
	public void novo() {
	
		FormNovoTipoEvento novo = new FormNovoTipoEvento();
		novo.mostrar();
			
		motor.atualizar();
	}





	@Override
	public void alterar() {
		
		Entidade selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado != null){
			
			FormAlterTipoEvento alterForm = new FormAlterTipoEvento( selecionado);
			alterForm.mostrar();
			
			motor.pesquisa();	
		}
		else
			Mensagens.msgDeErro("Selecione uma linha da tabela para alteração.");
	}





	@Override
	public void deletar() {
	
		Entidade selecionado= motor.modelo.getLinha(motor.tabela.getSelectedRow());
		
		if(selecionado == null)
			Mensagens.msgDeErro("Selecione uma linha da tabela abaixo para exclusão.");
		else{
				
			if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluír este item?")){	
				
				DAO<Entidade> dao =  new DAO<Entidade>(Entidade.class);
				
				selecionado = dao.get(selecionado.getId_entidade());
				
				selecionado.setStatus(0);
				
				if( dao.altera(selecionado))
					this.motor.modelo.atualiza();
			}
		}
	}
	
	
	
}

