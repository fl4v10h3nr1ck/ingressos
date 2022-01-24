
package comuns;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;





public class Comuns {


	
/******************* comuns de formularios ***********************/

	
public static final String[] ufs = {"...", "PA", "AC", "AL", "AP", "AM",  "BA", "CE",  "DF",  "ES",  "GO",  "MA",  "MT",  "MS",  "MG", "PB",  "PR",  "PE",  "RJ",  "RJ",  "RN",  "RS", "RO",  "RR",  "SC",  "SP",  "SE",  "TO"};
	
public static final int TIPO_DIA_UNICO = 1;
public static final int TIPO_VARIOS_DIAS_INGRESSO_UNICO = 2;
public static final int TIPO_VARIOS_DIAS_VARIOS_INGRESSOS = 3;

public static final int ETD_TIPO_INGRESSOS = 7;

public static String num_compilacao;
public static String nome_sistema;
public static String versao_sistema;
public static String software_code;



/******************* comuns de configuracao ***********************/


public static final int NUM_MAX_ITENS_TAB = 25;


/******************* cores ***********************/

public static final Color COR_BRANCO = Color.white;
public static final Color COR_PRETO = Color.black;
public static final Color COR_CINZA_CLARO = new Color(211,211,211);
public static final Color COR_SELECAO = new Color(43,87,211);

	public static String getVazioSeNull(String valor){
		
	return valor==null?"":valor;
	}
	
	
	
	
	
	public static boolean temConteudo(String valor){
		
	return valor!=null && valor.length()>0;
	}
		
		
	
	
	public static String selecionaArquivoDeImg(){
		

	JFileChooser fc = new JFileChooser();	
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
		fc.setFileFilter(new javax.swing.filechooser.FileFilter(){
				      
			public boolean accept(File f){
				    
			if (f.isDirectory()) {return true;}	
				
			
			return f.getName().toLowerCase().endsWith(".jpg") ||
					f.getName().toLowerCase().endsWith(".png") ||
					f.getName().toLowerCase().endsWith(".jpeg");
			}

			public String getDescription() {
				
			return "Arquivos de Imagens (jpg, png ou jpeg)";
			}
		});
				
	int returnVal = fc.showOpenDialog(null);

	if (returnVal == JFileChooser.APPROVE_OPTION)
	return fc.getSelectedFile().getAbsolutePath();	
	
	return null;
	}
	
	
	
	

	public static String addNovaLogo(String logo_atual, String logo_nova){

		String nome_temp	 =null;
		
		if(	(logo_atual==null && Comuns.temConteudo(logo_nova)) || 		
				(logo_atual!=null && 
				Comuns.temConteudo(logo_nova) && 
				logo_nova.compareTo(logo_atual)!=0)){
			
			if(Comuns.temConteudo(logo_nova)){	
			
				nome_temp = "lg"+
						Data.getDataAtual("")+
							Comuns.addPaddingAEsquerda(""+new Random().nextInt(1000000), 7, "0");	
			
				String extensao = Comuns.getextensaoDeArquivo(logo_nova);
				
				if(extensao==null){
						
					Mensagens.msgDeErro("O arquivo selecionado para logotipo é inválido.");	
					return null;
				}
			
				nome_temp += "."+extensao;
				
				if((nome_temp=Comuns.copiaArquivos(new File(logo_nova), nome_temp))==null){
						
					Mensagens.msgDeErro("Um erro ocorreu ao copiar a referência do arquivo de logotipo.");	
					return null;	
				}
			}
		}
		
		if(Comuns.temConteudo(logo_atual)){
			
			File antigo = new File(logo_atual);			
			if(antigo.exists())
				antigo.delete();
		}
			
		return nome_temp;
	}
	
	
	
	

	public static String getextensaoDeArquivo(String nome){
		
		if(nome.contains(".")){
			
			String[] aux = nome.split(Pattern.quote("."));	
			
			if(aux.length>0)
			return aux[aux.length-1].toLowerCase();
			
			return null;
		}
				
	return null;	
	}
	
	
	
	
	
	
	@SuppressWarnings("resource")
	public static String copiaArquivos(File novo, String nome_novo){
	

		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
        
    	try {
            
			sourceChannel = new FileInputStream(novo).getChannel();
			
			String path  = System.getProperty("user.dir")+"\\ingresso_midia\\"; 
			
			File aux = new File(path);			
			if(!aux.exists())
				aux.mkdirs();
			
			nome_novo = path+nome_novo;
			
	        destinationChannel = new FileOutputStream(new File(nome_novo)).getChannel();
	            
	        sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
	        
	        if (sourceChannel != null && sourceChannel.isOpen())
	        	sourceChannel.close();
	        
	        if (destinationChannel != null && destinationChannel.isOpen())
	        	destinationChannel.close();
	        
        	return nome_novo;
        } 
        catch (IOException  e) {
		e.printStackTrace();
		return null;
        }	
	}
	
	
	
	
	
	

	public static String addPaddingAEsquerda(String valor, int num, String add){
		
	
	for(int i  = valor.length(); i < num; i++)
	valor = add+valor;
		
	return valor;	
	}
	
	
	

	
	public static String maiuscula(String valor){
		
		return valor.toUpperCase(new Locale("pt", "BR"));		
	}
	
	

	
	
	
	public static String getDescricaoEndereco(String logradouro, 
												String num, 
												String complemento, 
												String bairro, 
												String cidade,  
												String uf, 
												String pais, 
												String cep, 
												boolean maiusculo){
		
	String retorno = getVazioSeNull(logradouro);
	
	if(temConteudo(retorno))
	retorno += " ";
	
	if(temConteudo(num))
	retorno += num;
	else
	retorno += "S/N";
	
	if(temConteudo(retorno) && temConteudo(complemento))
	retorno += " ";
	
	retorno += getVazioSeNull(complemento);
	
	if(temConteudo(retorno))
	retorno += ".";
		
	if(temConteudo(bairro))
	retorno += " "+bairro;
	
	if(temConteudo(retorno) && temConteudo(cidade))
	retorno += " ";
	
	retorno += getVazioSeNull(cidade);
	
	if(temConteudo(retorno) && temConteudo(uf))
	retorno += " ";
		
	retorno += getVazioSeNull(uf);
	
	if(temConteudo(retorno) && temConteudo(pais))
	retorno += " ";
			
	retorno += getVazioSeNull(pais);	
	
	if(temConteudo(retorno) && temConteudo(cep))
	retorno += " ";
	
	retorno += getVazioSeNull(cep);
	
				
	return maiusculo?maiuscula(retorno):"";
	}
	
	
	
	
	
	public static String getDescricaoContato(String tel1, 
											String tel2, 
											String tel3, 
											String tel4){
		
	String contatos = Comuns.getVazioSeNull(tel1);
		
	if(contatos.length()>0 && Comuns.temConteudo(tel2))
	contatos +=" | ";	
		
	contatos += Comuns.getVazioSeNull(tel2);
	
	if(contatos.length()>0 && Comuns.temConteudo(tel3))
	contatos +=" | ";	
			
	contatos += Comuns.getVazioSeNull(tel3);
		
	if(contatos.length()>0 && Comuns.temConteudo(tel4))
	contatos +=" | ";	
			
	contatos += Comuns.getVazioSeNull(tel4);
		
	return contatos;
	}
	
	
	
}
