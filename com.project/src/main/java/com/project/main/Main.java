package com.project.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.utility.DBConnection;

public class Main {
	
	
	public static void main(String[] args) {
		// Ottenere l'istanza della connessione
		DBConnection DBConn = DBConnection.getInstance();
		Connection conn = DBConn.getConnection();
		
		String QUERY = "SELECT * FROM products";
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(QUERY);
			
			while(rs.next()) {
				System.out.println("Product Name: " + rs.getString("product_name"));
				
			}
			rs.close();
			stm.close();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.closeConnection();
		}
	}

}
