package filtres;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import Modele.Sessions;

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
		// Si la sessions n'est pas enregistr�e, retour au formulaire de connexion
		if (!Sessions.estConnecte(session)) {
			httpReq.getSession().setAttribute("msgErr", "Vous n'�tes pas connect�");
			RequestDispatcher dispatch = httpReq.getRequestDispatcher("index.jsp");
			dispatch.forward(httpReq, response);
		}
		
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
