package principal.entidades;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="entidades", prefixo="etd")
public class Entidade {

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=20)	
@Anot_BD_Campo(nome = "id_entidade", tipo=int.class, set = "setId_entidade", get = "getId_entidade", ehId=true)				
private int id_entidade;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=80)
@Anot_TB_Coluna(posicao=0, rotulo="Nome", comprimento = 100)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")
private String nome;

@Anot_BD_Campo(nome = "status", tipo=int.class, set = "setStatus", get = "getStatus")
private int status;

@Anot_BD_Campo(nome = "tipo", tipo=int.class, set = "setTipo", get = "getTipo")
private int tipo;

@Anot_BD_Campo(nome = "params", set = "setParams", get = "getParams")
private String params;




public int getId_entidade() {
	return id_entidade;
}
public void setId_entidade(int id_entidade) {
	this.id_entidade = id_entidade;
}

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

public int getTipo() {
	return tipo;
}
public void setTipo(int tipo) {
	this.tipo = tipo;
}

public String getParams() {
	return params;
}
public void setParams(String params) {
	this.params = params;
}




	
}
