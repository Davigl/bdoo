package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.r.MunicipioDao;
import dao.r.PessoaDao;
import dao.r.UfDao;
import entidade.Municipio;
import entidade.Pessoa;
import entidade.Uf;

public class PessoaTeste {
	
	PessoaDao pessoaDao = new PessoaDao();
	MunicipioDao municipioDao = new MunicipioDao();
	UfDao ufDao = new UfDao();
	
	/*
	 * TESTES REALIZADOS COM ID = 0
	 * DELETA REGISTRO DE PESSOA, MUNICIPIO E UF (ID = 0)
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
	 * TESTE CRIAR PESSOA
	 * POPULA O BANCO PARA REALIZAR OS TESTES
	 * CRIA JOSEFA, MUNICIPIO E UF (ID = 0) 
	 */
	
	@Test
	public void testeB() {
		try {
			Uf uf = new Uf();
			uf.setId(0);
			uf.setNome("Ceara");
			uf.setSigla("CE");
			
			Municipio municipio = new Municipio();
			municipio.setId(0);
			municipio.setNome("Juazeiro do Norte");
			municipio.setUf(uf);
			
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Josefa");
			pessoa.setId(0);
			pessoa.setMunicipio(municipio);
			
			// ADICIONA MUNICIPIO UF E PESSOA AO BANCO COM ID 0
			
			ufDao.criar(uf);
			municipioDao.criar(municipio);
			pessoaDao.criar(pessoa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * TESTE BUSCAR PESSOA
	 * REALIZA TESTE DE BUSCA PESSOA
	 * BUSCA JOSEFA (ID = 0)
	 */
	
	@Test
	public void testeC() {
		Pessoa josefa = pessoaDao.ler(0);
		
		// JOSEFA NO BANCO NÃO É NULL
		
		assertNotNull(josefa);
		assertNotNull(josefa.getMunicipio());
		assertNotNull(josefa.getMunicipio().getUf());
		
		// TESTES DOS ATRIBUTOS OBTIDOS
		
		assertEquals("Josefa", josefa.getNome());
		assertSame(0, josefa.getId());
		assertEquals("Ceara", josefa.getMunicipio().getUf().getNome());
		assertEquals("Juazeiro do Norte", josefa.getMunicipio().getNome());
	}
	
	/*
	 * TESTE ATUALIZAR PESSOA
	 * ATUALIZA PESSOA COM (ID = 0)
	 * MUDA NOME PARA Maria
	 */
	
	@Test
	public void testeD() {
		try {
			Pessoa pessoaAntiga = new Pessoa(); 
			Pessoa pessoaNova = new Pessoa();
			Pessoa pessoaTeste = new Pessoa();
			
			pessoaAntiga = pessoaDao.ler(0);
			
			pessoaNova.setId(0);
			pessoaNova.setNome("Maria");
			pessoaNova.setMunicipio(pessoaAntiga.getMunicipio());
		
			pessoaDao.atualizar(pessoaNova);
			
			pessoaTeste = pessoaDao.ler(0);
			
			assertEquals("Maria", pessoaTeste.getNome());
			assertSame(0, pessoaTeste.getId());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * TESTE EXCLUIR PESSOA
	 * EXCLUI PESSOA COM (ID = 0)
	 */
	
	@Test
	public void testeE() {
		try {
			Pessoa pessoa = pessoaDao.ler(0);
			
			assertNotNull(pessoa);
		
			pessoaDao.excluir(pessoa);
			
			pessoa = pessoaDao.ler(0);
			
			assertNull(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
