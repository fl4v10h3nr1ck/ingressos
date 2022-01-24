package principal;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.tabelas.MotorDePesquisa;




public abstract class Recurso<T>{

	
	
protected JPanel painel_de_opcoes;
protected JPanel painel_de_outros_itens;
protected JPanel painel_de_documentos;
protected JPanel painel_principal;


public MotorDePesquisa<T> motor;	


	
protected JButton bt_novo;
protected JButton bt_alterar;
protected JButton bt_deletar;

public Class<?> tipoDeClasse;


private String titulo_guia; 





	public Recurso( String titulo_janela, Class<?> tipoDeClasse){
			

	this(titulo_janela, "Opções", tipoDeClasse);
	}


	
	

	public Recurso( String titulo_janela, 
			String titulo_guia, 
			Class<?> tipoDeClasse){
			
		this.tipoDeClasse = tipoDeClasse;
		
		
		this.titulo_guia = titulo_guia;
		
	
		this.motor = new MotorDePesquisa<T>(titulo_janela, tipoDeClasse);
		
		this.adicionarComponentes();
	}
	
	

	
	
	
	public void adicionarComponentes(){	
	
		this.painel_principal = new JPanel(new GridBagLayout());
				
		this.painel_de_opcoes = new JPanel(new GridBagLayout());
		this.painel_de_outros_itens = new JPanel(new GridBagLayout());
		this.painel_de_documentos = new JPanel(new GridBagLayout());

		painel_de_opcoes.setBorder(BorderFactory.createTitledBorder(this.titulo_guia));  
		
		GridBagConstraints cons = new GridBagConstraints();  
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.weighty  = 0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 0, 0, 0);	
		this.painel_principal.add(this.painel_de_opcoes, cons);	
		this.painel_principal.add(this.painel_de_outros_itens, cons);	
		this.painel_principal.add(this.painel_de_documentos, cons);	

		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		this.painel_principal.add(new JLabel(""), cons);

		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(0, 0, 0, 0);

		cons.fill = GridBagConstraints.BOTH;
		cons.weighty  = 1;
		
		this.painel_principal.add(this.motor, cons);
			
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 0, 5);
		this.bt_novo = new JButton(new ImageIcon(getClass().getResource("/icons/novo.png")));
		bt_novo.setToolTipText("Adicionar novo");
		this.painel_de_opcoes.add(bt_novo, cons);
		
		this.bt_alterar = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
		bt_alterar.setToolTipText("Alterar item selecionado na tabela");
		this.painel_de_opcoes.add(bt_alterar, cons);
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		this.bt_deletar = new JButton(new ImageIcon(getClass().getResource("/icons/remover.png")));
		bt_deletar.setToolTipText("Excluir item selecionado na tabela");
		this.painel_de_opcoes.add(bt_deletar, cons);
		
		cons.gridwidth = 1;
		cons.insets = new Insets(0, 5, 0, 5);
		this.painel_de_opcoes.add(new JLabel("Novo") , cons);
		
		this.painel_de_opcoes.add(new JLabel("Editar") , cons);
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		this.painel_de_opcoes.add(new JLabel("Excluir") , cons);

			bt_novo.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
			    	
			novo();	
			motor.atualizar();
			}});

			bt_alterar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				
			alterar();
			}});	

			bt_deletar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				  
			deletar();
			}});		
				
			this.motor.tabela.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
					
			if(e.getClickCount()==2)
			alterar();
					
			}}); 
					
		motor.atualizar();
	}
	
	
	
	
	public abstract void novo();
	
	
	
	
	public abstract void alterar();
	
	
	
	
	public abstract void deletar();
	
	
	

	public void gerar(){
		
		adicionarComponentes();	
	}



	
	public JPanel getComponentesDeRecurso(){

		return this.painel_principal;	
	}
	
	
	
		
}

