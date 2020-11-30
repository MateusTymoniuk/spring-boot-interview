package br.com.mateus.springbootinterview.cliente;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mateus.springbootinterview.cidade.Cidade;
import br.com.mateus.springbootinterview.cidade.CidadeRepository;
import br.com.mateus.springbootinterview.exception.NegocioException;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Cliente salvar(ClienteDTO dto) {
		if(this.clienteRepository.existsByNome(dto.getNome())) {
			throw new NegocioException("Já existe um cliente com esse nome");
		}
		
		Cidade cidade = cidadeRepository.getOne(dto.getCidadeId());
		dto.setCidade(cidade);
		return this.clienteRepository.save(mapRequestDTOParaEntity(dto));
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
	
	private Cliente mapRequestDTOParaEntity(ClienteDTO dto) {
		return mapper.map(dto, Cliente.class);
	}

	@Override
	public Cliente atualizar(Cliente entity) {
		if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("Cliente não pode ter id nulo");
        }
        return this.clienteRepository.save(entity);
	}

}
