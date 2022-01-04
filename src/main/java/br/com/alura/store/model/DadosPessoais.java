package br.com.alura.store.model;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

	private String nome;

	private String cpf;

	public DadosPessoais() {
		super();
	}

	public DadosPessoais(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
