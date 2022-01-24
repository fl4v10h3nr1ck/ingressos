


package componentes;



import javax.swing.JFrame;


import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;





public class Barra_De_Progresso extends JFrame{

	


private static final long serialVersionUID = 1L;




private int status = 0;    // aguarda o status da operação de 0  a 10.



// dimensões da barra de progresso
private int largura;
private int altura;
private int bordas;


private int largura_total;
private int altura_total;

private Color cor_bordas;
private Color cor_barras_progresso;
private Color cor_fundo_esquerdo;
private Color  cor_fundo_direito; 



private boolean aguarde;


	public Barra_De_Progresso(){
	
	this(200, 20, 2, false);
	}



	
	
	


	public Barra_De_Progresso( int largura, int altura , int bordas, boolean tipo){
	
	
	this( largura , altura, bordas, Color.black, Color.black, new Color(139, 134, 130), Color.white, false);
	}
	
	
	
	
	
	
	
	
	
	public Barra_De_Progresso( int largura, int altura , int bordas,
			Color cor_bordas, Color cor_barras_progresso, Color cor_fundo_esquerdo, Color  cor_fundo_direito, boolean tipo ){
	

		
		// a largura e altura recebida no construtor é apenas a da barra de status sem as bordas e divisórias,
		//ou seja, é apenas da parte interior da barra de progresso.	
			
		// é importa que a largura seja divisível por 10;	
			
		this.largura = largura;
		this.altura = altura;
		this.bordas = bordas;
		this.largura_total = this.largura + (15* this.bordas)/2;    // equivalente a: this.largura + 2* this.bordas + 9*this.bordas/2;
		this.altura_total = this.altura + (3* this.bordas);
		
		this.cor_bordas = cor_bordas;
		this.cor_barras_progresso = cor_barras_progresso;
		this.cor_fundo_esquerdo = cor_fundo_esquerdo;
		this.cor_fundo_direito = cor_fundo_direito; 
		
		this.aguarde = tipo;
		
		
		this.setSize( this.largura_total, this.altura_total);
		this.setResizable(false); 
		this.setLayout(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	    this.setAlwaysOnTop(true);    //faz essa janela sempre ficar na frente de todas as outras janelas.	
			
		
	}
	
	
	
	
	

	
	
	
	public final void paint(){


	
	Graphics g = getBufferStrategy().getDrawGraphics();	    
	Graphics2D g2 = (Graphics2D) g.create(getInsets().right,
            getInsets().top,
            getWidth() - getInsets().left,
            getHeight() - getInsets().bottom);	
			
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
	
	g2.setPaint( this.cor_bordas);
	g2.fillRect(  0 , 0  , this.largura_total, this.altura_total  );
	
	GradientPaint paint  = new GradientPaint(   0, this.altura_total/2, this.cor_fundo_esquerdo,
															this.largura_total, this.altura_total/2 ,this.cor_fundo_direito);	
	g2.setPaint(paint);
	g2.fillRoundRect( this.bordas, this.bordas , this.largura_total - 2*this.bordas,  this.altura + this.bordas, this.bordas, this.bordas);
	
	if(this.aguarde){
	g2.setPaint( this.cor_barras_progresso);
	Font   font         = new Font( "Arial" ,Font.BOLD , 18 );
	g2.setFont( font);	
	g2.drawString( "Aguarde...", 10, 23);	
	
	}
	else{
	g2.setPaint( this.cor_barras_progresso);
	for( int i = 0; i <10 && i < this.status; i++)
	g2.fillRect(  (3*this.bordas/2) + i *(( this.largura + 5 * this.bordas)/10), 3*this.bordas/2 ,this.largura/10,  this.altura  );
	}
	
	if (!getBufferStrategy().contentsLost())
		getBufferStrategy().show();


		g.dispose(); 
		g2.dispose();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// é usado o desenho direto para a barra de progresso
	
	public void paint( Graphics g){
		
	super.paint(g);	
	paint();	
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	public void mostrar(){
		
	setVisible(true);	
	createBufferStrategy(2);
	paint();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// atualiza a barra em 10%
	public void atualizar(){
		
	this.status++;
	paint();
	}
	
	
	
	
	
	
	
	
	
	
	
	// atualiza a barra em x%
	public void atualizar( int x){
		
	this.status += x;
	paint();
	}
	
	
	
	
	
	
	
	
	// limpa o status
	public void LimparStatus(){
		
	this.status = 0;
	}
	
	
	
	
	
}
