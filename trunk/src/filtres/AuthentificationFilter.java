package filtres;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import Modele.*;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
public class AuthentificationFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public AuthentificationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpReq = (HttpServletRequest) request;
		String session = httpReq.getRequestedSessionId();
		// Si la sessions n'est pas enregistrée, retour au formulaire de connexion
		if (!Sessions.estConnecte(session)) {
			System.out.println("pas connecté");
			httpReq.getSession().setAttribute("msgErr", "Vous n'êtes pas connecté");
			RequestDispatcher dispatch = httpReq.getRequestDispatcher("index.jsp");
			dispatch.forward(httpReq, response);
		}
		else
			System.out.println("connecté");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
