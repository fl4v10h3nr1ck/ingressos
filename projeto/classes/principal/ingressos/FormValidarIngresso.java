package principal.ingressos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import CamposDeTexto.campos.CampoLimitadoSoDigito;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Data;
import dao.DAO;
import principal.entidades.Entidade;
import principal.eventos.Evento;
import principal.eventos.SubEvento;
import principal.ingressos.Ingresso;
import principal.lotes.Lote;



public class FormValidarIngresso extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoLimitadoSoDigito tf_codigo;

private 	JPanel p_info;




	public FormValidarIngresso() {
		
		super("Validar Ingresso", 600, 400);
		
		adicionarComponentes();
	}
	

	
	
	
	
	public void adicionarComponentes(){
	
		GridBagConstraints cons = new GridBagConstraints();  
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 2, 2);
			
		JPanel p1 = new JPanel(new GridBagLayout());
		this.add(p1, cons);
		
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty  = 1;	
		cons.insets = new Insets(0, 0, 0, 0);
		
		this.p_info = new JPanel(new GridBagLayout());
		this.p_info.setBackground(Comuns.COR_CINZA_CLARO);
		this.add(p_info, cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Código do Ingresso:<font color=red>*</font></html>"), cons);
		
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_codigo = new CampoLimitadoSoDigito(12), cons);
		
		
		this.tf_codigo.getDocument().addDocumentListener( new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) { }
            
			public void insertUpdate(DocumentEvent e) { 
			
				acaoPrincipal();
			}
          
			public void removeUpdate(DocumentEvent e) { }
		});
		
		
		
	}
	
	
	


	
	


	@Override
	public boolean acaoPrincipal() {
		
		this.p_info.removeAll();
		
		if(this.tf_codigo.getText().length()==12){
			
			Ingresso ingresso = new DAO<Ingresso>(Ingresso.class).getPrimeiroOuNada(null, "igs.codigo='"+this.tf_codigo.getText()+"'", null);
			
			GridBagConstraints cons = new GridBagConstraints();  
			
			cons.fill = GridBagConstraints.HORIZONTAL;
			cons.gridwidth  = GridBagConstraints.REMAINDER;	
			cons.weighty  = 0;
			cons.weightx = 1;
			cons.insets = new Insets(5, 10, 5, 10);
			
			if(ingresso==null){
					
				this.p_info.add(new JLabel("<html>Código do Ingresso: "+this.tf_codigo.getText()+"</html>"), cons);
					
				this.p_info.add(new JLabel("<html><font color=red><b>INGRESSO INVÁLIDO!</b></font></html>", SwingConstants.CENTER), cons);
				
				this.p_info.add(new JLabel("<html>Este código não corresponde a nenhum ingresso</html>"), cons);	
			}
			else{
					
				Lote lote = new DAO<Lote>(Lote.class).get(ingresso.getFk_lote());
					
				if(ingresso.getUsado()>0){
						
					this.p_info.add(new JLabel("<html>Código do Ingresso: "+ingresso.getCodigo()+"</html>"), cons);
						
					this.p_info.add(new JLabel("<html><font color=red><b>INGRESSO JÁ FOI USADO E VALIDADO!</b></font></html>", SwingConstants.CENTER), cons);
						
					this.p_info.add(new JLabel("<html>Usado e validado em: "+Data.converteDataParaString(ingresso.getData_usado())+" "+
																									Comuns.addPaddingAEsquerda(ingresso.getHora_usado()+"", 2, "0")+":"+
																									Comuns.addPaddingAEsquerda(ingresso.getMin_usado()+"", 2, "0")+"</html>"), cons);
					
					this.p_info.add(new JLabel("<html>Este ingresso não é mais válido</html>"), cons);	
				}
				else{
					
					this.p_info.add(new JLabel("<html>Código do Ingresso: "+ingresso.getCodigo()+"</html>"), cons);
						
					this.p_info.add(new JLabel("<html><font color=green><b>INGRESSO VÁLIDO!</b></font></html>", SwingConstants.CENTER), cons);
						
					ingresso.setUsado(1);
					ingresso.setData_usado(new Date());
					ingresso.setHora_usado(Data.getHoraAtual());
					ingresso.setMin_usado(Data.getMinutoAtual());
						
					new DAO<Ingresso>(Ingresso.class).altera(ingresso);
				}
					
				if(lote!=null){
						
					this.p_info.add(new JSeparator(JSeparator.HORIZONTAL), cons);
						
					this.p_info.add(new JLabel("<html>Lote: "+lote.getCodigo()+"</html>"), cons);
					
					Entidade tipo = new DAO<Entidade>(Entidade.class).get(lote.getFk_tipo());
										
					this.p_info.add(new JLabel("<html>Tipo: "+(tipo!=null?tipo.getNome():"")+"</html>"), cons);
						
					this.p_info.add(new JLabel("<html>Preço (R$): "+lote.getPreco()+"</html>"), cons);
						
					this.p_info.add(new JSeparator(JSeparator.HORIZONTAL), cons);
	
					SubEvento subevento = new DAO<SubEvento>(SubEvento.class).get(lote.getFk_subevento());
						
					if(subevento!=null){
							
						Evento evento = new DAO<Evento>(Evento.class).get(subevento.getFk_evento());
							
						if(evento!=null){
									
							this.p_info.add(new JLabel("<html>Evento: "+evento.getNome()+"</html>"), cons);
							
							this.p_info.add(new JLabel("<html>Data: "+Data.converteDataParaString(subevento.getData())+" "+
																				Comuns.addPaddingAEsquerda(subevento.getHora_inicio()+"", 2, "0")+":"+
																				Comuns.addPaddingAEsquerda(subevento.getMin_inicio()+"", 2, "0")+" às "+
																				Comuns.addPaddingAEsquerda(subevento.getHora_fim()+"", 2, "0")+":"+
																				Comuns.addPaddingAEsquerda(subevento.getMin_fim()+"", 2, "0")+"</html>"), cons);
						}
						else
							this.p_info.add(new JLabel("<html>Evento indefinido</html>"), cons);
					}
					else
						this.p_info.add(new JLabel("<html>Evento indefinido</html>"), cons);
				}
			}
			
			cons.fill = GridBagConstraints.BOTH;
			cons.weighty  = 1;	
			cons.insets = new Insets(0, 0, 0, 0);
			this.p_info.add(new JLabel(""), cons);
			
			new Thread() {
		         
		        @Override
		        public void run() {
		           
		        	tf_codigo.setText("");
		        }
		    }.start();
		}
			
		this.p_info.revalidate();
		this.p_info.repaint();	
			
		return false;
	}
	
	

	
}
