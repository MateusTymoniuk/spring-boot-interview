package br.com.mateus.springbootinterview.cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	boolean existsByEstadoAndNome(String estado, String nome);

	List<Cidade> findByNome(String nome);
	
	List<Cidade> findByEstado(String estado);
}
