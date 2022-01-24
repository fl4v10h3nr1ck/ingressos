package componentes.tabelas;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import comuns.Comuns;

public class TabelaPadrao extends JTable{

private static final long serialVersionUID = 1L;

	



	
	public TabelaPadrao(String[] rotulos_de_colunas, int[] comprimentos, int[] centralizados){
		
		super(new DefaultTableModel(null, rotulos_de_colunas){
	
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col ){  
			            
			return false;
		}});	
	
		this.setRowHeight(20); 
		
		for(int i= 0; i < comprimentos.length; i++)
			this.getColumnModel().getColumn(i).setPreferredWidth(comprimentos[i]);
	
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Comuns.COR_PRETO);
		headerRenderer.setForeground(Comuns.COR_BRANCO);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setFont(new Font("SansSerif", Font.BOLD, 11));
		
		for (int i = 0; i < this.getModel().getColumnCount(); i++)
			this.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		
		this.getTableHeader().setBorder(javax.swing.BorderFactory.createEtchedBorder());	
		
		try {this.setDefaultRenderer( Class.forName( "java.lang.Object" ), new ConstrutorDeCelulaDeTabela(centralizados));}
		catch (ClassNotFoundException e) {e.printStackTrace();}
	}
	
	
	
	
	
	public DefaultTableModel getModelo(){
		
		return (DefaultTableModel) this.getModel();	
	}
	
	
	
	
	

	public class ConstrutorDeCelulaDeTabela extends DefaultTableCellRenderer{

		private static final long serialVersionUID = 1L;

	
		private int[] centralizados;
	
	
		public ConstrutorDeCelulaDeTabela(int[] centralizados){
			
			super();
		
			this.centralizados  =centralizados;
		}
		
		public Component getTableCellRendererComponent(JTable table,  
													   Object value,  
													   boolean isSelected,  
													   boolean hasFocus,  
													   int row,  
													   int column) {  	
			
			Component cell = super.getTableCellRendererComponent
				           (table, value, isSelected, hasFocus, row, column);	
				
		    
			if(cell instanceof DefaultTableCellRenderer){
				
				DefaultTableCellRenderer cellRender = (DefaultTableCellRenderer)cell;	
			
				cellRender.setBorder(new CompoundBorder(cellRender.getBorder(), new EmptyBorder(0, 2, 0, 2)));  
				cellRender.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
			
				boolean controle = false;
					for(int aux: this.centralizados){
					
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
	} 


	
}
