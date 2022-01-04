package br.com.alura.store.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.store.model.Cliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}

	public List<Cliente> buscarTodos() {
		String jpql = "SELECT c From Cliente c";
		return this.em.createQuery(jpql, Cliente.class).getResultList();
	}

	public List<Cliente> buscarPorNome(String nome) {
		String jpql = "SELECT c From Cliente c WHERE p.nome = :nome";
		return this.em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome).getResultList();
	}
	
}
