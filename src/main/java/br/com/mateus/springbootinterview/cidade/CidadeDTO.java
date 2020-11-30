package br.com.mateus.springbootinterview.cidade;

import javax.validation.constraints.NotEmpty;

public class CidadeDTO {
	
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String estado;

	public CidadeDTO() {
		super();
	}

	public CidadeDTO(Long id, @NotEmpty String nome, @NotEmpty String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
