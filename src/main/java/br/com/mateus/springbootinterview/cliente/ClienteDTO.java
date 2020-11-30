package br.com.mateus.springbootinterview.cliente;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.mateus.springbootinterview.cidade.Cidade;

public class ClienteDTO {

	private Long id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String sexo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataNascimento;

	private Integer idade;

	private Long cidadeId;

	@JsonIgnore
	private Cidade cidade;

	@JsonProperty("idade")
	public Integer getIdade() {
		if (dataNascimento == null) {
			return null;
		}
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Long id, @NotEmpty String nome, @NotEmpty String sexo, LocalDate dataNascimento, Integer idade,
			Long cidadeId, Cidade cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidadeId = cidadeId;
		this.cidade = cidade;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidade) {
		this.cidadeId = cidade;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
