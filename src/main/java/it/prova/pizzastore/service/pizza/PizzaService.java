package it.prova.pizzastore.service.pizza;

import java.util.List;

import it.prova.pizzastore.dao.pizza.PizzaDAO;
import it.prova.pizzastore.model.Pizza;


public interface PizzaService {
	public List<Pizza> listAllElements() throws Exception;

	public Pizza caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Pizza pizzaInstance) throws Exception;

	public void inserisciNuovo(Pizza pizzaInstance) throws Exception;

	public void rimuovi(Long idPizzaToRemove) throws Exception;

	public List<Pizza> findByExample(Pizza example) throws Exception;

	// per injection
	public void setPizzaDAO(PizzaDAO pizzaDAO);
}
