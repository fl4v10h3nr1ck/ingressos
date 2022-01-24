package componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_TB_Coluna_Selecao;
import componentes.janelas.Dialogo;
import componentes.tabelas.MotorDePesquisaDeSelecao;




public class FormDeSelecao<T> extends Dialogo{

	

private static final long serialVersionUID = 1L;
	

public MotorDePesquisaDeSelecao<T> motorDePesquisa;

private Class<?> tipo;

public T retorno;



	public FormDeSelecao(String title, T retorno, Class<?> tipo, String where) {
	
	this(title, retorno, tipo, where, null, null);
	}

	
	public FormDeSelecao(String title, T retorno, Class<?> tipo, String where, String termos) {
		
	this(title, retorno, tipo, where, termos, null);
	}
	
		
	public FormDeSelecao(String title, T retorno, Class<?> tipo, String where, String termos, String orderby) {
	
	super(title, 550, 550);

	this.retorno = retorno;
	
	this.tipo = tipo;
	
	this.motorDePesquisa = new MotorDePesquisaDeSelecao<T>(title, this.tipo);
	this.motorDePesquisa.tableModel.setWhere(where);
	this.motorDePesquisa.tableModel.setOrderBy(orderby);
	
	adicionarComponentes();
	
	if(termos!=null)
	this.motorDePesquisa.tf_termos.setText(termos);
	
	
	this.motorDePesquisa.search();
	}

	
	
	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();   	
		
	this.setLayout(new GridBagLayout());
	    
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.insets = new Insets(1, 1, 1, 1);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.add(this.motorDePesquisa, cons);

	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 4, 2, 2);
	this.add(new JLabel("<html><font color=white>*Duplo clique na linha desejada para adicioná-la.</font></html>"),cons);
	

	
		this.motorDePesquisa.table.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
		
			if(e.getClickCount()==2){	
		
			if(acaoPrincipal())
			dispose();
			
			}	
		}}); 	
		
		
		
		
		this.motorDePesquisa.table.addKeyListener(new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent arg0) {
					
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
							
			if(acaoPrincipal())
			dispose();
			}
						
		}});	
		
		
		this.motorDePesquisa.tf_termos.addKeyListener(new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent arg0) {
					
				
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){	
									
			if(acaoPrincipal())
			acaoPrincipal();
			}	
				
			
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
		motorDePesquisa.table.addRowSelectionInterval(0, 0);
		}});

	

		this.motorDePesquisa.tf_termos.getDocument().addDocumentListener( new DocumentListener() {  
        
		@Override
		public void insertUpdate(DocumentEvent e) {
				
			motorDePesquisa.search() ;
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			
			motorDePesquisa.search() ;
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
		}});  

	
	}




	@Override
	public boolean acaoPrincipal() {	
		
	T selecionado =  (T) motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
	
		if(selecionado != null){
		
			for(Field aux: selecionado.getClass().getDeclaredFields()){
					
				if(aux.isAnnotationPresent(Anot_BD_Campo.class) && 
						(aux.isAnnotationPresent(Anot_TB_Coluna_Selecao.class) ||
								aux.getAnnotation(Anot_BD_Campo.class).ehId())){
					
					try {
						retorno.getClass().getDeclaredMethod(aux.getAnnotation(Anot_BD_Campo.class).set(), aux.getAnnotation(Anot_BD_Campo.class).tipo()).
									invoke(retorno, 
										selecionado.getClass().getDeclaredMethod(aux.getAnnotation(Anot_BD_Campo.class).get()).invoke(selecionado));
					} 
					catch (IllegalAccessException |  
							IllegalArgumentException |
							InvocationTargetException |
							NoSuchMethodException |
							SecurityException e1) {e1.printStackTrace();}
				}
			}

		return true;
		}
	
	return false;	
	}
	
	

}
