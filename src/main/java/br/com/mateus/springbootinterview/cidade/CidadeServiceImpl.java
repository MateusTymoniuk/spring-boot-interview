package br.com.mateus.springbootinterview.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mateus.springbootinterview.exception.NegocioException;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository repository;
	
	@Override
	public Cidade salvar(Cidade entity) {
		if(this.repository.existsByEstadoAndNome(entity.getEstado(), entity.getNome())) {
			throw new NegocioException("Já existe uma cidade com esse nome");
		}
		return this.repository.save(entity);
	}
	
	@Override
	public List<Cidade> buscarPorNome(String nome) {
		return this.repository.findByNome(nome);
	}

	@Override
	public List<Cidade> buscarPorEstado(String estado) {
		return this.repository.findByEstado(estado);
	}

}
