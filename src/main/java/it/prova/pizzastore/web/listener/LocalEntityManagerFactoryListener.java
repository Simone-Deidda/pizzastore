package it.prova.pizzastore.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.pizzastore.model.auth.Ruolo;
import it.prova.pizzastore.model.auth.StatoUtente;
import it.prova.pizzastore.model.auth.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;


@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("pizzastore_unit");

			initUsersAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initUsersAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo User", "PIZZAIOLO_ROLE"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino User", "FATTORINO_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Giucas", "Casella", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("pizzaiolo", "pizz") == null) {
			Utente admin = new Utente("pizzaiolo", "pizz", "Gerry", "Scotti", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("fattorino", "fatt") == null) {
			Utente admin = new Utente("fattorino", "fatt", "Carlo", "Conti", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
		}
	}

}