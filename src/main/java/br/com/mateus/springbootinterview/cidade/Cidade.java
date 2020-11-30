package br.com.mateus.springbootinterview.cidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.mateus.springbootinterview.cliente.Cliente;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	private String estado;

	@OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
	private List<Cliente> clientes;

	public Cidade() {
		super();
	}

	public Cidade(long id, String nome, String estado, List<Cliente> clientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
		this.clientes = clientes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
