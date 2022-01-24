package principal.clientes;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import componentes.janelas.Dialogo;





public class FormDeGestaoDeClientes extends Dialogo{

	

private static final long serialVersionUID = 1L;
	



	public FormDeGestaoDeClientes() {
	
		super("Gestão de Clientes", 1024, 600);
		
		adicionarComponentes();
	}

	
	
	
	
	public void adicionarComponentes(){
		    
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	this.add(new RecursosDeClientes().getComponentesDeRecurso(), cons);
	}





	@Override
	public boolean acaoPrincipal() {return false;}

}
