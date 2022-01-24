

package principal.eventos;

import java.util.Date;

import comuns.Comuns;
import comuns.Data;
import dao.DAO;





public class FormNovoEvento extends FormEventoBase{


private static final long serialVersionUID = 1L;



	public FormNovoEvento(){
		
		this(null);
	}
	
	
	
	
	public FormNovoEvento(Evento evento){
		
		super(evento);	
		
		adicionarComponentes();
	}
	
	

	
	public boolean acaoPrincipal(){
		
		if(!validacao())
			return false;
		
		if(this.evento==null)
			this.evento = new Evento();
	
		evento.setNome(tf_nome.getText());
		evento.setFk_cliente(this.cliente.getId_cliente());
		
		if(this.tipo_dia_unico.isSelected())
			evento.setTipo(Comuns.TIPO_DIA_UNICO);
		else if(this.tipo_varios_dias_um_ingresso.isSelected())
			evento.setTipo(Comuns.TIPO_VARIOS_DIAS_INGRESSO_UNICO);
		else if(this.tipo_varios_dias_varios_ingressos.isSelected())
			evento.setTipo(Comuns.TIPO_VARIOS_DIAS_VARIOS_INGRESSOS);
		
		evento.setNome_local(tf_local.getText());
		evento.setLogradouro(tf_logradouro.getText());
		evento.setNumero(tf_num.getText());
		evento.setBairro(tf_bairro.getText());
		evento.setCidade(tf_cidade.getText());
		evento.setCep(tf_cep.getText());
		evento.setUf(cb_uf.getSelectedItem().toString());
		evento.setComplemento(tf_complemento.getText());
		
		evento.setLogo(Comuns.addNovaLogo(evento.getLogo(), this.tf_logo.getText()));
		
		evento.setStatus(1);
		evento.setData_cadastro(new Date());
	
		int id_evento = new DAO<Evento>(Evento.class).novo(evento);
			
		if(id_evento>0){
					
			evento.setId_evento(id_evento);
			
			if(evento.getTipo() == Comuns.TIPO_DIA_UNICO){
				
				SubEvento aux = new SubEvento();
				aux.setData(Data.converteStringParaData(this.tf_data.getText()));
				aux.setHora_inicio(Integer.parseInt(this.tf_hora_inicio.getText()));
				aux.setMin_inicio(Integer.parseInt(this.tf_min_inicio.getText().length()>0?this.tf_min_inicio.getText():"0"));
				aux.setHora_fim(Integer.parseInt(this.tf_hora_fim.getText()));
				aux.setMin_fim(Integer.parseInt(this.tf_min_fim.getText().length()>0?this.tf_min_fim.getText():"0"));
				aux.setFk_evento(evento.getId_evento());
				new DAO<SubEvento>(SubEvento.class).novo(aux);
			}
			else{
				for(SubEvento aux: this.subeventos){
				
					aux.setFk_evento(evento.getId_evento());
					new DAO<SubEvento>(SubEvento.class).novo(aux);
				}
			}
			
			
			return true;
		}
	
		return false;
	}




	
	
}
