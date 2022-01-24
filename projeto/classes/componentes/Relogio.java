package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;




public class Relogio extends JLabel implements ActionListener {  
	      
	
private static final long serialVersionUID = 1L;

private SimpleDateFormat df;
private Date tempDate;  
private Timer timer;  
	          
	



	public Relogio() {  
	
	super("");  
	
	this.df = new SimpleDateFormat("HH:mm"); 
	
	this.tempDate = new Date();  
	this.timer = new Timer(60 * 1000, this);  
	
	timer.setCoalesce(true);  
	timer.setRepeats(true);  
	}  
	          
	
	
	
	
	
	
	
	public void start() {  
	setTime();  
	timer.start();  
	}  
	  
	
	
	
	
	
	public void stop() {  
	timer.stop();  
	this.setText("");  
	}  
	          
	
	
	
	
	
	private void setTime() {  
	tempDate.setTime(System.currentTimeMillis());  
	super.setText(df.format(tempDate));  
	}  
	 
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {  
	setTime();  
	}  



}
