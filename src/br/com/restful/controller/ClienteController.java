package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.ClienteDAO;
import br.com.restful.model.Cliente;

/**
 * Classe responsável por ser o controlador entre o resource e a camada DAO
 * @author FilippePimentel
 */
	public class ClienteController {
		
	public ArrayList<Cliente> listarTodos() {
		return ClienteDAO.getInstance().buscarTodosClientes();
	}
	
	public Cliente buscarClientePeloID(Integer id) 
	{
		return ClienteDAO.getInstance().buscarClientePeloId(id);
	}
	
	public String buscarClientePeloNome(String nome) 
	{
		return ClienteDAO.getInstance().buscarClientePeloNome(nome);
	}
	
	public String CadastrarCliente(Cliente cli) 
	{
		return ClienteDAO.getInstance().cadastrarCliente(cli);
	}
	
}
