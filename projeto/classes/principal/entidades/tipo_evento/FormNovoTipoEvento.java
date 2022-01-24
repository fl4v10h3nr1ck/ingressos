

package principal.entidades.tipo_evento;

import comuns.Comuns;
import dao.DAO;
import principal.entidades.Entidade;





public class FormNovoTipoEvento extends FormTipoEventoBase{


private static final long serialVersionUID = 1L;



	public FormNovoTipoEvento(){
		
		this(null);
	}
	
	
	
	
	public FormNovoTipoEvento(Entidade entidade){
		
		super(entidade);	
		
		adicionarComponentes();
	}
	
	

	
	public boolean acaoPrincipal(){
		
		if(!validacao())
			return false;
		
		if(this.entidade==null)
			this.entidade = new Entidade();
		
		entidade.setNome(tf_nome.getText());
	
		entidade.setStatus(1);
		entidade.setTipo(Comuns.ETD_TIPO_INGRESSOS);
		
		int id_entidade = new DAO<Entidade>(Entidade.class).novo(entidade);
			
		if(id_entidade>0){
					
			entidade.setId_entidade(id_entidade);
			return true;
		}
		
		return false;
	}




	
	
}
