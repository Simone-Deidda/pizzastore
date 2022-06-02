package it.prova.pizzastore.dao;

import it.prova.pizzastore.model.auth.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {

	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
