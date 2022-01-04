package br.com.alura.store.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.store.dao.CategoriaDao;
import br.com.alura.store.dao.ClienteDao;
import br.com.alura.store.dao.PedidoDao;
import br.com.alura.store.dao.ProdutoDao;
import br.com.alura.store.model.Categoria;
import br.com.alura.store.model.Cliente;
import br.com.alura.store.model.ItemPedido;
import br.com.alura.store.model.Pedido;
import br.com.alura.store.model.Produto;
import br.com.alura.store.util.JPAUtil;

public class PerformanceConsulta {

	public static void main(String[] args) {
		populaBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido buscarPedidoComCliente = pedidoDao.buscarPedidoComCliente(1l);

		em.close();
		System.out.println(buscarPedidoComCliente.getCliente().getNome());
	}

	private static void populaBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS4", "Slim", new BigDecimal("2000"), videogames);
		Produto pc = new Produto("DELL", "Vostro", new BigDecimal("4000"), informatica);
		Cliente cliente = new Cliente("Thales", "123456789");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(50, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido, pc));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(pc);
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);

		em.getTransaction().commit();
		em.close();
	}

}
