
package comuns;

import javax.swing.JOptionPane;





public class Mensagens {


	



	public static void msgDeErroAoSalvar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao salvar as informações.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}


	
	public static void msgDeErroAoDeletar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao excluir as informações.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}

	
	
	public static void msgDeErroAoAlterar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao alterar as informações.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}

	
	
	public static void msgDeErroAoObter(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao recuperar as informações.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	
	public static void msgDeSucessoAoSalvar(){
		
	JOptionPane.showMessageDialog(null, "Informações salvas com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	
	

	public static void msgDeSucessoAoAlterar(){
		
	JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	

	public static void msgDeSucessoAoDeletar(){
		
	JOptionPane.showMessageDialog(null, "Remoção realizada com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	
	public static void msgDeRelatorioGeradoComSucesso(){
		
	JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	
	
	public static void msgDeErro(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	
	public static void msgDeSucesso(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	
	
	
	public static void msgDeAlerta(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "Atenção", JOptionPane.WARNING_MESSAGE);	
	}
		
	
	
	
	public static boolean dialogoDeConfirmacao(String msg){
	
	int dialogButton = JOptionPane.YES_NO_OPTION;
	int dialogResult = JOptionPane.showConfirmDialog(null, msg, "Confirmação",dialogButton);
	
	return dialogResult==0? true:false;
	}
	
	
	
}	