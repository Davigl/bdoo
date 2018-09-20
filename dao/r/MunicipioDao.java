package dao.r;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Municipio;
import entidade.Uf;

public class MunicipioDao extends Dao<Municipio> {

	private UfDao ufDao = new UfDao();
	private Uf uf;

	private Municipio mapeamentoRelacionaObjeto() throws SQLException {
		Municipio municipio = new Municipio();
		municipio.setId(resultSet.getInt("id"));
		municipio.setNome(resultSet.getString("nome"));
		uf = ufDao.ler(resultSet.getInt("id_uf"));
		municipio.setUf(uf);
		return municipio;
	}

	private void criarUf(Uf uf) {
		Uf ufLer = ufDao.ler(uf.getId());
		if (ufLer == null) {
			ufDao.criar(uf);
		}
	}

	@Override
	public void criar(Municipio object) {
		try {
			criarUf(object.getUf());

			String sql = String.format("insert into %s.municipio(id, nome, id_uf) " + //
					"values (?, ?, ?)", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());
			preparedStatement.setString(2, object.getNome());
			preparedStatement.setInt(3, object.getUf().getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Municipio ler(Integer id) {
		Municipio municipio = null;
		try {
			String sql = String.format("select id, nome, id_uf from %s.Municipio " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				municipio = mapeamentoRelacionaObjeto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return municipio;
	}

	@Override
	public Municipio ler(Municipio object) {
		return ler(object.getId());
	}

	@Override
	public List<Municipio> lerTudo() {
		List<Municipio> municipios = new ArrayList<Municipio>();
		try {
			String sql = String.format("select id, nome, id_uf from %s.Municipio", esquema);

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Municipio municipio = mapeamentoRelacionaObjeto();
				municipios.add(municipio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return municipios;
	}

	@Override
	public void atualizar(Municipio object) {
		try {
			criarUf(object.getUf());

			String sql = String.format("update %s.municipio set nome = ?, id_uf = ? " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, object.getNome());
			preparedStatement.setInt(2, object.getUf().getId());
			preparedStatement.setInt(3, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Municipio object) {
		try {
			String sql = String.format("delete from %s.municipio " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
