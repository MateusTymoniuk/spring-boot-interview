package br.com.mateus.springbootinterview.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mateus.springbootinterview.cidade.CidadeRepository;
import br.com.mateus.springbootinterview.exception.NegocioException;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Cliente salvar(Cliente entity) {
		if(this.clienteRepository.existsByNome(entity.getNome())) {
			throw new NegocioException("Já existe um cliente com esse nome");
		}
		
		if(!this.cidadeRepository.existsByEstadoAndNome(entity.getCidade().getEstado(), entity.getCidade().getNome())) {
			throw new NegocioException("É preciso cadastrar a cidade antes");
		}
		
		return this.clienteRepository.save(entity);
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return this.clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {
		return this.clienteRepository.findByNome(nome);
	}

	@Override
	public void excluir(Cliente entity) {
		this.clienteRepository.delete(entity);
	}
	
	@Override
	public Cliente atualizar(Cliente entity) {
		if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("Cliente não pode ter id nulo");
        }
        return this.clienteRepository.save(entity);
	}

}
