package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import componentes.janelas.Frame;
import comuns.Comuns;
import principal.clientes.FormDeGestaoDeClientes;
import principal.empresa.FormDeGestaoEmpresa;
import principal.entidades.tipo_evento.FormDeGestaoDeTipoEvento;
import principal.eventos.FormDeGestaoDeEventos;
import principal.ingressos.FormGerarIngressos;
import principal.ingressos.FormValidarIngresso;
import principal.lotes.FormDeGestaoDeLotes;
import principal.usuarios.FormDeGestaoDeUsuarios;




public class Principal extends Frame{

	

private static final long serialVersionUID = 1L;


	private JPanel painel_principal;

	
	

	public Principal(){
	
	    super("Ingressos", 
	    				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
	   					GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		
	    this.getContentPane().setBackground(Color.white);	
	    	
	    adicionarComponentes();
	}
	
	
	
	
	
	
	public void adicionarComponentes(){
	
		GridBagConstraints cons = new GridBagConstraints();  
	
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weighty  = 1;
		cons.weightx = 1;
		cons.insets = new Insets(0, 0, 0, 0);
		this.add(this.painel_principal = new JPanel(new GridBagLayout()), cons);
		this.painel_principal.setBackground(Comuns.COR_BRANCO);
	
		this.mostraHome();
	}




	
	
	private void mostraHome(){
		
		this.painel_principal.removeAll();		
		
		GridBagConstraints cons = new GridBagConstraints();  
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(0, 0, 0, 0);
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setBackground(Comuns.COR_BRANCO);
		this.painel_principal.add(p1, cons);
		
		cons.insets = new Insets(10, 10, 0, 0);
		p1.add(new JLabel("<html><b>Opções</b></html>") , cons);
		
		cons.insets = new Insets(10, 0, 10, 0);
		p1.add(new JSeparator(JSeparator.HORIZONTAL), cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 0, 5);
		JButton bt_empresa = new JButton(new ImageIcon(getClass().getResource("/icons/empresa.png")));
		bt_empresa.setToolTipText("Empresa");
		p1.add(bt_empresa, cons);
		
		JButton bt_usuarios = new JButton(new ImageIcon(getClass().getResource("/icons/usuario.png")));
		bt_usuarios.setToolTipText("Usuários");
		p1.add(bt_usuarios, cons);
		
		JButton bt_clientes = new JButton(new ImageIcon(getClass().getResource("/icons/cliente.png")));
		bt_clientes.setToolTipText("Clientes");
		p1.add(bt_clientes, cons);
		
		JButton bt_eventos = new JButton(new ImageIcon(getClass().getResource("/icons/evento.png")));
		bt_eventos.setToolTipText("Eventos");
		p1.add(bt_eventos, cons);
		
		JButton bt_lotes = new JButton(new ImageIcon(getClass().getResource("/icons/lote.png")));
		bt_lotes.setToolTipText("Lotes");
		p1.add(bt_lotes, cons);
		
		
		JButton bt_gerar_ingressos = new JButton(new ImageIcon(getClass().getResource("/icons/gerar_ingresso.png")));
		bt_gerar_ingressos.setToolTipText("Gerar Ingressos");
		p1.add(bt_gerar_ingressos, cons);
		

		JButton bt_validar_ingressos = new JButton(new ImageIcon(getClass().getResource("/icons/validar.png")));
		bt_validar_ingressos.setToolTipText("Validar Ingressos");
		p1.add(bt_validar_ingressos, cons);
		
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		JButton bt_tipos_entidades = new JButton(new ImageIcon(getClass().getResource("/icons/tipos.png")));
		bt_tipos_entidades.setToolTipText("Setores de Eventos");
		p1.add(bt_tipos_entidades, cons);
		
		cons.gridwidth = 1;
		cons.insets = new Insets(0, 5, 0, 5);
		p1.add(new JLabel("Empresa") , cons);
		p1.add(new JLabel("Usuários") , cons);
		p1.add(new JLabel("Clientes") , cons);
		p1.add(new JLabel("Eventos") , cons);
		p1.add(new JLabel("Lotes") , cons);
		p1.add(new JLabel("Gerar Ingressos") , cons);
		p1.add(new JLabel("Validar Ingressos") , cons);
		cons.gridwidth = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("Setores de Eventos") , cons);
		
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 0, 0, 0);
		JPanel p2 = new JPanel(new GridBagLayout()){
			
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g){
						
					super.paintComponent(g);
					
					Graphics2D g2 = (Graphics2D) g.create();	
	
					int largura  = this.getWidth();
					int altura  = this.getHeight();
						  
						try {
							
						BufferedImage	b = ImageIO.read(getClass().getResource("/icons/favicon.png"));
	
						    
						g2.setColor(new Color(66, 133, 244));
						g2.fillRect( 0, 0,   largura, altura);
						
						g2.drawImage(b, (largura - b.getWidth())/2, (altura - b.getHeight())/2,   b.getWidth(), b.getHeight(), null);

						} 
						catch (IOException e) {} 
				}
			};
		this.painel_principal.add(p2, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		JLabel lb;
		this.painel_principal.add(lb=new JLabel("Ingressos") , cons);
		lb.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 0, 0, 0);
		this.painel_principal.add(new JPanel(new GridBagLayout()){
			
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
					
				super.paintComponent(g);
				
				Graphics2D g2 = (Graphics2D) g.create();	

				int largura  = this.getWidth();
				int altura  = this.getHeight();
	
					g2.setColor(new Color(66, 133, 244));
					g2.fillRect( 0, 0,   largura, altura);
					
					//g2.drawImage(b, (largura - b.getWidth())/2, (altura - b.getHeight())/2,   b.getWidth(), b.getHeight(), null);
			}
		}, cons);
		
		
		
			bt_empresa.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
			    	
				FormDeGestaoEmpresa form = new FormDeGestaoEmpresa();	
				form.mostrar();
			}});

			
			
			bt_usuarios.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormDeGestaoDeUsuarios form = new FormDeGestaoDeUsuarios();	
					form.mostrar();
			}});
			
			
			
			bt_clientes.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    	
					FormDeGestaoDeClientes form = new FormDeGestaoDeClientes();	
					form.mostrar();
			}});
			
			
			
			bt_eventos.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormDeGestaoDeEventos form = new FormDeGestaoDeEventos();	
					form.mostrar();
			}});
			
			
			
			bt_lotes.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormDeGestaoDeLotes form = new FormDeGestaoDeLotes();	
					form.mostrar();
			}});

			
			bt_gerar_ingressos.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormGerarIngressos form = new FormGerarIngressos();	
					form.mostrar();
			}});
			
			bt_validar_ingressos.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormValidarIngresso form = new FormValidarIngresso();	
					form.mostrar();
			}});
			
			
			bt_tipos_entidades.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				    
					FormDeGestaoDeTipoEvento form = new FormDeGestaoDeTipoEvento();	
					form.mostrar();
			}});
			
			
			
			
		this.painel_principal.revalidate();
		this.painel_principal.repaint();
	}
	
	

	
	
	
}
