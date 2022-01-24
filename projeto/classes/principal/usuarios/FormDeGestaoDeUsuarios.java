package principal.usuarios;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import componentes.janelas.Dialogo;





public class FormDeGestaoDeUsuarios extends Dialogo{

	

private static final long serialVersionUID = 1L;
	



	public FormDeGestaoDeUsuarios() {
	
		super("Gestão de Usuários", 1024, 600);
		
		adicionarComponentes();
	}

	
	
	
	
	public void adicionarComponentes(){
		    
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	this.add(new RecursosDeUsuarios().getComponentesDeRecurso(), cons);
	}





	@Override
	public boolean acaoPrincipal() {return false;}

}
