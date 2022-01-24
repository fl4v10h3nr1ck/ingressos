package componentes.tabelas;

import java.lang.reflect.Field;
import java.util.Date;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import dao.DAO;



public class ModeloDeTabela<T>  extends ModeloDeTabelaBase<T>{

	

private static final long serialVersionUID = 1L;




	public ModeloDeTabela(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabela(Class<?> tipo, boolean paginacao){
		
	super(tipo, paginacao);
	}

	
	
	
	@SuppressWarnings("unchecked")
	public void pesquisa(String termos, String local){
		
	this.linhas.clear();
	
	String sub_query_filtro = null;
	
	String sub_query_desativados = "";

	String order_by = " ORDER BY "+classe.getAnnotation(Anot_BD_Tabela.class).prefixo()+".";
	
	Field[] fields  = this.classe.getDeclaredFields();
	
		for (Field field : fields) {
		
		if(field.isAnnotationPresent(Anot_BD_Campo.class) && 
				field.getAnnotation(Anot_BD_Campo.class).nome().compareTo("status")==0 && 
				sub_query_desativados.length()==0)
		sub_query_desativados =  " "+classe.getAnnotation(Anot_BD_Tabela.class).prefixo()+".status=1 and ";
			
			
			
		if (field.getAnnotation(Anot_BD_Campo.class).ehId())
		order_by += field.getAnnotation(Anot_BD_Campo.class).nome()+" DESC";
		}		
			
	
		if(local.length() > 0){	
		
			for (Field field : fields) {
					
				if (field.isAnnotationPresent(Anot_BD_Campo.class) &&
						field.isAnnotationPresent(Anot_TB_Coluna.class) &&
							field.getAnnotation(Anot_TB_Coluna.class).rotulo().compareTo(local) == 0){
						
				if(field.getAnnotation(Anot_BD_Campo.class).tipo().equals(Integer.class) || field.getAnnotation(Anot_BD_Campo.class).tipo().equals(Date.class))
				termos = termos.replaceAll("\\D", " ");	
					
				sub_query_filtro = ModeloDeTabelaBase.searchSubQuery(termos, 
											(field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?classe.getAnnotation(Anot_BD_Tabela.class).prefixo():field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome());	
				break;
				}
			}	
		}
	
	
	
	String sub_query = (this.query_where!=null && this.query_where.length()>0?this.query_where+" AND ":"")+
		sub_query_desativados+
		(sub_query_filtro != null && sub_query_filtro.length()> 0? sub_query_filtro:"1");
		
	
	DAO<T> dao = new DAO<T>((Class<T>) this.classe); 
		
		
	this.countItens = dao.getCont(null, sub_query, null);
		
		
	this.linhas.addAll(dao.get(null, sub_query+ order_by+" LIMIT "+(paginacao?this.MAXPAGEITENS+" OFFSET "+this.currentIndex:"50"), null));	
	
	fireTableDataChanged();
	}	

	
	

	
}
