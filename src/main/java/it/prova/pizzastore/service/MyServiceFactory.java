package it.prova.pizzastore.service;

import it.prova.pizzastore.dao.RuoloDAO;
import it.prova.pizzastore.dao.RuoloDAOImpl;
import it.prova.pizzastore.dao.UtenteDAO;
import it.prova.pizzastore.dao.UtenteDAOImpl;
import it.prova.pizzastore.dao.cliente.ClienteDAO;
import it.prova.pizzastore.dao.cliente.ClienteDAOImpl;
import it.prova.pizzastore.dao.ordine.OrdineDAO;
import it.prova.pizzastore.dao.ordine.OrdineDAOImpl;
import it.prova.pizzastore.dao.pizza.PizzaDAO;
import it.prova.pizzastore.dao.pizza.PizzaDAOImpl;
import it.prova.pizzastore.service.cliente.ClienteService;
import it.prova.pizzastore.service.cliente.ClienteServiceImpl;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.service.ordine.OrdineServiceImpl;
import it.prova.pizzastore.service.pizza.PizzaService;
import it.prova.pizzastore.service.pizza.PizzaServiceImpl;

public class MyServiceFactory {
	private static UtenteService UTENTE_SERVICE_INSTANCE = null;
	private static RuoloService RUOLO_SERVICE_INSTANCE = null;
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;
	
	private static ClienteService CLIENTE_SERVICE_INSTANCE = null;
	private static ClienteDAO CLIENTE_DAO_INSTANCE = null;
	private static OrdineService ORDINE_SERVICE_INSTANCE = null;
	private static OrdineDAO ORDINE_DAO_INSTANCE = null;
	private static PizzaService PIZZA_SERVICE_INSTANCE = null;
	private static PizzaDAO PIZZA_DAO_INSTANCE = null;
	
	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);
		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		return RUOLO_SERVICE_INSTANCE;
	}
	
	public static ClienteService getClienteServiceInstance() {
		if (CLIENTE_SERVICE_INSTANCE == null)
			CLIENTE_SERVICE_INSTANCE = new ClienteServiceImpl();

		if (CLIENTE_DAO_INSTANCE == null)
			CLIENTE_DAO_INSTANCE = new ClienteDAOImpl();

		CLIENTE_SERVICE_INSTANCE.setClienteDAO(CLIENTE_DAO_INSTANCE);
		return CLIENTE_SERVICE_INSTANCE;
	}
	
	public static OrdineService getOrdineServiceInstance() {
		if (ORDINE_SERVICE_INSTANCE == null)
			ORDINE_SERVICE_INSTANCE = new OrdineServiceImpl();

		if (ORDINE_DAO_INSTANCE == null)
			ORDINE_DAO_INSTANCE = new OrdineDAOImpl();

		ORDINE_SERVICE_INSTANCE.setOrdineDAO(ORDINE_DAO_INSTANCE);
		return ORDINE_SERVICE_INSTANCE;
	}
	
	public static PizzaService getPizzaServiceInstance() {
		if (PIZZA_SERVICE_INSTANCE == null)
			PIZZA_SERVICE_INSTANCE = new PizzaServiceImpl();

		if (PIZZA_DAO_INSTANCE == null)
			PIZZA_DAO_INSTANCE = new PizzaDAOImpl();

		PIZZA_SERVICE_INSTANCE.setPizzaDAO(PIZZA_DAO_INSTANCE);
		return PIZZA_SERVICE_INSTANCE;
	}
}
