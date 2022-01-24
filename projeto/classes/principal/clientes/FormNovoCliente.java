

package principal.clientes;

import java.util.Date;

import dao.DAO;





public class FormNovoCliente extends FormClienteBase{


private static final long serialVersionUID = 1L;



	public FormNovoCliente(){
		
	this(null);
	}
	
	
	
	
	public FormNovoCliente(Cliente cliente){
		
		super(cliente);	
		
		adicionarComponentes();
	}
	
	

	
	public boolean acaoPrincipal(){
		
		if(!validacao())
			return false;
		
		if(this.cliente==null)
			this.cliente = new Cliente();
		
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
		
		cliente.setStatus(1);
		cliente.setData_cadastro(new Date());
		
		int id_cliente = new DAO<Cliente>(Cliente.class).novo(cliente);
			
		if(id_cliente>0){
					
			cliente.setId_cliente(id_cliente);
			return true;
		}
		
		return false;
	}




	
	
}
