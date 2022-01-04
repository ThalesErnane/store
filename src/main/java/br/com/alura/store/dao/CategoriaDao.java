package br.com.alura.store.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.store.model.Categoria;

public class CategoriaDao {

	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	
	public Categoria buscarPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}

	public List<Categoria> buscarTodos() {
		String jpql = "SELECT c From Categoria c";
		return this.em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> buscarPorNome(String nome) {
		String jpql = "SELECT c From Categoria c WHERE c.nome = 1l";
		return this.em.createQuery(jpql, Categoria.class)
				.setParameter(1, nome).getResultList();
	}
	
}
