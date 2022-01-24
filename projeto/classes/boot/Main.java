
package boot;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import principal.ConfigDAO;
import principal.GestorDeBD;
import principal.Principal;
//import vocenaovaipassar.beans.UsuarioAtual;
//import vocenaovaipassar.geral.VoceNaoVaiPassar;
import comuns.Comuns;
import comuns.Configuracoes;




public class Main {

	
	
	
	public static void main(String[] args){
		
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}
	    catch (ClassNotFoundException e) {e.printStackTrace();}
	    catch (InstantiationException e) {e.printStackTrace();}
	    catch (IllegalAccessException e) {e.printStackTrace();}
	    catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}	
		
		Comuns.num_compilacao = "2018052900001";
		
		Comuns.software_code  = "4609";
		
		Comuns.nome_sistema = "ingressos";
	
		Comuns.versao_sistema = "1.0.0";
		
		Configuracoes.connector = new ConfigDAO(new Gestor(), Comuns.nome_sistema).getConexao();
		
		if(Configuracoes.connector==null)
			return;
	
		//VoceNaoVaiPassar gestorDeUsuarios = new VoceNaoVaiPassar(Configuracoes.connector);
					
		//if(gestorDeUsuarios.prepara()){
			
		//	boolean login = false;
					
		//	if(login)	
		//		Configuracoes.usuarioAtual = gestorDeUsuarios.login("dev", "devvc39mnapz98x");
		//	else{	
			
		//		Configuracoes.usuarioAtual = new 	UsuarioAtual();
		//		Configuracoes.usuarioAtual.setId(1);
		//		Configuracoes.usuarioAtual.setNome("admin");
		//	}
							
		//	if(Configuracoes.usuarioAtual!=null && Configuracoes.usuarioAtual.getId() > 0){
				
		
				Principal desktop = new Principal();	
				desktop.mostrar();
			//}	
		//}
	}	
	
	
	
	
	
	

	
	private static class Gestor implements GestorDeBD{

		@Override
		public String[] getListaDeCriacaoDeTabelas() {
		
			return new String[]{
			"CREATE TABLE  IF NOT EXISTS clientes (" +
			"id_cliente INT NOT NULL AUTO_INCREMENT,"+
			"nome_razao VARCHAR(150) NULL,"+
			"cpf_cnpj VARCHAR(45) NULL,"+
			"logradouro VARCHAR(150) NULL,"+
			"bairro VARCHAR(45) NULL,"+
			"numero VARCHAR(20) NULL,"+
			"cidade VARCHAR(150) NULL,"+
			"uf VARCHAR(5) NULL,"+
			"complemento VARCHAR(150) NULL,"+
			"cep VARCHAR(45) NULL,"+
			"tel VARCHAR(45) NULL,"+
			"data_cadastro DATE NULL,"+
			"status INT NULL,"+
			"PRIMARY KEY(id_cliente))ENGINE = InnoDB;",
							 
			"CREATE TABLE  IF NOT EXISTS eventos (" +
			"id_evento INT NOT NULL AUTO_INCREMENT,"+
			"fk_cliente INT NULL,"+
			"nome VARCHAR(45) NULL,"+
			" logradouro VARCHAR(250) NULL,"+
			"bairro VARCHAR(250) NULL,"+
			"numero VARCHAR(45) NULL,"+
			"cidade VARCHAR(250) NULL,"+
			"uf VARCHAR(5) NULL,"+
			"complemento VARCHAR(250) NULL,"+
			"cep VARCHAR(45) NULL,"+
			"tipo INT NULL,"+
			"status INT NULL,"+
			"data_cadastro DATE NULL,"+
			"logo VARCHAR(250) NULL,"+
			"nome_local VARCHAR(250) NULL,"+  
		    "PRIMARY KEY (id_evento)," + 
		    "INDEX fk_cliente_idx (fk_cliente ASC), "+
		    "CONSTRAINT fk_cliente FOREIGN KEY (fk_cliente) REFERENCES clientes(id_cliente) ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB;",
		                     
		    "CREATE TABLE  IF NOT EXISTS subeventos (" +
		    "id_subevento INT NOT NULL AUTO_INCREMENT, "+
		    "fk_evento INT NULL, "+
		    "data DATE NULL, "+
		    "hora_inicio INT NULL, "+
		    "hora_fim INT NULL, "+
		    "min_inicio INT NULL, "+
		    "min_fim INT NULL, "+  
			"PRIMARY KEY (id_subevento)," + 
			"INDEX fk_evento_subevento_idx (fk_evento ASC), "+
			"CONSTRAINT fk_evento_subevento FOREIGN KEY (fk_evento) REFERENCES eventos(id_evento) ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB;",
			
			"CREATE TABLE  IF NOT EXISTS entidades (" +
			"id_entidade INT NOT NULL AUTO_INCREMENT, "+
			"nome VARCHAR(250) NULL, "+
			"status INT NULL, "+
			"tipo INT NULL, "+
			"params VARCHAR(250) NULL, "+
			"PRIMARY KEY (id_entidade))ENGINE = InnoDB;",
					
			"CREATE TABLE  IF NOT EXISTS lotes (" +
			"id_lote INT NOT NULL AUTO_INCREMENT, "+
			"fk_subevento INT NULL, "+
			"fk_tipo INT NULL, "+
			"codigo VARCHAR(45) NULL, "+
			"data_limite_impressao DATE NULL, "+
			"preco VARCHAR(45) NULL, "+
			"quantidade INT NULL, "+
			"PRIMARY KEY (id_lote),"+
			"INDEX fk_subevento_idx (fk_subevento ASC), "+
			"INDEX fk_tipo_lotes_idx (fk_tipo ASC), "+
			"CONSTRAINT fk_subevento_lotes FOREIGN KEY (fk_subevento) REFERENCES subeventos(id_subevento) ON DELETE NO ACTION ON UPDATE NO ACTION,"+
			"CONSTRAINT fk_tipo_lotes FOREIGN KEY (fk_tipo) REFERENCES entidades(id_entidade) ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB;",

			"CREATE TABLE  IF NOT EXISTS ingressos (" +
			"id_ingresso INT NOT NULL AUTO_INCREMENT, "+
			"fk_lote INT NULL, "+
			"codigo VARCHAR(250) NULL, "+
			"usado INT NULL, "+
			"data_usado DATE NULL, "+
			"hora_usado INT NULL, "+
			"min_usado INT NULL, "+
			"PRIMARY KEY (id_ingresso),"+
			"INDEX fk_lote_idx (fk_lote ASC), "+
			"CONSTRAINT fk_lote FOREIGN KEY (fk_lote) REFERENCES lotes(id_lote) ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB;",
								        
			"CREATE TABLE  IF NOT EXISTS usuarios (" +
			"id_usuario INT NOT NULL AUTO_INCREMENT, "+
			"nome VARCHAR(45) NULL, "+
			"senha VARCHAR(45) NULL, "+
			"status INT NULL, "+
			"data_cadastro DATE NULL, "+
			"PRIMARY KEY (id_usuario))ENGINE = InnoDB;",
											        
			"CREATE TABLE  IF NOT EXISTS empresas (" +
			"id_empresa INT NOT NULL AUTO_INCREMENT, "+
			"codigo VARCHAR(45) NULL, "+
			"nome_razao VARCHAR(150) NULL, "+
			"cpf_cnpj VARCHAR(45) NULL, "+
			"liberada_ate DATE NULL, "+
			"status INT NULL, "+
			"tel_1 VARCHAR(45) NULL, "+
			"tel_2 VARCHAR(45) NULL, "+
			"site VARCHAR(250) NULL, "+
			"email VARCHAR(250) NULL, "+
			"logo VARCHAR(250) NULL, "+
			"PRIMARY KEY (id_empresa))ENGINE = InnoDB;"};
		}

		@Override
		public int getVersaoAtual() {return 2;}

		
		
		@Override
		public boolean atualizaBase(Connection conexao, int versao_antiga) {
			
			try{	
			
				Statement st = conexao.createStatement();
				
				/********************** cria tabelas iniciais ****************************/
			
				String[] tabelas = getListaDeCriacaoDeTabelas();
				
				if(tabelas!=null && tabelas.length>0){
					for(String query: tabelas)
						st.executeUpdate(query);
				}
				
				/********************** add valores padrao ****************************/
				
				if(versao_antiga<=1){
					
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Open Bar', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Vip', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Golden', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Mesas', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Prime', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Pista', '1', '7', NULL);");
					st.executeUpdate("INSERT INTO entidades (nome, status, tipo, params) VALUES ('Camarote', '1', '7', NULL);");
				}

				if(versao_antiga<2)
					st.executeUpdate("ALTER TABLE lotes ADD tipo_preco INT NULL;");
			}
			catch(SQLException erroSQL){
				erroSQL.printStackTrace();
				return false;
			}
		
		return true;
		}
	}
	
	
	
	
	
	
	
}
