package principal.clientes;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="clientes", prefixo="clt")
public class Cliente {
	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=15)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)	
@Anot_BD_Campo(nome = "id_cliente", tipo=int.class, set = "setId_cliente", get = "getId_cliente", ehId=true)				
private int id_cliente;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=55)
@Anot_TB_Coluna(posicao=1, rotulo="Nome/Razao Social", comprimento = 25)
@Anot_BD_Campo(nome = "nome_razao", set = "setNome_razao", get = "getNome_razao")				
private String nome_razao;

@Anot_TB_Coluna_Selecao(posicao=2, comprimento=30)
@Anot_TB_Coluna(posicao=2, rotulo="CPF/CNPJ", comprimento = 15)	
@Anot_BD_Campo(nome = "cpf_cnpj", set = "setCpf_cnpj", get = "getCpf_cnpj")
private String cpf_cnpj;

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

@Anot_TB_Coluna(posicao=3, rotulo="TEL", comprimento = 15)	
@Anot_BD_Campo(nome = "tel", set = "setTel", get = "getTel")
private String tel;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", tipo=int.class, set = "setStatus", get = "getStatus")
private int status;



public int getId_cliente() {	return id_cliente;}
public void setId_cliente(int id_cliente) {	this.id_cliente = id_cliente;}

public String getTel() {	return tel;}
public void setTel(String tel) {	this.tel = tel;}

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

public String getNome_razao() {
	return nome_razao;
}
public void setNome_razao(String nome_razao) {
	this.nome_razao = nome_razao;
}

public String getCpf_cnpj() {
	return cpf_cnpj;
}
public void setCpf_cnpj(String cpf_cnpj) {
	this.cpf_cnpj = cpf_cnpj;
}

public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}


	
	
	
	
}
