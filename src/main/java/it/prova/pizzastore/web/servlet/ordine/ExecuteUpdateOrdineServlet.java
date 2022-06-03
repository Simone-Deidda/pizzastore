package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceParam = request.getParameter("codice");
		String dataParam = request.getParameter("data");

		String utenteIdParam = request.getParameter("utente.id");
		String clienteIdParam = request.getParameter("cliente.id");
		String[] pizzeIdParam = request.getParameterValues("pizza.ids");

		String idOrdineParam = request.getParameter("idOrdine");
		Ordine ordineInstance = new Ordine();
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		try {
			ordineInstance = UtilityForm.initializeOrdineFromParams(codiceParam, dataParam, clienteIdParam,
					pizzeIdParam, utenteIdParam);
			ordineInstance.setId(Long.parseLong(idOrdineParam));
			
			if (!UtilityForm.validateOrdineBean(ordineInstance)) {

				request.setAttribute("insert_ordine_attr", ordineInstance);
				request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());

				request.setAttribute("pizze_list_attribute",
						MyServiceFactory.getPizzaServiceInstance().listAllElements());
				request.setAttribute("clienti_list_attribute",
						MyServiceFactory.getClienteServiceInstance().listAllElements());

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
				return;			}

			ordineInstance.getSommaPrezziPizza();
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/ordine/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
	}

}
