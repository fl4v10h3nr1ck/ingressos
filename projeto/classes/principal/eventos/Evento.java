package principal.eventos;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="eventos", prefixo="evt")
public class Evento {

	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=20)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)	
@Anot_BD_Campo(nome = "id_evento", tipo=int.class, set = "setId_evento", get = "getId_evento", ehId=true)				
private int id_evento;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=60)
@Anot_TB_Coluna(posicao=1, rotulo="Nome", comprimento = 25)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")				
private String nome;

@Anot_BD_Campo(nome = "logradouro", set = "setLogradouro", get = "getLogradouro")	
private String logradouro;

@Anot_BD_Campo(nome = "numero", set = "setNumero", get = "getNumero")	
private String numero;

@Anot_BD_Campo(nome = "cep", set = "setCep", get = "getCep")	
private String cep;

@Anot_BD_Campo(nome = "cidade", set = "setCidade", get = "getCidade")	
private String cidade;

@Anot_BD_Campo(nome = "bairro", set = "setBairro", get = "getBairro")	
private String bairro;

@Anot_BD_Campo(nome = "complemento", set = "setComplemento", get = "getComplemento")	
private String complemento;

@Anot_BD_Campo(nome = "uf", set = "setUf", get = "getUf")	
private String uf;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", tipo=int.class, set = "setStatus", get = "getStatus")
private int status;

@Anot_BD_Campo(nome = "fk_cliente", tipo=int.class, set = "setFk_cliente", get = "getFk_cliente")
private int fk_cliente;

@Anot_BD_Campo(nome = "tipo", tipo=int.class, set = "setTipo", get = "getTipo")
private int tipo;

@Anot_BD_Campo(nome = "logo", set = "setLogo", get = "getLogo")	
private String logo;

@Anot_BD_Campo(nome = "nome_local", set = "setNome_local", get = "getNome_local")	
private String nome_local;





public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

public String getLogradouro() {	return logradouro;}
public void setLogradouro(String logradouro) {	this.logradouro = logradouro;}

public String getCep() {	return cep;}
public void setCep(String cep) {	this.cep = cep;}

public String getCidade() {	return cidade;}
public void setCidade(String cidade) {	this.cidade = cidade;}

public String getBairro() {	return bairro;}
public void setBairro(String bairro) {	this.bairro = bairro;}

public String getComplemento() {	return complemento;}
public void setComplemento(String complemento) {	this.complemento = complemento;}

public String getUf() {	return uf;}
public void setUf(String uf) {	this.uf = uf;}

public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}

public int getId_evento() {
	return id_evento;
}
public void setId_evento(int id_evento) {
	this.id_evento = id_evento;
}

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

public int getFk_cliente() {
	return fk_cliente;
}
public void setFk_cliente(int fk_cliente) {
	this.fk_cliente = fk_cliente;
}

public int getTipo() {
	return tipo;
}
public void setTipo(int tipo) {
	this.tipo = tipo;
}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
public String getNome_local() {
	return nome_local;
}
public void setNome_local(String nome_local) {
	this.nome_local = nome_local;
}


	
}
