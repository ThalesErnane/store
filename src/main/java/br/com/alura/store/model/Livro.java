package br.com.alura.store.model;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto { // Herança 

	private String autor;

	private Integer numeroDePaginas;
	
	public Livro() {}

	public Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

}
