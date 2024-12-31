package com.project.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.project.utility.DBConnection;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	        int choice;
	        do {
	            System.out.println("\n--- Menu Interattivo ---");
	            System.out.println("1. Visualizza utenti");
	            System.out.println("2. Aggiungi un nuovo utente");
	            System.out.println("3. Aggiorna un utente");
	            System.out.println("4. Elimina un utente");
	            System.out.println("5. Esci");
	            System.out.print("Scegli un'opzione: ");
	            choice = scanner.nextInt();
	            scanner.nextLine(); // Consuma il carattere newline
	            switch (choice) {
                case 1:
                    viewUsers();
                    break;
//                case 2:
//                    addUser(scanner);
//                    break;
//                case 3:
//                    updateUser(scanner);
//                    break;
//                case 4:
//                    deleteUser(scanner);
//                    break;
//                case 5:
//                    System.out.println("Uscita dal programma.");
//                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
	        } while (choice !=5);
	        
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
	
	static DBConnection DBConn = DBConnection.getInstance();
	private static void viewUsers() {
        String query = "SELECT * FROM utenti";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        try (Connection connection = DBConn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n--- Elenco Utenti ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la visualizzazione degli utenti: " + e.getMessage());
        }
    }

}
