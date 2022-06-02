package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.dao.cliente.ClienteDAO;
import it.prova.pizzastore.model.Cliente;


public interface ClienteService {
	public List<Cliente> listAllElements() throws Exception;

	public Cliente caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Cliente clienteInstance) throws Exception;

	public void inserisciNuovo(Cliente clienteInstance) throws Exception;

	public void rimuovi(Long idClienteToRemove) throws Exception;

	public List<Cliente> findByExample(Cliente example) throws Exception;

	// per injection
	public void setClienteDAO(ClienteDAO clienteDAO);
}
