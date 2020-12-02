package br.com.mateus.springbootinterview.cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
	
	Cliente salvar(Cliente entity);

	Optional<Cliente> buscarPorId(Long id);
	
	List<Cliente> buscarPorNome(String nome);
	
	void excluir(Cliente entity);
	
	Cliente atualizar(Cliente entity);
}
