package br.com.restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * Classe Responsável por conter os métodos de criar e fechar o banco de dados
 * 
 * @author FilippePimentel
 */

public class ConnectionFactory {
	private static String HOSTNAME = "localhost";
	private static int PORTA = 5432;
	private static String BANCO = "ProjetoClienteRestful";
	private static String USUARIO = "postgres";
	private static String SENHA = "postgres";
	private static String URL = "jdbc:postgresql://" + HOSTNAME + ":" + PORTA + "/" + BANCO;

	/**
	 * 
	 * Método Responsável por criar conexão com banco de dados
	 * 
	 * @author FilippePimentel
	 */
	public static Connection getConnection() {
		try {

			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (ClassNotFoundException ex) {
			System.err.println("Erro: " + ex.getMessage());
		} catch (SQLException ex) {
			System.err.println("Erro na conexão: " + ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * Método Responsável por fechar conexão com banco de dados
	 * 
	 * @author FilippePimentel
	 */
	public static void closeConnection(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro em fechar conexão: " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Método Responsável por fechar a conexão com banco de dados
	 * 
	 * @author FilippePimentel
	 */
	public static void closeConnection(Connection con, PreparedStatement stmt) {

		closeConnection(con);
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro em fechar conexão: " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Método Responsável por fechar conexão com banco de dados
	 * 
	 * @author FilippePimentel
	 */
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

		closeConnection(con, stmt);
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro em fechar conexão: " + ex.getMessage());
		}
	}
}
