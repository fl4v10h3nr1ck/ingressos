
package componentes.tabelas;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import comuns.Comuns;
import anotacoes.Anot_TB_Coluna;





public class MotorDePesquisa<T> extends JPanel{


	

private static final long serialVersionUID = 1L;

		

public JTextField tf_termos;
public JButton bt_pesquisar;
public JComboBox <Object>  locais;

public JTable tabela;
public ModeloDeTabela<T> modelo;

	
private String titulo;

public JLabel informacoes_de_tabela;

public JPanel painel_de_opcoes;

public JScrollPane scroll;

public Class<?> tipoDeClasse;




	public MotorDePesquisa(String titulo, Class<?> tipoDeClasse){
	
	this(titulo, tipoDeClasse, true);
	}
	

	
	
	
	public MotorDePesquisa(String titulo, Class<?> TipoDeclasse, boolean paginacao){
		
	this.titulo = titulo;
	this.setLayout(new GridBagLayout());
	this.setBackground(Color.white);

	this.tipoDeClasse  =TipoDeclasse;
	
	this.modelo = new ModeloDeTabela<T>(TipoDeclasse, paginacao);
	
	this.adicionarComponentes();

		for (Field field : TipoDeclasse.getDeclaredFields()) {
		
		if (field.isAnnotationPresent(Anot_TB_Coluna.class))
		this.tabela.getColumnModel().getColumn(field.getAnnotation(Anot_TB_Coluna.class).posicao()).setPreferredWidth(field.getAnnotation(Anot_TB_Coluna.class).comprimento()*(1400/100));	
		}
	}
		

	
	
	
	
	public void adicionarComponentes(){
		
		GridBagConstraints cons = new GridBagConstraints();   
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(4, 5, 4, 2);
		this.add(new JLabel("<html><b>"+this.titulo+"</b></html>"), cons);
		cons.insets = new Insets(4, 1, 4, 0);
		this.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setBackground(Color.WHITE);
		this.add(p1, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 0;
		cons.insets = new Insets(2, 2, 0, 2);
		this.painel_de_opcoes = new JPanel(new GridBagLayout());
		this.painel_de_opcoes.setBackground(Color.WHITE);	
		this.add(this.painel_de_opcoes, cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridwidth = 1;
		cons.weightx = 0.7;
		p1.add(new JLabel("Termos:"), cons);
		cons.weightx = 0.3;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("Local:"), cons);
	
		
		cons.gridwidth = 1;
		cons.weightx = 0.7;
		this.tf_termos = new JTextField();
		p1.add(this.tf_termos, cons);
			
		cons.weightx = 0.25;
		this.locais = new JComboBox<Object>(modelo.getLocais());
		p1.add(this.locais, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		cons.weightx = 0;
		cons.ipadx = 40;
		this.bt_pesquisar = new JButton("Pesquisar", new ImageIcon(getClass().getResource("/icons/pesquisa.png")));
		p1.add(this.bt_pesquisar, cons);
	
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 1;
		cons.weightx = 1;	
		cons.ipadx = 0;
		cons.insets = new Insets(2, 1, 0, 0);
		this.tabela = new JTable( modelo);
		this.tabela.setRowHeight(20);
		
		try {this.tabela.setDefaultRenderer( Class.forName( "java.lang.String" ), new ConstrutorDeCelulaDeTabela(this.modelo.getTipo()));}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		
		
		scroll = new JScrollPane(this.tabela, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, cons);
		this.tabela.getParent().setBackground(new Color(221, 221, 221 ));	
		
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Comuns.COR_PRETO);
		headerRenderer.setForeground(Comuns.COR_BRANCO);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < this.tabela.getModel().getColumnCount(); i++){
		this.tabela.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
	}
	
	
	this.tabela.getTableHeader().setBorder(javax.swing.BorderFactory.createEtchedBorder());
	
	
    this.informacoes_de_tabela = new JLabel("");
    	if(this.modelo.paginacao){
    		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.insets = new Insets(2, 0, 0, -1);
			JPanel panel = new JPanel(){
			
			private static final long serialVersionUID = 1L;
	
			@Override   
	        public void paintComponent(Graphics g){    
	      
			
			Graphics2D g2 = (Graphics2D) g.create();	
	
			GradientPaint paint = new GradientPaint(  getWidth()/2, 0, new Color(79, 149, 209),  getWidth()/2,  getHeight(), Color.black);					
			g2.setPaint( paint);
			g2.fillRect( 0 , 0 ,  getWidth(),  getHeight());		
	        }};
		panel.setLayout(new GridBagLayout());
		this.add(panel, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.weighty  = 0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_prev = new JButton(new ImageIcon(getClass().getResource("/icons/anterior.png")));
		bt_prev.setToolTipText("Página Anterior");
		panel.add(bt_prev, cons);
		
		JButton bt_pri_page = new JButton(new ImageIcon(getClass().getResource("/icons/primeira_pag.png")));
		bt_pri_page.setToolTipText("Primeira Página");
		panel.add(bt_pri_page, cons);
		
		cons.insets = new Insets(0, 5, 0, 5);
		panel.add(informacoes_de_tabela, cons);
	
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_utl_page = new JButton(new ImageIcon(getClass().getResource("/icons/ultima_pag.png")));
		bt_utl_page.setToolTipText("Última Página");
		panel.add(bt_utl_page, cons);
		
		JButton bt_next = new JButton(new ImageIcon(getClass().getResource("/icons/proximo.png")));
		bt_next.setToolTipText("Próxima Página");
		panel.add(bt_next, cons);
    	
    	
		bt_prev.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		paginaAnterior();
		}});
		
			
			
		bt_pri_page.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		primeiraPagina();
		}});

			
			
		bt_utl_page.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		ultimaPagina();
		}});

			
			
		bt_next.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		proximaPagina();	
		}});
    	
    	}
	
    	
    	

		bt_pesquisar.addActionListener( new ActionListener(){
		public void actionPerformed( ActionEvent event ){
			    	
		pesquisa();
		}});
	
		
		
		this.tf_termos.addActionListener(new ActionListener (){
		@Override 	
		public void actionPerformed(ActionEvent e) {  
			
		pesquisa();
		}});
	
	}
	
	
	
	
	public void atualizar(){
	
		this.modelo.limpaPesquisa(this.locais.getSelectedItem().toString());	
		this.setInfoTable();
	}
	
	

	
	public void pesquisa() {
		
		this.modelo.pesquisa(this.tf_termos.getText(), this.locais.getSelectedItem().toString());	
		this.setInfoTable();
	}
	
	
	

	
	public void proximaPagina(){
		
	this.modelo.proximaPagina(this.tf_termos.getText(), this.locais.getSelectedItem().toString());	
	this.setInfoTable();
	}
		


	
		
	
	public void paginaAnterior(){
		
	this.modelo.paginaAnterior(this.tf_termos.getText(), this.locais.getSelectedItem().toString());	
	this.setInfoTable();
	}
			
		
		
	
	
	
	public void primeiraPagina(){
		
	this.modelo.primeiraPagina(this.tf_termos.getText(), this.locais.getSelectedItem().toString());	
	this.setInfoTable();
	}
				
	
	
	
		
		
	public void ultimaPagina(){
		
	this.modelo.ultimaPagina(this.tf_termos.getText(), this.locais.getSelectedItem().toString());	
	this.setInfoTable();
	}
	
	
	
	
	
	
	public void setInfoTable(){
	
	this.informacoes_de_tabela.setText("<html><font color=white><b>Mostrando "+this.modelo.currentIndex+" à "+(this.modelo.currentIndex+this.modelo.getRowCount())+" de "+this.modelo.countItens+" iten(s)</b></font></html>");	
	}
	
	
	
}		