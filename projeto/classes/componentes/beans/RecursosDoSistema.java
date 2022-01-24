package componentes.beans;

import java.util.ArrayList;
import java.util.List;

import vocenaovaipassar.beans.ListaDeRecursosDoSistema;
import vocenaovaipassar.beans.Recurso;






public class RecursosDoSistema extends ListaDeRecursosDoSistema{

	
	
	@Override
	public List<Recurso> getRecursos() {
	
	List<Recurso> lista_de_recursos = new ArrayList<Recurso>();	
		
	lista_de_recursos.add(new Recurso("Cadastros", Recurso.VER_ED_REM, "GERECAD"));	
	lista_de_recursos.add(new Recurso("Gerar relatórios", Recurso.SIM_NAO, "GEREREL"));	
	lista_de_recursos.add(new Recurso("Registro e licenciamento", Recurso.SIM_NAO, "REGLICEN"));	
	lista_de_recursos.add(new Recurso("Informações empresariais", Recurso.SIM_NAO, "INFOEMP"));	
			
	return lista_de_recursos;
	}

}
