package it.prova.pizzastore.dao.pizza;

import java.util.List;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Pizza;

public interface PizzaDOA extends IBaseDAO<Pizza> {
	public List<Pizza> findByExample(Pizza example);
}
