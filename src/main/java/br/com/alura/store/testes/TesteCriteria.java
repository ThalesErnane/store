package br.com.alura.store.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.store.dao.CategoriaDao;
import br.com.alura.store.dao.ProdutoDao;
import br.com.alura.store.model.Categoria;
import br.com.alura.store.model.Produto;
import br.com.alura.store.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		populaBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		 // dao.buscaPorParametrosComCriteria("PS4", null, LocalDate.now());
		// dao.buscaPorParametros("DELL", null, null);

	}

	private static void populaBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS4", "Slim", new BigDecimal("2000"), videogames);
		Produto pc = new Produto("DELL", "Vostro", new BigDecimal("4000"), informatica);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(pc);

		em.getTransaction().commit();
		em.close();
	}

}
