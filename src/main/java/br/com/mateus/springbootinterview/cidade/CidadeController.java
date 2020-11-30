package br.com.mateus.springbootinterview.cidade;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO criar(@RequestBody @Valid CidadeDTO dto) {
		Cidade novaCidade = service.salvar(mapRequestDTOParaEntity(dto));
		return mapResponseEntityParaDTO(novaCidade);
	}
	
	@RequestMapping(method = RequestMethod.GET, params= {"nome"})
	public List<CidadeDTO> buscarPorNome(@RequestParam String nome) {
		List<Cidade> cidadesPorNome = service.buscarPorNome(nome);
		
		List<CidadeDTO> dtos = cidadesPorNome
				  .stream()
				  .map(cidade -> mapResponseEntityParaDTO(cidade))
				  .collect(Collectors.toList());
		return dtos;
	}
	
	@RequestMapping(method = RequestMethod.GET, params= {"estado"})
	public List<CidadeDTO> buscarPorEstado(@RequestParam String estado) {
		List<Cidade> cidadesPorEstado = service.buscarPorEstado(estado);
		
		List<CidadeDTO> dtos = cidadesPorEstado
				  .stream()
				  .map(cidade -> mapResponseEntityParaDTO(cidade))
				  .collect(Collectors.toList());
		return dtos;
	}
	
	private Cidade mapRequestDTOParaEntity(CidadeDTO dto) {
		return mapper.map(dto, Cidade.class);
	}

	private CidadeDTO mapResponseEntityParaDTO(Cidade entity) {
		return mapper.map(entity, CidadeDTO.class);
	}
}
