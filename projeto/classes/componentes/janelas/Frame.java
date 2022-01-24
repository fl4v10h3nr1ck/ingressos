

package componentes.janelas;



import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;





public class Frame extends JFrame implements Windows{


private static final long serialVersionUID = 1L;




	public Frame( String title, int width, int height){
	
	super(title);
			
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(width , height);
	this.setLocationRelativeTo(null);
	
	this.setLayout(new GridBagLayout());	
	
	try {setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png" )));} catch (IOException e) {e.printStackTrace();} 
	
	this.setFocusable(true);
	

	this.addKeyListener(new KeyAdapter(){
	
	public void keyPressed(KeyEvent ek){
				
	
		if(ek.getKeyCode() == KeyEvent.VK_F1){}
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F2){	}
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F3){}
	}});
			
			
			
			
	
	
	}

	
	


	@Override
	public void mostrar() {
		
	this.setVisible(true);
	}



	
	@Override
	public void esconder() {
	
	this.setVisible(false);
	}
	
	
	

}
