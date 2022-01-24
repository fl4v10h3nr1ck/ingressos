package principal.clientes;

import comuns.Comuns;
import dao.DAO;




public class FormAlterCliente extends FormClienteBase{




private static final long serialVersionUID = 1L;

	


	public FormAlterCliente(Cliente cliente) {
	
		super(cliente);
		
		this.adicionarComponentes();
		
		this.setValores();
	}


	

	

	public void setValores(){
	
		this.tf_nome_razao.setText(this.cliente.getNome_razao());
		this.tf_tel.setText(this.cliente.getTel());
	
		this.tf_logradouro.setText(this.cliente.getLogradouro());
		this.tf_num.setText(this.cliente.getNumero());
		this.tf_bairro.setText(this.cliente.getBairro());
		this.tf_cidade.setText(this.cliente.getCidade());
		this.tf_cep.setText(this.cliente.getCep());
		this.cb_uf.setSelectedItem(this.cliente.getUf());
		this.tf_complemento.setText(this.cliente.getComplemento());
		
		if(Comuns.temConteudo(this.cliente.getCpf_cnpj()) && this.cliente.getCpf_cnpj().length()>14)
			this.cb_tipo.setSelectedIndex(1);
		else
			this.cb_tipo.setSelectedIndex(0);
		
		this.setTipo();
	}
	
	
	
	
	
	public boolean acaoPrincipal(){

		if(!validacao())
			return false;
						
		cliente.setNome_razao(tf_nome_razao.getText());
		cliente.setCpf_cnpj(tf_cpf_cnpj.getText());
		cliente.setTel(tf_tel.getText());
		
		cliente.setLogradouro(tf_logradouro.getText());
		cliente.setNumero(tf_num.getText());
		cliente.setBairro(tf_bairro.getText());
		cliente.setCidade(tf_cidade.getText());
		cliente.setCep(tf_cep.getText());
		cliente.setUf(cb_uf.getSelectedItem().toString());
		cliente.setComplemento(tf_complemento.getText());
	
		return new DAO<Cliente>(Cliente.class).altera(this.cliente);
	}
	
	
	
}
