package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteSearchOrdineServlet")
public class ExecuteSearchOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceParam = request.getParameter("codice");
		String dataParam = request.getParameter("data");

		String utenteIdParam = request.getParameter("utente.id");
		String clienteIdParam = request.getParameter("cliente.id");
		String[] pizzeIdParam = request.getParameterValues("pizza.ids");

		Ordine example = new Ordine();
		
		try {
			example = UtilityForm.initializeOrdineFromParams(codiceParam, dataParam, clienteIdParam, pizzeIdParam, utenteIdParam);
			
			example.getSommaPrezziPizza();
			request.setAttribute("ordini_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/ordine/list.jsp").forward(request, response);
	}

}
