package componentes.tabelas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import comuns.Comuns;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_TB_Coluna;



public abstract class ModeloDeTabelaBase<T>  extends AbstractTableModel{

	

private static final long serialVersionUID = 1L;


public int currentIndex = 0;
public int MAXPAGEITENS;
public int countItens = 0;

protected String query_join;
protected String query_where;
protected String query_order_by;



public List<T> linhas;

protected Class<?> classe;


protected boolean paginacao;



	public ModeloDeTabelaBase(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabelaBase(Class<?> tipo, boolean paginacao){
		
	MAXPAGEITENS = Comuns.NUM_MAX_ITENS_TAB;
	classe = tipo;
	
	this.linhas = new ArrayList<T>();
	
	this.query_join = "";
	this.query_where = "";
	this.query_order_by = "";
	
	this.paginacao =paginacao;
	}

	
	

	public void setLista(List<T> linhas){
	
	this.linhas.clear();	
	
	this.linhas.addAll(linhas);
	
	fireTableDataChanged();
	}
	
	
	
	
	
	
	@Override
	public int getColumnCount() {
		
	int colunas = 0;
	
		for (Field field : classe.getDeclaredFields()) {
		
		if (field.isAnnotationPresent(Anot_TB_Coluna.class))
		colunas++;
	    }
	return colunas;
	}
	
	
	
	
	@Override
	public int getRowCount() {
	
	return this.linhas.size();
	}

	
	
	
	@Override
	public Object getValueAt(int linha, int coluna) {

		try {
		  
			for (Field field : classe.getDeclaredFields()) {
				
				if (field.isAnnotationPresent(Anot_TB_Coluna.class) &&
							field.isAnnotationPresent(Anot_BD_Campo.class) && 
								field.getAnnotation(Anot_TB_Coluna.class).posicao() == coluna)
				return classe.getMethod(field.getAnnotation(Anot_BD_Campo.class).getTab().length()>0?
											field.getAnnotation(Anot_BD_Campo.class).getTab():
												field.getAnnotation(Anot_BD_Campo.class).get()).invoke(this.linhas.get(linha));
					
			}		
		} 
		catch (Exception e) {return "Erro";}
		
	return "";
	}	
		
	

	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
	
	
	
	
	@Override
	public String getColumnName(int columnIndex) {
	
		for (Field field : classe.getDeclaredFields()) {
			
		if (field.isAnnotationPresent(Anot_TB_Coluna.class)&& 
				field.getAnnotation(Anot_TB_Coluna.class).posicao() == columnIndex)
		return "<html><b>"+field.getAnnotation(Anot_TB_Coluna.class).rotulo()+"</b></html>";
		  
		}

	return "";
	}
	
	
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	
	return String.class;
	}
	
	
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
	
	
	
	
	public void atualiza(){
		
	this.atualiza("", "");
	}
	
	
	
	
	public void atualiza(String termos, String local){
		
	this.currentIndex = 0;
	this.countItens = 0;
			
	this.pesquisa(termos, local);		
	}
		



	public T getLinha(int index){
	
	if(index< 0 || index >= this.linhas.size())
	return null;
	
	return this.linhas.get(index);	
	}

	
	

	
	public abstract void pesquisa(String termos, String local);
	
	
	

	
	protected Object[] getLocais(){
		
	List<String>aux = new ArrayList<String>();
	
	boolean control;
		for (int i = 0; i < 20; i++) {
			
		control	 = false;
			for (Field field : classe.getDeclaredFields()) {
		
				if ( field.isAnnotationPresent(Anot_TB_Coluna.class) && 
						field.getAnnotation(Anot_TB_Coluna.class).posicao()== i){
				
				aux.add(field.getAnnotation(Anot_TB_Coluna.class).rotulo());
				control = true;
				break;
				}
			}	
		
		if(!control)
		break;
		}
		
	return aux.toArray();
	}
	
	
	

	public Class<?> getTipo(){
		
	return this.classe;	
	}
	
	
	
	
	

	
	public void limpaPesquisa(String local){
		
	this.currentIndex = 0;
	this.countItens = 0;
	
	this.pesquisa("", local);
	}
	
	
	

	
/************************ paginacao *********************************/	
	
	
	
	

	public void proximaPagina(String termos, String local){
		
	if(this.currentIndex < (this.countItens - this.MAXPAGEITENS))
	this.currentIndex += this.MAXPAGEITENS;
	
	this.pesquisa(termos, local);	
	}
	


	
	
	public void paginaAnterior(String termos, String local){
	
	if((this.currentIndex - this.MAXPAGEITENS) >= 0)
	this.currentIndex -= this.MAXPAGEITENS; 	

	this.pesquisa(termos, local);	
	}
		
	
	
	
	
	public void primeiraPagina(String termos, String local){
		
	this.currentIndex = 0;
	
	this.pesquisa(termos, local);	
	}
			
	
	
	
	public void ultimaPagina(String termos, String local){
		
		if(this.countItens>= this.MAXPAGEITENS){
			
		if(this.countItens%this.MAXPAGEITENS == 0)
		this.currentIndex = this.countItens - this.MAXPAGEITENS;
		else
		this.currentIndex = this.countItens-(this.countItens%this.MAXPAGEITENS);	
		}	
	
	this.pesquisa(termos, local);	
	}
			
	
	
	
	

	public static String searchSubQuery(String search, String locale){
		
	
	if(search == null || locale == null || search.length() == 0 || locale.length() == 0) 
	return "";
						
	StringBuilder subquery = new StringBuilder();	
			
	String[] tokens = search.split("\\s");
	String[] fields = locale.split("\\s");
			
		for (int i=0; i<fields.length; i++){
			    
		subquery.append(" ( ");	 
			    
		for (int j=0; j<tokens.length; j++){
		subquery.append(fields[i]+ " like '%"+tokens[j]+"%' ");
				    			 
		if(j< tokens.length-1)
		subquery.append(" AND ");	
		}
				
	subquery.append(" ) ");	
				
		if(i< fields.length-1)
		subquery.append(" OR ");
		}
	
	return subquery.toString();
	}
	


	
	
	
	
	
	
/************************ paginacao *********************************/	

	

	public void setJoin(String join){this.query_join = join;}
	public void setWhere(String where){this.query_where = where;}
	public void setOrderBy(String order){this.query_order_by = order;}
}
