package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.r.MunicipioDao;
import dao.r.PessoaDao;
import dao.r.UfDao;
import entidade.Municipio;
import entidade.Pessoa;
import entidade.Uf;

public class MunicipioTeste {

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
	 * TESTE CRIAR MUNICIPIO
	 * POPULA O BANCO PARA REALIZAR OS TESTES
	 * CRIA MUNICIPIO (JUAZEIRO DO NORTE) (ID = 0) 
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
			
			ufDao.criar(uf);
			municipioDao.criar(municipio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * TESTE LER MUNICIPIO
	 * REALIZA TESTE DE BUSCA MUNICIPIO
	 * BUSCA (JUAZEIRO DO NORTE) (ID = 0)
	 */
	
	@Test
	public void testeC() {
		Municipio municipio = municipioDao.ler(0);
		
		// JOSEFA NO BANCO NÃO É NULL
		
		assertNotNull(municipio);
		
		// TESTES DOS ATRIBUTOS OBTIDOS
		
		assertEquals("Juazeiro do Norte", municipio.getNome());
		assertSame(0, municipio.getId());
		assertEquals("Ceara", municipio.getUf().getNome());
	}
	
	/*
	 * TESTE ATUALIZAR UF
	 * ATUALIZA UF COM (ID = 0)
	 * MUDA NOME MUNICIPIO PARA Crato
	 */
	
	@Test
	public void testeD() {
		try {
			Municipio municipioAntigo = new Municipio(); 
			Municipio municipioNovo = new Municipio();
			Municipio municipioTeste = new Municipio();
			
			municipioAntigo = municipioDao.ler(0);
			
			municipioNovo.setId(0);
			municipioNovo.setNome("Crato");
			municipioNovo.setUf(municipioAntigo.getUf());
			
			municipioDao.atualizar(municipioNovo);
			
			municipioTeste = municipioDao.ler(0);
			
			assertEquals("Crato", municipioTeste.getNome());
		
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
			Municipio municipio = municipioDao.ler(0);
			
			assertNotNull(municipio);
		
			municipioDao.excluir(municipio);
			
			municipio = municipioDao.ler(0);
			
			assertNull(municipio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
