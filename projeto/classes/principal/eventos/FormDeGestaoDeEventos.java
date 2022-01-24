package principal.eventos;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import componentes.janelas.Dialogo;





public class FormDeGestaoDeEventos extends Dialogo{

	

private static final long serialVersionUID = 1L;
	



	public FormDeGestaoDeEventos() {
	
		super("Gestão de Eventos", 1024, 600);
		
		adicionarComponentes();
	}

	
	
	
	
	public void adicionarComponentes(){
		    
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	this.add(new RecursosDeEventos().getComponentesDeRecurso(), cons);
	}





	@Override
	public boolean acaoPrincipal() {return false;}

}
