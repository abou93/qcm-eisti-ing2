package Controleur;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.Modele;
import Modele.Sessions;
import Modele.Utilisateur;
import Modele.UtilisateurManager;

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
		/* On supprimer le fichier temporaire (si il a déjà été créé ici, c'est que
		 * le temps a été entièrement écoulé)
		 */
		File tmp = (File)request.getSession().getAttribute("tmp");
		if (tmp != null) {
			tmp.delete();
			request.getSession().removeAttribute("tmp");
		}
		try{
			String urlData = context.getRealPath("/Data/QCMs/QCMs.xml");
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
				m = new Modele(urlData);
				request.getSession().setAttribute("m", m);
				RequestDispatcher dispatch = request.getRequestDispatcher("accueil.jsp");
				dispatch.forward(request, response);
			}
			else
			{
				try{
					urlData = context.getRealPath("/Data/QCMs/"+qcm+".xml");
					m.chargerQCM(qcm, urlData);
					request.getSession().setAttribute("m", m);
					// Création d'un fichier temporaire contenant l'heure d'affichage de la page
					tmp = File.createTempFile("nom",".txt");
					//tmp.deleteOnExit();
					System.out.println("fichier tmp créé : " + tmp.getName());
					FileWriter fout =new FileWriter(tmp);
					Date date = new Date();
					fout.write(String.valueOf(date.getTime()));
					System.out.println(String.valueOf(date.getTime()) + " écrit dans le fichier");
					fout.close();
					request.getSession().setAttribute("tmp", tmp);
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
				request.getSession().removeAttribute("msgErr");
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
				// supprimer tous les attributs créés pendant une execution
				request.getSession().removeAttribute("choixUser");
				request.getSession().removeAttribute("fini");
				request.getSession().removeAttribute("fin");
				request.getSession().removeAttribute("m");
				request.getSession().removeAttribute("estProf");
				request.getSession().removeAttribute("msgErr");
				request.getSession().removeAttribute("liste");
				request.getSession().removeAttribute("user");
				// régénérer l'id de session
				request.getSession().invalidate();
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		}
	}

}
