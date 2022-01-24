package componentes.tabelas;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import comuns.Comuns;



public class ConstrutorDeCelulaDeTabela extends DefaultTableCellRenderer{

private static final long serialVersionUID = 1L;


@SuppressWarnings("unused")
private Class<?> tipoDeClasse;



	public ConstrutorDeCelulaDeTabela(Class<?> tipoDeClasse){
		
	this.tipoDeClasse = tipoDeClasse;
	}




	
	public Component getTableCellRendererComponent(JTable table,  
												   Object value,  
												   boolean isSelected,  
												   boolean hasFocus,  
												   int row,  
												   int column) {  	
		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);	
			
		if(cell instanceof DefaultTableCellRenderer){
			
			DefaultTableCellRenderer cellRender = (DefaultTableCellRenderer)cell;	
		
			cellRender.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
		
		
			boolean controle = false;
				for(int aux: this.getCentralizados()){
				
					if(column == aux){
					controle =  true;
					break;
					}
				}
					
			if(controle)
				cellRender.setHorizontalAlignment(SwingConstants.CENTER);
			else
				cellRender.setHorizontalAlignment(SwingConstants.LEFT);
		
			cell.setForeground(Comuns.COR_PRETO);
		
			if(row%2==0)
				cell.setBackground(Comuns.COR_BRANCO);
			else
				cell.setBackground(Comuns.COR_CINZA_CLARO);
		
			if(isSelected)
				cell.setBackground(Comuns.COR_SELECAO);
		}
		
		return cell;
	  }



	

	
	private int[] getCentralizados(){	
			
		//if(tipoDeClasse == Cliente.class)
		//	return new int[]{0, 2, 3, 4};
	
		return new int[0];	
	}

	
	
	
	
} 

