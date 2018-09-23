package dao.r;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dao.IntefaceDao;
import entidade.Pessoa;

public abstract class Dao<T> implements IntefaceDao<T> {

	String esquema = SingletonConnection.ESQUEMA;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public Dao() {
		connection = SingletonConnection. //
				getSingletonConnection(). //
				getConnection();
	}

	public void criar(T object) { }

	public T ler(T object) { return null; }

	public List<T> lerTudo() { return null; }

	public void atualizar(T object) { }

	public void excluir(T object) { }
	
	

}
