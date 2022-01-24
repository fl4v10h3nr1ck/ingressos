
package principal.relatorios;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import principal.lotes.Lote;
import comuns.Mensagens;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;



public class FabricaDeRelatorios {

	
//private Barra_De_Progresso progressBar;
	
	



	
	
	public FabricaDeRelatorios(){


	//this.progressBar = new Barra_De_Progresso();
	}
	
	
	
	
	public boolean erro(Exception e){
		
	//if(progressBar!=null)
	//progressBar.dispose();
	e.printStackTrace();
	Mensagens.msgDeErro("Um erro ocorreu ao gerar o relatório."); 
	return false;		
	}
	
	
	

/*
	private boolean criaRelatorios(String titulo, JasperReport jr, Map<String, Object> parametros, JRDataSource ds ){
	
	//if(this.progressBar != null)
	//progressBar.atualizar(4);	
		
		try{

		Empresa empresa= new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, null);
		
			if(empresa!=null){
		
				
			parametros.put("mostrar_cabecalho", empresa.getMostrar_endereco_docs()!=null && 	
													empresa.getMostrar_endereco_docs().compareTo("SIM")==0);
			parametros.put("nome_empresa", Comuns.getVazioSeNull(empresa.getNome_fantasia()));
			parametros.put("razao_empresa", Comuns.getVazioSeNull(empresa.getRazao_social()));
			parametros.put("endereco_empresa", Comuns.getVazioSeNull(empresa.getEndereco()));
			parametros.put("endereco_empresa_juridico", Comuns.getVazioSeNull(empresa.getEndereco_juridico()));
			parametros.put("cnpj_empresa", Comuns.getVazioSeNull(empresa.getCnpj()));
					
			String contatos = Comuns.getDescricaoContato(empresa.getTel_1(), empresa.getTel_2(), null, null);
				
			if(contatos.length()>0 && Comuns.temConteudo(empresa.getEmail()))
			contatos +="\n";
				
			contatos += Comuns.getVazioSeNull(empresa.getEmail());
					
			if(contatos.length()>0 && Comuns.temConteudo(empresa.getSite()))
			contatos +=" | ";
				
			contatos += Comuns.getVazioSeNull(empresa.getSite());
					
			parametros.put("contato_empresa", contatos);
		
			String logo = FormEmpresa.getLogo(empresa);
				
			parametros.put("logo_empresa", Comuns.temConteudo(logo) && new File(logo).exists()?logo:null);
			//parametros.put("marcadagua_empresa", Comuns.temConteudo(empresa.getMarcadagua_empresa()) && new File(empresa.getMarcadagua_empresa()).exists()?empresa.getMarcadagua_empresa():null);
			parametros.put("marcadagua_empresa", null);
			}
	
			
		JasperPrint jp  =null;
		if(ds==null)
		jp = JasperFillManager.fillReport(jr, parametros, Configuracoes.connector);
		else
		jp = JasperFillManager.fillReport(jr, parametros, ds);
		
		this.viewReportFrame( titulo, jp);
		}
		catch (Exception e) { return this.erro(e);}
		
	return true;
    }
	


	
	
	private void viewReportFrame( String titulo, JasperPrint print ) {
		
	if(this.progressBar != null)
	progressBar.atualizar(2);	
	
	
	JasperViewer viewer = new JasperViewer( print, false );
	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
	Rectangle screenRect = ge.getMaximumWindowBounds();  

	viewer.setSize(screenRect.width, screenRect.height);   	
	viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	viewer.setLocationRelativeTo(null);
	viewer.setTitle("Relatório");
	

		
		try {
		viewer.setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png" )));
		} 
		catch (IOException e) {this.erro(e); return;} 
	
	
	//viewer.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	
	if(this.progressBar != null)
	progressBar.atualizar(2);	
	
	progressBar.dispose();

	viewer.setVisible(true);
    }
	*/
	
	

	public void relatorioDeIngresso(Lote lote, List<ItemIngresso> lista){
		
		//if(base==null)
		//	return;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
	
		parametros.put("lote", lote.getCodigo());
		parametros.put("cont", lista.size());
		
		BaseIngresso base=  new BaseIngresso();
		base.setLista(lista);
		
		List<BaseIngresso> aux = new ArrayList<BaseIngresso>();
		aux.add(base);
		
		System.out.println("num de ingressos: "+lista.size());
		
		imprimiDireto("/relatorios/base", parametros, new JRBeanCollectionDataSource(aux));
	}
	
	
	
	
	
	private void imprimiDireto(String pathRelatorio, Map<String, Object> parametros, JRBeanCollectionDataSource ds){
		
		try{
		
			InputStream inputStream = getClass().getResourceAsStream(pathRelatorio+".jrxml");     
			
			JasperReport jr = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));
									   
			//JasperPrintManager.printReport(JasperFillManager.fillReport(jr, parametros, ds), false);
		
			JasperPrint jp = JasperFillManager.fillReport(jr, parametros, ds);
			viewReportFrame("", jp);
		}
		catch (Exception e) { this.erro(e);}
	}
	
	
	

	private void viewReportFrame( String titulo, JasperPrint print ) {
		
	
	JasperViewer viewer = new JasperViewer( print, false );
	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
	Rectangle screenRect = ge.getMaximumWindowBounds();  

	viewer.setSize(screenRect.width, screenRect.height);   	
	viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	viewer.setLocationRelativeTo(null);
	viewer.setTitle("Relatório");

	viewer.setVisible(true);
    }
	
	
	
	
}
