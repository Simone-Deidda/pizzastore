package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import it.prova.pizzastore.model.auth.Ruolo;
import it.prova.pizzastore.model.auth.Utente;


public interface UtenteDAO extends IBaseDAO<Utente> {

	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception;

	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws Exception;

	public Optional<Utente> login(String username, String password) throws Exception;

}
