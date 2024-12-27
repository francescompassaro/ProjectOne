package com.project.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection conn;

	// DB parameters
	private static final String URL = "jdbc:mysql://localhost:3306/SHOP";
	private static final String USER = "francis";
	private static final String PASSWD = "francesco";

	// Costruttore privato per impedire l'istanziazione diretta
	private DBConnection() {
		try {
			// initialize connection
			conn = DriverManager.getConnection(URL, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error during database connection: " + e.getMessage());
		}
	}

	// metodo per ottenere l'istanza della classe
	public static DBConnection getInstance() {
		if (instance == null) {
			synchronized (DBConnection.class) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

	// metodo per ottenere la connessione
	public Connection getConnection() {
		return conn;
	}

	// metodo per chiudere la connessione
	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}