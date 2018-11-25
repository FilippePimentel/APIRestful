package br.com.restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * Classe Respons�vel por conter os m�todos de criar e fechar o banco de dados
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
	 * M�todo Respons�vel por criar conex�o com banco de dados
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
			System.err.println("Erro na conex�o: " + ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * M�todo Respons�vel por fechar conex�o com banco de dados
	 * 
	 * @author FilippePimentel
	 */
	public static void closeConnection(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro em fechar conex�o: " + ex.getMessage());
		}
	}

	/**
	 * 
	 * M�todo Respons�vel por fechar a conex�o com banco de dados
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
			System.err.println("Erro em fechar conex�o: " + ex.getMessage());
		}
	}

	/**
	 * 
	 * M�todo Respons�vel por fechar conex�o com banco de dados
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
			System.err.println("Erro em fechar conex�o: " + ex.getMessage());
		}
	}
}
