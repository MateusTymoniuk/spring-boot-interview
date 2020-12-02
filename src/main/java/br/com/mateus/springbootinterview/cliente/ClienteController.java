package br.com.mateus.springbootinterview.cliente;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO criar(@RequestBody @Valid ClienteDTO dto) {
		Cliente novoCliente = service.salvar(mapRequestDTOParaEntity(dto));
		return mapResponseEntityParaDTO(novoCliente);
	}

	@GetMapping("{id}")
	public ClienteDTO get(@PathVariable Long id) {
		return service.buscarPorId(id).map(this::mapResponseEntityParaDTO)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = RequestMethod.GET, params = { "nome" })
	public List<ClienteDTO> buscarPorNome(@RequestParam String nome) {
		List<Cliente> clientesPorNome = service.buscarPorNome(nome);

		List<ClienteDTO> dtos = clientesPorNome.stream().map(cidade -> mapResponseEntityParaDTO(cidade))
				.collect(Collectors.toList());
		return dtos;
	}

	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	public ClienteDTO atualizar(@PathVariable Long id, @RequestBody JsonPatch patch) {
		try {
			Cliente cliente = service.buscarPorId(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
			Cliente clienteAtualizado = applyPatchToCustomer(patch, cliente);
			return mapResponseEntityParaDTO(service.atualizar(clienteAtualizado));
		} catch (JsonProcessingException | JsonPatchException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		Cliente client = service.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		service.excluir(client);
	}
	
	private Cliente mapRequestDTOParaEntity(ClienteDTO dto) {
		return mapper.map(dto, Cliente.class);
	}

	private ClienteDTO mapResponseEntityParaDTO(Cliente entity) {
		return mapper.map(entity, ClienteDTO.class);
	}

	private Cliente applyPatchToCustomer(JsonPatch patch, Cliente targetCustomer)
			throws JsonPatchException, JsonProcessingException {
		JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
		return objectMapper.treeToValue(patched, Cliente.class);
	}

}
