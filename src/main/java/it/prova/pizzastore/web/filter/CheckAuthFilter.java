package it.prova.pizzastore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.auth.Utente;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = { "login.jsp", "/LoginServlet", "/LogoutServlet", "/css/", "/js/" };
	private static final String[] ADMIN_URLS = { "/cliente/", "index.jsp" };
	private static final String[] PIZZAIOLO_URLS = { "/ordine/", "/pizza/", "index.jsp"};
	private static final String[] FATTORINO_URLS = {"index.jsp", "ExecuteFattorinoListServlet",
			"/fattorino/list.jsp", "PrepareFattorinoDeleteServlet", "ExecuteFattorinoDeleteServlet", "ExecuteFattorinoShowServlet",
			"/fattorino/delete.jsp", "/fattorino/show.jsp" };

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String pathAttuale = httpRequest.getServletPath();

		if (!isPathInWhiteList(pathAttuale)) {
			Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");

			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}

			if (isAdminPath(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
				return;
			}

			if (isPizzaioloPath(pathAttuale) && !utenteInSession.isPizzaiolo()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
				return;
			}

			if (isFattorinoPath(pathAttuale) && !utenteInSession.isFattorino()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAdminPath(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPizzaioloPath(String requestPath) {
		for (String urlPatternItem : PIZZAIOLO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isFattorinoPath(String requestPath) {
		for (String urlPatternItem : FATTORINO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}