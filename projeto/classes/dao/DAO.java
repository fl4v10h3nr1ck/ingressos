package dao;

import comuns.Configuracoes;
import principal.DAOBase;




public class DAO<T> extends DAOBase<T>{

	
	
	
	
	public DAO(Class<T> tipo) {
		
	super(tipo, Configuracoes.connector, true);
	}

	
	
	
	
}
