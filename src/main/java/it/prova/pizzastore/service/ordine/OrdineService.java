package it.prova.pizzastore.service.ordine;

import java.util.List;

import it.prova.pizzastore.dao.ordine.OrdineDAO;
import it.prova.pizzastore.model.Ordine;


public interface OrdineService {
	public List<Ordine> listAllElements() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdineToRemove) throws Exception;

	public List<Ordine> findByExample(Ordine example) throws Exception;
	
	public void inizializzaCostoTotaleOrdine(Ordine ordineInstance) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDAO ordineDAO);

}
