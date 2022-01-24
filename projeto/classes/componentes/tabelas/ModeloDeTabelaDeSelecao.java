package componentes.tabelas;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Configuracoes;




public class ModeloDeTabelaDeSelecao<T>  extends ModeloDeTabelaBase<T>{

	

private static final long serialVersionUID = 1L;




	public ModeloDeTabelaDeSelecao(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabelaDeSelecao(Class<?> tipo, boolean paginacao){
		
	super(tipo, paginacao);
	}

	

	

	@Override
	public int getColumnCount() {
		
	int colunas = 0;
		for (Field field : classe.getDeclaredFields()) {
			
		if (field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class))
		colunas++;
	    }
			
	return colunas;
	}
	
	
	
	
	
	@Override
	public Object getValueAt(int linha, int coluna) {
	
		try {
			  
			for (Field field : classe.getDeclaredFields()) {
					
			if (field.isAnnotationPresent(Anot_TB_Coluna.class) && 
					field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class) &&
						field.isAnnotationPresent(Anot_BD_Campo.class) &&
							field.getAnnotation(Anot_TB_Coluna_Selecao.class).posicao() == coluna)	
			return classe.getMethod(field.getAnnotation(Anot_BD_Campo.class).getTab().length()>0?
							field.getAnnotation(Anot_BD_Campo.class).getTab():
								field.getAnnotation(Anot_BD_Campo.class).get()).invoke(this.linhas.get(linha));
					
			}				
		} 
		catch (Exception e) {return "Erro";}
	
	return "";
	}	
		
	

	
	

	@Override
	public String getColumnName(int columnIndex) {
	

		try {	
			for (Field field : classe.getDeclaredFields()) {
				
			if (field.isAnnotationPresent(Anot_TB_Coluna.class) && 
					field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class) &&
						field.getAnnotation(Anot_TB_Coluna_Selecao.class).posicao() == columnIndex)
			return field.getAnnotation(Anot_TB_Coluna.class).rotulo();
		    }
		} 
		catch (SecurityException e) {e.printStackTrace();return "";}	
			
	return "";
	}
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public void pesquisa(String termos, String local){
		
	this.linhas.clear();
	
	String tabela = classe.getAnnotation(Anot_BD_Tabela.class).nome();
	String prefixo = classe.getAnnotation(Anot_BD_Tabela.class).prefixo();
	String join = classe.getAnnotation(Anot_BD_Tabela.class).join();
	
	StringBuilder campos = new StringBuilder();
	String sub_query_filtro = null;
	StringBuilder campos_filtro = new StringBuilder();
	
	
	Field[] fields = this.classe.getDeclaredFields();
			
		for (Field field : fields) {
			
			if (field.isAnnotationPresent(Anot_BD_Campo.class) && 
					(field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class) ||
							field.getAnnotation(Anot_BD_Campo.class).ehId())){		
			campos_filtro.append((field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?prefixo:field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome()+" ");
			}	
		}
	
	sub_query_filtro = ModeloDeTabelaBase.searchSubQuery(termos, campos_filtro.toString());	
	campos.append(campos_filtro.toString().replace(" ", ", "));
	campos.delete(campos.length() -2, campos.length());
		
		try {
		
		Statement st = Configuracoes.connector.createStatement();
		
		
		System.out.println("SELECT "+campos+" FROM "+tabela+" as "+prefixo+" "+
				" "+(join!=null && join.length()>0?join:"")+
				(this.query_join != null && this.query_join.length()> 0? this.query_join:"")+
				" WHERE "+
				(this.query_where != null && this.query_where.length()> 0? this.query_where:"1")+" "+
				(sub_query_filtro != null && sub_query_filtro.length()> 0? "and "+sub_query_filtro:"")+" "+
				(this.query_order_by != null && this.query_order_by.length()> 0? " ORDER BY "+this.query_order_by:"")+" "+
				" LIMIT "+
				(paginacao?this.MAXPAGEITENS+" OFFSET "+this.currentIndex:"50"));
		
		
		
		ResultSet resultSet = st.executeQuery(
		"SELECT "+campos+" FROM "+tabela+" as "+prefixo+" "+
		" "+(join!=null && join.length()>0?join:"")+
		(this.query_join != null && this.query_join.length()> 0? this.query_join:"")+
		" WHERE "+
		(this.query_where != null && this.query_where.length()> 0? this.query_where:"1")+" "+
		(sub_query_filtro != null && sub_query_filtro.length()> 0? "and "+sub_query_filtro:"")+" "+
		(this.query_order_by != null && this.query_order_by.length()> 0? " ORDER BY "+this.query_order_by:"")+" "+
		" LIMIT "+
		(paginacao?this.MAXPAGEITENS+" OFFSET "+this.currentIndex:"50"));
		
			
			while(resultSet.next()){

			T aux = (T) this.classe.newInstance();
			
				for (Field field : fields){		
							
				if (field.isAnnotationPresent(Anot_BD_Campo.class) && 
						(field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class) ||
								field.getAnnotation(Anot_BD_Campo.class).ehId()))
				aux.getClass().getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).set(), field.getAnnotation(Anot_BD_Campo.class).tipo()).
								invoke(aux, 
								resultSet.getObject((field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?prefixo:field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome()));
				}
				
				
			 this.linhas.add((T) aux);
			 }
		 	
		fireTableDataChanged();
		st.close();
		}
		catch (SQLException sqlException ) {sqlException.printStackTrace();this.linhas.clear();}	
		catch (InstantiationException e) {e.printStackTrace();this.linhas.clear();}
		catch (IllegalAccessException e) {e.printStackTrace();this.linhas.clear();}
		catch (IllegalArgumentException e){e.printStackTrace();this.linhas.clear();}
		catch (InvocationTargetException e) {e.printStackTrace();this.linhas.clear();}
		catch (NoSuchMethodException e) {e.printStackTrace();this.linhas.clear();}
		catch (SecurityException e) {e.printStackTrace();this.linhas.clear();}
	}
	
	
	
	
	
/*	
	

	
	public void pesquisa(String termos, String local){
		
	this.linhas.clear();
	
	String sub_query_filtro = null;
	StringBuilder campos_filtro = new StringBuilder();
	String prefixo = classe.getAnnotation(Anot_BD_Tabela.class).prefixo();
	

		if(local.length() > 0){	
				
			for (Field field : this.classe.getDeclaredFields()) {
			
				if (field.isAnnotationPresent(Anot_TB_LocaisDeBusca.class) &&
						field.isAnnotationPresent(Anot_BD_Campo.class) && 
							field.getAnnotation(Anot_TB_LocaisDeBusca.class).selecao())
				campos_filtro.append((field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?prefixo:field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome()+" ");
			}
		}
			
	sub_query_filtro = Comuns.searchSubQuery(termos, campos_filtro.toString());

	String sub_query = 
		(this.query_where!= null && this.query_where.length()>0? this.query_where+" and ":"")+
		(sub_query_filtro != null && sub_query_filtro.length()> 0? sub_query_filtro:"1");
		
	
	DAO<T> dao = new DAO<T>(this.classe); 
		
		
	this.countItens = dao.getCont(null, sub_query, null);
		
		
	this.linhas.addAll(dao.get(null, sub_query+ " LIMIT "+(paginacao?this.MAXPAGEITENS+" OFFSET "+this.currentIndex:"50"), null));	
	
	fireTableDataChanged();
	}	

*/	
	

}