package principal.ingressos;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="ingressos", prefixo="igs")
public class Ingresso {
		
@Anot_BD_Campo(nome = "id_ingresso", tipo=int.class, set = "setId_ingresso", get = "getId_ingresso", ehId=true)				
private int id_ingresso;

@Anot_BD_Campo(nome = "fk_lote", tipo=int.class, set = "setFk_lote", get = "getFk_lote")
private int fk_lote;

@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")
private String codigo;

@Anot_BD_Campo(nome = "usado", tipo=int.class, set = "setUsado", get = "getUsado")				
private int usado;

@Anot_BD_Campo(nome = "data_usado", tipo=Date.class, set = "setData_usado", get = "getData_usado")
private Date data_usado;

@Anot_BD_Campo(nome = "hora_usado", tipo=int.class, set = "setHora_usado", get = "getHora_usado")
private int hora_usado;

@Anot_BD_Campo(nome = "min_usado", tipo=int.class, set = "setMin_usado", get = "getMin_usado")
private int min_usado;



public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public int getId_ingresso() {
	return id_ingresso;
}
public void setId_ingresso(int id_ingresso) {
	this.id_ingresso = id_ingresso;
}

public int getFk_lote() {
	return fk_lote;
}
public void setFk_lote(int fk_lote) {
	this.fk_lote = fk_lote;
}
public int getUsado() {
	return usado;
}
public void setUsado(int usado) {
	this.usado = usado;
}
public Date getData_usado() {
	return data_usado;
}
public void setData_usado(Date data_usado) {
	this.data_usado = data_usado;
}
public int getHora_usado() {
	return hora_usado;
}
public void setHora_usado(int hora_usado) {
	this.hora_usado = hora_usado;
}
public int getMin_usado() {
	return min_usado;
}
public void setMin_usado(int min_usado) {
	this.min_usado = min_usado;
}



	
}
