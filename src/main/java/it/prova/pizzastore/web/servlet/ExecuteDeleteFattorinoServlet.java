package it.prova.pizzastore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteFattorinoServlet")
public class ExecuteDeleteFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idOrdineParam = request.getParameter("idOrdine");
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		String idUserParam = request.getParameter("idUser");
		if (!NumberUtils.isCreatable(idUserParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getOrdineServiceInstance().rimuovi(Long.parseLong(idOrdineParam));
			request.setAttribute("ordini_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().listAllByUserId(Long.parseLong(idUserParam)));
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/fattorino/list.jsp").forward(request, response);
	}

}
