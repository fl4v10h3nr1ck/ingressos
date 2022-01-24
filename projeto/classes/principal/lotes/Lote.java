package principal.lotes;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="lotes", prefixo="lte")
public class Lote {
		
@Anot_BD_Campo(nome = "id_lote", tipo=int.class, set = "setId_lote", get = "getId_lote", ehId=true)				
private int id_lote;

@Anot_BD_Campo(nome = "fk_subevento", tipo=int.class, set = "setFk_subevento", get = "getFk_subevento")
private int fk_subevento;

@Anot_BD_Campo(nome = "fk_tipo", tipo=int.class, set = "setFk_tipo", get = "getFk_tipo")
private int fk_tipo;

@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")
private String codigo;

@Anot_BD_Campo(nome = "data_limite_impressao", tipo=Date.class, set = "setData_limite_impressao", get = "getData_limite_impressao")
private Date data_limite_impressao;

@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco")
private String preco;

@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade")
private int quantidade;

@Anot_BD_Campo(nome = "tipo_preco", tipo=int.class, set = "setTipo_preco", get = "getTipo_preco")
private int tipo_preco;




public int getId_lote() {
	return id_lote;
}
public void setId_lote(int id_lote) {
	this.id_lote = id_lote;
}

public int getFk_subevento() {
	return fk_subevento;
}
public void setFk_subevento(int fk_subevento) {
	this.fk_subevento = fk_subevento;
}

public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public int getFk_tipo() {
	return fk_tipo;
}
public void setFk_tipo(int fk_tipo) {
	this.fk_tipo = fk_tipo;
}

public Date getData_limite_impressao() {
	return data_limite_impressao;
}
public void setData_limite_impressao(Date data_limite_impressao) {
	this.data_limite_impressao = data_limite_impressao;
}

public String getPreco() {
	return preco;
}
public void setPreco(String preco) {
	this.preco = preco;
}

public int getQuantidade() {
	return quantidade;
}
public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
}
public int getTipo_preco() {
	return tipo_preco;
}
public void setTipo_preco(int tipo_preco) {
	this.tipo_preco = tipo_preco;
}





	
}
