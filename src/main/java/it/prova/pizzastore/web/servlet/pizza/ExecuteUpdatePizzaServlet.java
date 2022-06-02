package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzoBase");
		Pizza pizzaToBeUpdated = UtilityForm.initializePizzaFromParams(nomeParam, descrizioneParam, ingredientiParam,
				prezzoBaseParam);

		String idPizzaParam = request.getParameter("idPizza");
		if (!NumberUtils.isCreatable(idPizzaParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		pizzaToBeUpdated.setId(Long.parseLong(idPizzaParam));
		
		if (!UtilityForm.validatePizzaBean(pizzaToBeUpdated)) {
			request.setAttribute("update_pizza_attr", pizzaToBeUpdated);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaToBeUpdated);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzaServlet?operationResult=SUCCESS");
	}

}
