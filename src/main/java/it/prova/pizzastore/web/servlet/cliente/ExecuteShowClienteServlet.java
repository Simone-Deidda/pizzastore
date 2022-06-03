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

@WebServlet("/ExecuteShowClienteServlet")
public class ExecuteShowClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idClienteParam = request.getParameter("idCliente");
		if (!NumberUtils.isCreatable(idClienteParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Cliente clienteInstance = MyServiceFactory.getClienteServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idClienteParam));

			if (clienteInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListClienteServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("show_cliente_attr", clienteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/cliente/show.jsp").forward(request, response);
	}

}
