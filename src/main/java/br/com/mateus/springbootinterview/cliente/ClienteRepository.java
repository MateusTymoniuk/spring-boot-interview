package br.com.mateus.springbootinterview.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	boolean existsByNome(String nome);

	List<Cliente> findByNome(String nome);
}
