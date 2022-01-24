package principal.empresa;


import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="empresas", prefixo="emp")
public class Empresa {


@Anot_BD_Campo(nome = "id_empresa", tipo=int.class, set = "setId_empresa", get = "getId_empresa", ehId=true)			
private int id_empresa;

@Anot_BD_Campo(nome = "nome_razao", set = "setNome_razao", get = "getNome_razao")			
private String nome_razao;

@Anot_BD_Campo(nome = "cpf_cnpj", set = "setCpf_cnpj", get = "getCpf_cnpj")
private String cpf_cnpj;

@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;

@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "site", set = "setSite", get = "getSite")
private String site;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "logo", set = "setLogo", get = "getLogo")	
private String logo;


public int getId_empresa() {	return id_empresa;}
public void setId_empresa(int id_empresa) {	this.id_empresa = id_empresa;}

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

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getSite() {	return site;}
public void setSite(String site) {	this.site = site;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}



}
