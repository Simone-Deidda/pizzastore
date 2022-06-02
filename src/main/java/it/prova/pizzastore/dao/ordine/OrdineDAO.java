package it.prova.pizzastore.dao.ordine;

import java.util.List;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	public List<Ordine> findByExample(Ordine example);
}
