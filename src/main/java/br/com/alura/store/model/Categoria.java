package br.com.alura.store.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId id; // chave composta

	public Categoria() {
	}

	public Categoria(String nome) {
		super();
		this.id = new CategoriaId(nome, "xpto");
	}
	
	public String getNome() {
		return this.id.getNome();
	}

}
