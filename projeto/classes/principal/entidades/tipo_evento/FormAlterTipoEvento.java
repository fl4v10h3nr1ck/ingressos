package principal.entidades.tipo_evento;

import dao.DAO;
import principal.entidades.Entidade;




public class FormAlterTipoEvento extends FormTipoEventoBase{




private static final long serialVersionUID = 1L;

	


	public FormAlterTipoEvento(Entidade entidade) {
	
		super(entidade);
		
		this.adicionarComponentes();
		
		this.setValores();
	}


	

	

	public void setValores(){
	
		this.tf_nome.setText(this.entidade.getNome());
	}
	
	
	
	
	
	public boolean acaoPrincipal(){

		if(!validacao())
			return false;
						
		entidade.setNome(tf_nome.getText());
		
		return new DAO<Entidade>(Entidade.class).altera(this.entidade);
	}
	
	
	
}
