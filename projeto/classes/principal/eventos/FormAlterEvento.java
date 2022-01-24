package principal.eventos;

import java.util.List;

import comuns.Comuns;
import comuns.Data;
import dao.DAO;
import principal.clientes.Cliente;
import principal.lotes.Lote;




public class FormAlterEvento extends FormEventoBase{




private static final long serialVersionUID = 1L;

	


	public FormAlterEvento(Evento cliente) {
	
		super(cliente);
		
		this.adicionarComponentes();
		
		this.setValores();
	}


	

	

	public void setValores(){
	
		this.tf_nome.setText(this.evento.getNome());
		
		this.tf_local.setText(this.evento.getNome_local());
		this.tf_logradouro.setText(this.evento.getLogradouro());
		this.tf_num.setText(this.evento.getNumero());
		this.tf_bairro.setText(this.evento.getBairro());
		this.tf_cidade.setText(this.evento.getCidade());
		this.tf_cep.setText(this.evento.getCep());
		this.cb_uf.setSelectedItem(this.evento.getUf());
		this.tf_complemento.setText(this.evento.getComplemento());
		
		if(this.evento.getTipo() ==Comuns.TIPO_DIA_UNICO ){
			
			this.tipo_dia_unico.setSelected(true);
			
			this.setTipo();
			
			SubEvento subevento = new DAO<SubEvento>(SubEvento.class).getPrimeiroOuNada(null, "svt.fk_evento="+this.evento.getId_evento(), "svt.data DESC");
		
			if(subevento!=null){
				
				this.tf_data.setText(Data.converteDataParaString(subevento.getData()));
				this.tf_hora_inicio.setText(subevento.getHora_inicio());
				this.tf_min_inicio.setText(subevento.getMin_inicio());
				this.tf_hora_fim.setText(subevento.getHora_fim());
				this.tf_min_fim.setText(subevento.getMin_fim());
			}
		}
		else{
			
			if(this.evento.getTipo() ==Comuns.TIPO_VARIOS_DIAS_INGRESSO_UNICO )
				this.tipo_varios_dias_um_ingresso.setSelected(true);
			else if(this.evento.getTipo() ==Comuns.TIPO_VARIOS_DIAS_VARIOS_INGRESSOS )
				this.tipo_varios_dias_varios_ingressos.setSelected(true);
			
			this.setTipo();
			
			this.subeventos = new DAO<SubEvento>(SubEvento.class).get(null, "svt.fk_evento="+this.evento.getId_evento(), "svt.data DESC");
			
			this.atualizaTabelaDeSubEventos();
		}
		
		this.cliente = new DAO<Cliente>(Cliente.class).get(this.evento.getFk_cliente());

		if(this.cliente!=null)
			this.tf_cliente.setText("("+this.cliente.getCpf_cnpj()+") "+this.cliente.getNome_razao());	
	
		if(possuiLoteGerado()){
			
			this.tf_cliente.setEnabled(false);
			this.tipo_dia_unico.setEnabled(false);
			this.tipo_varios_dias_um_ingresso.setEnabled(false);
			this.tipo_varios_dias_varios_ingressos.setEnabled(false);
			
			this.tf_data.setEnabled(false);
			this.tf_hora_inicio.setEnabled(false);
			this.tf_min_inicio.setEnabled(false);
			this.tf_hora_fim.setEnabled(false);
			this.tf_min_fim.setEnabled(false);
			this.bt_adicionar_subevento.setEnabled(false);
			this.bt_remover_subevento.setEnabled(false);
		}
	
		this.tf_logo.setText(this.evento.getLogo());
	}
	
	
	
	
	
	public boolean acaoPrincipal(){

		if(!validacao())
			return false;
		
		evento.setNome(tf_nome.getText());
	
		evento.setNome_local(tf_local.getText());
		evento.setLogradouro(tf_logradouro.getText());
		evento.setNumero(tf_num.getText());
		evento.setBairro(tf_bairro.getText());
		evento.setCidade(tf_cidade.getText());
		evento.setCep(tf_cep.getText());
		evento.setUf(cb_uf.getSelectedItem().toString());
		evento.setComplemento(tf_complemento.getText());
	
		evento.setLogo(Comuns.addNovaLogo(evento.getLogo(), this.tf_logo.getText()));
		
		if(possuiLoteGerado())
			return new DAO<Evento>(Evento.class).altera(this.evento);
		
		evento.setFk_cliente(this.cliente.getId_cliente());
		
		if(new DAO<Evento>(Evento.class).altera(this.evento)){
		
			new DAO<SubEvento>(SubEvento.class).removeSemConfirmacao("fk_evento="+evento.getId_evento());
			
			if(this.tipo_dia_unico.isSelected())
				evento.setTipo(Comuns.TIPO_DIA_UNICO);
			else if(this.tipo_varios_dias_um_ingresso.isSelected())
				evento.setTipo(Comuns.TIPO_VARIOS_DIAS_INGRESSO_UNICO);
			else if(this.tipo_varios_dias_varios_ingressos.isSelected())
				evento.setTipo(Comuns.TIPO_VARIOS_DIAS_VARIOS_INGRESSOS);

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
	
	
	
	
	private boolean possuiLoteGerado(){
		
		List<SubEvento> subeventos = new DAO<SubEvento>(SubEvento.class).get(null, "svt.fk_evento="+this.evento.getId_evento(), "svt.data DESC");
		
		boolean controle = false;
		
		for(SubEvento aux: subeventos){
		
			if(new DAO<Lote>(Lote.class).getCont(null, "lte.fk_subevento="+aux.getId_subevento(), null)>0){
				
				controle = true;
				break;
			}		
		}
		
		return controle;
	}
	
	
	
}
