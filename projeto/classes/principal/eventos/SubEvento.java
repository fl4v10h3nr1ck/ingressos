package principal.eventos;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="subeventos", prefixo="svt")
public class SubEvento {
	
@Anot_BD_Campo(nome = "id_subevento", tipo=int.class, set = "setId_subevento", get = "getId_subevento", ehId=true)				
private int id_subevento;

@Anot_BD_Campo(nome = "fk_evento", tipo=int.class, set = "setFk_evento", get = "getFk_evento")
private int fk_evento;

@Anot_BD_Campo(nome = "data", tipo=Date.class, set = "setData", get = "getData")
private Date data;

@Anot_BD_Campo(nome = "hora_inicio", tipo=int.class, set = "setHora_inicio", get = "getHora_inicio")
private int hora_inicio;

@Anot_BD_Campo(nome = "min_inicio", tipo=int.class, set = "setMin_inicio", get = "getMin_inicio")
private int min_inicio;

@Anot_BD_Campo(nome = "hora_fim", tipo=int.class, set = "setHora_fim", get = "getHora_fim")
private int hora_fim;

@Anot_BD_Campo(nome = "min_fim", tipo=int.class, set = "setMin_fim", get = "getMin_fim")
private int min_fim;




public int getId_subevento() {
	return id_subevento;
}
public void setId_subevento(int id_subevento) {
	this.id_subevento = id_subevento;
}

public int getFk_evento() {
	return fk_evento;
}
public void setFk_evento(int fk_evento) {
	this.fk_evento = fk_evento;
}

public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}

public int getHora_inicio() {
	return hora_inicio;
}
public void setHora_inicio(int hora_inicio) {
	this.hora_inicio = hora_inicio;
}

public int getHora_fim() {
	return hora_fim;
}
public void setHora_fim(int hora_fim) {
	this.hora_fim = hora_fim;
}

public int getMin_inicio() {
	return min_inicio;
}
public void setMin_inicio(int min_inicio) {
	this.min_inicio = min_inicio;
}

public int getMin_fim() {
	return min_fim;
}
public void setMin_fim(int min_fim) {
	this.min_fim = min_fim;
}








}
