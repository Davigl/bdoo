package dao.r;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Uf;

public class UfDao extends Dao<Uf> {

	private Uf mapeamentoRelacionaObjeto() throws SQLException {
		Uf uf = new Uf();
		uf.setId(resultSet.getInt("id"));
		uf.setSigla(resultSet.getString("sigla"));
		uf.setNome(resultSet.getString("nome"));
		return uf;
	}

	@Override
	public void criar(Uf object) {
		try {
			String sql = String.format("insert into %s.uf(id, sigla, nome) " + //
					"values (?, ?, ?)", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());
			preparedStatement.setString(2, object.getSigla());
			preparedStatement.setString(3, object.getNome());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Uf ler(Integer id) {
		Uf uf = null;
		try {
			String sql = String.format("select id, sigla, nome from %s.uf " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				uf = mapeamentoRelacionaObjeto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uf;
	}

	@Override
	public Uf ler(Uf object) {
		return ler(object.getId());
	}

	@Override
	public List<Uf> lerTudo() {
		List<Uf> ufs = new ArrayList<Uf>();

		try {
			String sql = String.format("select id, sigla, nome from %s.uf",
					esquema);
			
			System.out.println("Aqui: " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Uf uf = mapeamentoRelacionaObjeto();
				ufs.add(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ufs;
	}

	@Override
	public void atualizar(Uf object) {
		try {
			String sql = String.format("update %s.uf set sigla = ?, nome = ? " + //
					"where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, object.getSigla());
			preparedStatement.setString(2, object.getNome());
			preparedStatement.setInt(3, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Uf object) {
		try {
			String sql = String.format("delete from %s.uf where id = ?", esquema);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, object.getId());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
