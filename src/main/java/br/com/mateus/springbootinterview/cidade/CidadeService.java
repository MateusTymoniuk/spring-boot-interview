package br.com.mateus.springbootinterview.cidade;

import java.util.List;

public interface CidadeService {
	
	Cidade salvar(Cidade entity);

	List<Cidade> buscarPorNome(String nome);
	
	List<Cidade> buscarPorEstado(String estado);

}
