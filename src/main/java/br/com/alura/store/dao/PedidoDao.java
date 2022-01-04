package br.com.alura.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.store.model.Pedido;
import br.com.alura.store.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		this.em.merge(pedido);
	}

	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.remove(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<Pedido> buscarTodos() {
		String jpql = "SELECT p From Pedido p";
		return this.em.createQuery(jpql, Pedido.class).getResultList();
	}

	public List<Pedido> buscarPorNome(String nome) {
		String jpql = "SELECT p From Pedido p WHERE p.nome = :nome";
		return this.em.createQuery(jpql, Pedido.class)
				.setParameter("nome", nome).getResultList();
	}
	
	public List<Pedido> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p From Pedido p WHERE p.categoria.nome = :nome";
		return this.em.createQuery(jpql, Pedido.class)
				.setParameter("nome", nome).getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco From Pedido p WHERE p.nome = :nome";
		return this.em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome).getSingleResult();
	}
	
	public List<Object[]> relatorioDeVendas(){
		String jpql = "SELECT produto.nome, SUM(item.quantidade), "
				+ "MAX(pedido.data) FROM Pedido pedido JOIN pedido.itens item  "
				+ "JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC";
		return this.em.createQuery(jpql, Object[].class).getResultList();
	}
	
	
	public List<RelatorioDeVendasVo> relatorioDeVendasVo(){
		String jpql = "SELECT new  br.com.alura.store.vo.RelatorioDeVendasVo (produto.nome, SUM(item.quantidade), "
				+ "MAX(pedido.data)) FROM Pedido pedido JOIN pedido.itens item  "
				+ "JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC";
		return this.em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	// Consultas com Join Fetch
	public Pedido buscarPedidoComCliente(Long id) {
		return this.em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class) // carrega um relacionamento EAGER 
				.setParameter("id", id)
				.getSingleResult();
	}
}
