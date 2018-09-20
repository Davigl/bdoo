package dao.r;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.IntefaceDao;

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

}
