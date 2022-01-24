
package componentes.janelas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import componentes.ouvintes.OuvinteTecladoDialogos;
import comuns.Comuns;





public abstract class Dialogo extends JDialog implements Windows{

	
private static final long serialVersionUID = 1L;




	public Dialogo( String title, int width, int height){
	
		super();
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(width , height);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		this.setModal(true);
		this.getContentPane().setBackground(Comuns.COR_BRANCO);  
		
		try {setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png")));} catch (IOException e) {e.printStackTrace();} 
	}

	
	
	
	
	public abstract void adicionarComponentes();
	
	
	
	


	@Override
	public void mostrar() {
	
		this.addOuvinte(this);			
		
		this.getContentPane().setBackground(Comuns.COR_BRANCO);  
		
		this.setVisible(true);
	}



	
	@Override
	public void esconder() {
		
		this.setVisible(false);
	}
	
	
	
	
	private void addOuvinte(Container container) {  
		
		for (Component c : container.getComponents())       
			addOuvinte((Container)c); 
		
		if(container instanceof JLabel){
		
			Font padrao = UIManager.getFont("Label.font");
		
			// se a fonte do componente é igual a padrao entao ele nao recebeu personalizacao exclusiva, logo, podem mudar.
			if(container.getFont().getSize() == padrao.getSize() && 
					container.getFont().getFontName().compareTo(padrao.getFontName())==0 && 
						container.getFont().getStyle() == padrao.getStyle()){
			
				container.setForeground(Comuns.COR_PRETO);	
				container.setFont(new Font("SansSerif", Font.PLAIN, 11) );
			}
		}
		
		if(container instanceof JPanel){
			
			Color padrao = UIManager.getColor("Panel.background");
			
			if(container.getBackground() == padrao)	
				container.setBackground(Comuns.COR_BRANCO);	
		}
		
		container.addKeyListener(new OuvinteTecladoDialogos(this));
	} 

	
	
	
	
	public abstract boolean acaoPrincipal();
	
	
	
	
	
	
	
	
}
