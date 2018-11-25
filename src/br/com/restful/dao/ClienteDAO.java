package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Cliente;

/**
 * 
 * Classe Responsável por conter os métodos do CRUD
 * 
 * @author FilippePimentel
 */
public class ClienteDAO extends ConnectionFactory {

	private static ClienteDAO instance;
	/**
	 * Método Responsável por criar uma instancia da classe Cliente(Singleton)
	 * @author FilippePimentel
	 */
	public static ClienteDAO getInstance() {
		if(instance == null) 
			instance = new ClienteDAO();
		return instance;
	}
	
	private Connection con = null;
/**
 * Método Responsável por buscar todos clientes do banco
 * @author FilippePimentel
 */
	public ArrayList<Cliente> buscarTodosClientes() {
		
		try {
			con = ConnectionFactory.getConnection();
			String SQL = "SELECT * from tab_cliente WHERE id_cliente > 95 order by nome_cliente ";
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setEndereco(rs.getString("endereco_cliente"));
				clientes.add(cliente);
			}
			ps.close();
			return clientes;
		} catch (SQLException ex) {
			System.err.println("Erro ao recuperar: " + ex.getMessage());
		} catch (Exception ex) {
			System.err.println("Erro geral..." + ex.getMessage());
		}
		return null;
	}

	public Cliente buscarClientePeloId(Integer id) {
		String SQL = "SELECT * from tab_cliente WHERE id_cliente = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(SQL, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.first()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setEndereco(rs.getString("endereco_cliente"));
				return cliente;
			} else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println("Erro: " + ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return null;
	}

	public String buscarClientePeloNome(String nome) {
		String SQL = "SELECT * from tab_cliente WHERE nome_cliente = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(SQL, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();

			if (rs.first()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setEndereco(rs.getString("endereco_cliente"));
				return cliente.toString();
			} else {
				return "Cliente inexistente na base de dados!";
			}
		} catch (SQLException ex) {
			System.err.println("Erro: " + ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return null;
	}
	
	public String cadastrarCliente(Cliente cli) {
		String SQL = "INSERT INTO tab_cliente (id_cliente, "
			+ "nome_cliente, cpf_cliente, endereco_cliente)"
			+ " values(?,?,?,?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(SQL, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			stmt.setInt(1, cli.getId());
			stmt.setString(3, cli.getNome());
			stmt.setString(2, cli.getCpf());
			stmt.setString(4, cli.getEndereco());
			rs = stmt.executeQuery();

		} catch (SQLException ex) {
			return "Erro: " + ex.getMessage();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return "Sucesso!";
	}
}
