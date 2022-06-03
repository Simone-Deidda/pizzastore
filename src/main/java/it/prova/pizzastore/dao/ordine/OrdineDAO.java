package it.prova.pizzastore.dao.ordine;

import java.util.List;
import java.util.Optional;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	public List<Ordine> findByExample(Ordine example);

	public Integer getSumPrezziPizze(Ordine ordineInstance);

	public Optional<Ordine> findOneEager(long parseLong);
}
