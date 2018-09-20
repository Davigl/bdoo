package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.r.MunicipioDao;
import dao.r.PessoaDao;
import dao.r.UfDao;
import entidade.Municipio;
import entidade.Pessoa;
import entidade.Uf;

public class UfTeste {
	
	UfDao ufDao = new UfDao();
	PessoaDao pessoaDao = new PessoaDao();
	MunicipioDao municipioDao = new MunicipioDao();

	/*
	 * TESTES REALIZADOS COM ID = 0
	 * DELETA REGISTRO DE UF (ID = 0)
	 */
	
	@Test
	public void testeA() {
		try {
			Municipio municipio = new Municipio();
			municipio.setId(0);
			
			municipioDao.excluir(municipio);
			
			Pessoa pessoa = new Pessoa();
			pessoa.setId(0);
			
			pessoaDao.excluir(pessoa);
			
			Uf uf = new Uf();
			uf.setId(0);
			
			ufDao.excluir(uf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * TESTE CRIAR UF
	 * POPULA O BANCO PARA REALIZAR OS TESTES
	 * CRIA UF (CEARÁ) (ID = 0) 
	 */
	
	@Test
	public void testeB() {
		try {
			Uf uf = new Uf();
			uf.setId(0);
			uf.setNome("Ceara");
			uf.setSigla("CE");
			
			ufDao.criar(uf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * TESTE LER UF
	 * REALIZA TESTE DE BUSCA UF
	 * BUSCA CEARÁ (ID = 0)
	 */
	
	@Test
	public void testeC() {
		Uf uf = ufDao.ler(0);
		
		// JOSEFA NO BANCO NÃO É NULL
		
		assertNotNull(uf);
		
		// TESTES DOS ATRIBUTOS OBTIDOS
		
		assertEquals("Ceara", uf.getNome());
		assertSame(0, uf.getId());
		assertEquals("CE", uf.getSigla());
	}
	
	/*
	 * TESTE ATUALIZAR UF
	 * ATUALIZA UF COM (ID = 0)
	 * MUDA NOME E SIGLA PARA Sao Paulo e SP respectivamente.
	 */
	
	@Test
	public void testeD() {
		try {
			Uf ufAntigo = new Uf(); 
			Uf ufNovo = new Uf();
			Uf ufTeste = new Uf();
			
			ufAntigo = ufDao.ler(0);
			
			ufNovo.setId(0);
			ufNovo.setNome("Sao Paulo");
			ufNovo.setSigla("SP");
		
			ufDao.atualizar(ufNovo);
			
			ufTeste = ufDao.ler(0);
			
			assertEquals("Sao Paulo", ufTeste.getNome());
			assertEquals("SP", ufTeste.getSigla());
			assertSame(0, ufTeste.getId());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * TESTE EXCLUIR UF
	 * EXCLUI UF COM (ID = 0)
	 */
	
	@Test
	public void testeE() {
		try {
			Uf uf = ufDao.ler(0);
			
			assertNotNull(uf);
		
			ufDao.excluir(uf);
			
			uf = ufDao.ler(0);
			
			assertNull(uf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
