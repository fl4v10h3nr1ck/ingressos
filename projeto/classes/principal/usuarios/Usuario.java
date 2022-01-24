package principal.usuarios;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="usuarios", prefixo="usr")
public class Usuario {
	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=20)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 20)
@Anot_BD_Campo(nome = "id_usuario", tipo=int.class, set = "setId_usuario", get = "getId_usuario", ehId=true)				
private int id_usuario;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=80)
@Anot_TB_Coluna(posicao=1, rotulo="Nome", comprimento = 80)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")				
private String nome;

@Anot_BD_Campo(nome = "senha", set = "setSenha", get = "getSenha")
private String senha;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", tipo=int.class, set = "setStatus", get = "getStatus")
private int status;





public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

public int getId_usuario() {
	return id_usuario;
}
public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
}

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}



	
	
	
	
}
