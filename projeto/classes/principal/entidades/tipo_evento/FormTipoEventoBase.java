package principal.entidades.tipo_evento;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import CamposDeTexto.campos.CampoLimitado;
import componentes.janelas.Dialogo;
import comuns.Mensagens;
import principal.entidades.Entidade;




public abstract class FormTipoEventoBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;


protected Entidade entidade;
	



	public FormTipoEventoBase() {
		
		this(null);
	}

	
	
	
	
	
	public FormTipoEventoBase(Entidade entidade) {
		
		super("Cadastro de Setor de Evento", 750, 150);
	
		this.entidade = entidade;
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
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		cons.weighty  = 0;
		cons.weightx = 1;
		cons.insets = new Insets(2, 2, 0, 2);
		p1.add(new JLabel("<html>Nome do Setor:<font color=red>*</font></html>"), cons);
	
		cons.insets = new Insets(0, 2, 2, 2);
		p1.add(this.tf_nome = new CampoLimitado(200), cons);
		
		
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.anchor = GridBagConstraints.EAST;
		cons.ipadx = 25;
		JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
		bt_salvar.setToolTipText("Salvar Setor de Evento.");
		this.add(bt_salvar, cons);
			
		
			bt_salvar.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
				if(acaoPrincipal()){	
						
				Mensagens.msgDeSucessoAoSalvar();
				dispose();
				}	
			}});
	}
	
	
	
	
	
	protected boolean validacao(){
	
		if(this.tf_nome.getText().length() == 0){
		
			Mensagens.msgDeErro("Informe o nome  do tipo de evento.");
			this.tf_nome.ativaModoDeErro();
			return false;	
		}
		
		return true;
	}
	
	


	
}
