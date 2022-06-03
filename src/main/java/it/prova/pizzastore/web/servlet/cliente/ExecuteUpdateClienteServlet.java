package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdateClienteServlet")
public class ExecuteUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		Cliente clienteToBeUpdated = UtilityForm.initializeClienteFromParams(nomeParam, cognomeParam, indirizzoParam);

		String idClienteParam = request.getParameter("idCliente");
		if (!NumberUtils.isCreatable(idClienteParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		clienteToBeUpdated.setId(Long.parseLong(idClienteParam));
		
		if (!UtilityForm.validateClienteBean(clienteToBeUpdated)) {
			request.setAttribute("update_cliente_attr", clienteToBeUpdated);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getClienteServiceInstance().aggiorna(clienteToBeUpdated);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListClienteServlet?operationResult=SUCCESS");
	}

}
