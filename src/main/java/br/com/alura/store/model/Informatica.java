package br.com.alura.store.model;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto { // Herança 

	private String autor;

	private Integer numeroDePaginas;

	private String marca;

	private String modelo;

	public Informatica() {
	}

	public Informatica(String autor, Integer numeroDePaginas, String marca, String modelo) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.marca = marca;
		this.modelo = modelo;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
