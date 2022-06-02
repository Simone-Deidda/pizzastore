package it.prova.pizzastore.dao.cliente;

import java.util.List;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Cliente;

public interface ClienteDAO extends IBaseDAO<Cliente>{
	public List<Cliente> findByExample(Cliente example);

}
