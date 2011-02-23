package controleur;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Personne;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		// on récupère les données
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String sexe = request.getParameter("sexe");
		String [] transport = request.getParameterValues("transport");
		String cp = request.getParameter("cp");
		
		boolean erreur = false;
		Vector<String> msgErr = new Vector<String>();
		// vérification des données récupérées
		if (nom.equals("")) {
			erreur = true;
			msgErr.add("Veuillez saisir un nom");
		}
		if (prenom.equals("")) {
			erreur = true;
			msgErr.add("Veuillez saisir un prénom");
		}
		if (sexe.equals("")) {
			erreur = true;
			msgErr.add("Veuillez choisir un sexe");
		}
		if (cp.equals("")) {
			erreur = true;
			msgErr.add("Veuillez saisir un code postal");
		}
		try {
			Integer.parseInt(cp);
		}
		catch (NumberFormatException e) {
			erreur = true;
			msgErr.add("Le code postal saisi n'est pas valide");
		}
		
		Personne p = new Personne (nom, prenom, sexe, cp, transport);
		
		if (!erreur) {
			RequestDispatcher dispatch = request.getRequestDispatcher("affichage.jsp");
			request.setAttribute("personne", p);
			dispatch.forward(request, response);
		}
		else {
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			request.setAttribute("personne", p);
			request.setAttribute("erreur", msgErr);
			dispatch.forward(request, response);
		}
		
		
	}

}
