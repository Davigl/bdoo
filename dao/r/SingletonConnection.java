package dao.r;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {

	private Connection connection;
	
	public static final String ESQUEMA = "bdoo_2016110337";

	private static final String URL = "jdbc:postgresql://labsql.fapce.edu.br:3024/fap_2018_2";
	private static final String USUARIO = "bdoo_2016110337";
	private static final String SENHA = "rapariga123";

	private static SingletonConnection singletonConnection;
	
	private SingletonConnection() {
		try {
			connection = DriverManager.getConnection(URL, USUARIO, SENHA); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SingletonConnection getSingletonConnection() {
		if ( singletonConnection == null ) {
			singletonConnection = new SingletonConnection();
		}
		
		return singletonConnection;		
	}
	
	public Connection getConnection() {
		return connection;
	}

}
