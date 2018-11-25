package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.restful.controller.ClienteController;
import br.com.restful.model.Cliente;

/**
 * Classe responsável por conter os métodos REST de acesso ao WebService
 * @author FilippePimentel
 */

@Path("/cliente")
public class ClienteResource {
	
	/**
	 * Método responsável por fazer chamada ao controller
	 * @author FilippePimentel
	 */
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cliente> listarTodos(){
		return new ClienteController().listarTodos();
	}
	
	@GET
	@Path("/buscarPorNome")
	@Produces("application/json")
	public String buscarClientePeloNome(@QueryParam("nome") String nome) {
		return new ClienteController().buscarClientePeloNome(nome);
	}
	
	@GET
	@Path("/buscarPorID")
	@Produces("application/json")
	public Cliente buscarClientePeloId(@QueryParam("id") String id) {
		return new ClienteController().buscarClientePeloID(Integer.parseInt(id));
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String Cadastrar(String json){
	    
	    System.out.println(json);
	    return null;
	}
}