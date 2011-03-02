package Controleur;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Modele.*;

/**
 * Servlet implementation class controleurListeQCMs
 */
public class ControleurListeQCMs extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static boolean init = false; // pour savoir si on a créé les users de test
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurListeQCMs() {
        // TODO Auto-generated constructor stub
        super();
        if (!init) {
			init = true;
			new Utilisateur("rachou", "123", false);
			new Utilisateur("stitch", "456", true);
			new Utilisateur("michou", "789", true);
			new Utilisateur("fransou", "000", false);
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		Modele m;
		try{
			String urlData = context.getRealPath("/QCMs/QCMs.xml");
			if ((Modele)request.getSession().getAttribute("m") == null)
			{
				m = new Modele(urlData);
			}
			else
			{
				m = (Modele)request.getSession().getAttribute("m");
			}
			String qcm = request.getParameter("QCM");
			if(qcm == null || qcm.matches(""))
			{
				//m = new Modele(urlData);
				request.getSession().setAttribute("m", m);
				RequestDispatcher dispatch = request.getRequestDispatcher("accueil.jsp");
				dispatch.forward(request, response);
			}
			else
			{
				try{
					urlData = context.getRealPath("/QCMs/"+qcm+".xml");
					m.initialiser(qcm, urlData);
					request.getSession().setAttribute("m", m);
					RequestDispatcher dispatch = request.getRequestDispatcher("AffichageQCM.jsp");
					dispatch.forward(request, response);
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// On peut accéder ici en ayant cliqué sur "login" ou "logout", le 1er
		// test permet de savoir d'où l'on vient.
		
		String login = request.getParameter("Login");
		// Si on a fait login...
		if (login != null) {
			// Si l'utilisateur est inconnu...
			if (UtilisateurManager.getUser(request.getParameter("login")) == null) {
				request.getSession().setAttribute("msgErr", "Login inconnu");
				RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
				dispatch.forward(request, response);
			}
			// Si le password ne correspond pas...
			else if (!UtilisateurManager.getUser(request.getParameter("login")).
					getPassword().equals(request.getParameter("pwd"))) {
				request.getSession().setAttribute("msgErr", "Password incorrect");
				RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
				dispatch.forward(request, response);
			}
			// Si tout va bien...
			else {
				if (!Sessions.estConnecte(request.getRequestedSessionId()))
					Sessions.connecter(request.getRequestedSessionId());
				request.getSession().setAttribute("estProf", UtilisateurManager.estProf(request.getParameter("login")));
				request.getSession().setAttribute("user", request.getParameter("login"));
				doGet(request, response);
			}
		}
		// Si on a fait logout...
		else {
			// Si la session est enregistrée on la supprime
			if (Sessions.estConnecte(request.getRequestedSessionId())) {
				Sessions.deconnecter(request.getRequestedSessionId());
				request.getSession().removeAttribute("user");
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		}
	}

}
