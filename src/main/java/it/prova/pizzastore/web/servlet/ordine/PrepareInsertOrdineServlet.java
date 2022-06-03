package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareInsertOrdineServlet")
public class PrepareInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("insert_ordine_attr", new Ordine());
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());
			request.setAttribute("pizze_list_attribute", MyServiceFactory.getPizzaServiceInstance().listAllElements());
			request.setAttribute("clienti_list_attribute", MyServiceFactory.getClienteServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
	}

}
