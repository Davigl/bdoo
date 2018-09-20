package dao.r;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Municipio;
import entidade.Pessoa;

public class PessoaDao extends Dao<Pessoa> {
	
	private Municipio municipio = new Municipio();
	private MunicipioDao municipioDao = new MunicipioDao();
	
	private Pessoa mapeamentoRelacionaObjeto() throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(resultSet.getInt("id"));
		pessoa.setNome(resultSet.getString("nome"));
		municipio = municipioDao.ler(resultSet.getInt("id_municipio"));
		pessoa.setMunicipio(municipio);
		
		return pessoa;
	}
	

	private void criarMunicipio(Municipio municipio) {
		Municipio municipioLer = municipioDao.ler(municipio.getId());
		if (municipioLer == null) {
			municipioDao.criar(municipio);
		}
	}
	
	@Override
	public void criar(Pessoa object) {
		try {
			criarMunicipio(object.getMunicipio());

			String sql = String.format("insert into %s.pessoa(id, nome, id_municipio) " + //
					"values (?, ?, ?)", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());
			preparedStatement.setString(2, object.getNome());
			preparedStatement.setInt(3, object.getMunicipio().getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public Pessoa ler(Integer id) {
		Pessoa pessoa = null;
		try {
			String sql = String.format("select id, nome, id_municipio from %s.Pessoa " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pessoa = mapeamentoRelacionaObjeto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	@Override
	public Pessoa ler(Pessoa object) {
		return ler(object.getId());
	}

	@Override
	public List<Pessoa> lerTudo() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			String sql = String.format("select id, nome, id_municipio from %s.Pessoa", esquema);

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Pessoa pessoa = mapeamentoRelacionaObjeto();
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoas;
	}

	@Override
	public void atualizar(Pessoa object) {
		try {
			criarMunicipio(object.getMunicipio());

			String sql = String.format("update %s.pessoa set nome = ?, id_municipio = ? " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, object.getNome());
			preparedStatement.setInt(2, object.getMunicipio().getId());
			preparedStatement.setInt(3, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Pessoa object) {
		try {
			String sql = String.format("delete from %s.pessoa " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
