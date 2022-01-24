package componentes.tabelas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Field;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import anotacoes.Anot_TB_Coluna_Selecao;




public class MotorDePesquisaDeSelecao<T> extends JPanel{


	

private static final long serialVersionUID = 1L;

		

public JTextField tf_termos;

public JTable table;
public ModeloDeTabelaDeSelecao<T> tableModel;
	
private String titulo;

public JScrollPane scroll;

@SuppressWarnings("unused")
private  Class<?> tipoDeClasse;



	public MotorDePesquisaDeSelecao(String titulo, Class<?> tipoDeClasse){
		
	this.titulo = titulo;
	this.setLayout(new GridBagLayout());
	this.setBackground(Color.white);

	this.tipoDeClasse=  tipoDeClasse;
	
	this.tableModel = new ModeloDeTabelaDeSelecao<T>(tipoDeClasse, false);
	this.adicionarComponentes();
		
		for (Field field : tipoDeClasse.getDeclaredFields()) {
			
		if (field.isAnnotationPresent(Anot_TB_Coluna_Selecao.class))
		this.table.getColumnModel().getColumn(field.getAnnotation(Anot_TB_Coluna_Selecao.class).posicao()).
				setPreferredWidth(field.getAnnotation(Anot_TB_Coluna_Selecao.class).comprimento()*(550/100));	
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
	
		cons.insets = new Insets(2, 2, 0, 2);
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setBackground(Color.WHITE);
		this.add(p1, cons);
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		p1.add(new JLabel("Termos de Busca:"), cons);
		
		cons.insets = new Insets(2, 2, 2, 2);
		this.tf_termos = new JTextField();
		p1.add(this.tf_termos, cons);
	
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty  = 1;
		cons.weightx = 1;	
		cons.ipadx = 0;
		cons.insets = new Insets(2, 1, 0, 0);
		this.table = new JTable( tableModel);
		this.table.setRowHeight(25);
		
		scroll = new JScrollPane(this.table, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, cons);
		this.table.getParent().setBackground(new Color(221, 221, 221 ));	
		
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);	
	}
	
	
	
	
	
	public void update(){
			
			this.tableModel.atualiza();	
	}
	
	
	
	
	
	public void search() {
		
			this.tableModel.atualiza(this.tf_termos.getText(), null);	
	}
	
		
}		