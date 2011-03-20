package Controleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.classic.Session;

import DAO.DAOBase;
import DAO.HibernateUtil;
import Modele.Cours;
import Modele.QCM;
import Modele.Sessions;
import Modele.Utilisateur;
import Modele.UtilisateurManager;

/**
 * Servlet implementation class controleurListeQCMs
 */
public class ControleurListeQCMs extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurListeQCMs() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    static {	
		UtilisateurManager.creerListe (DAOBase.getListUsers());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* On supprimer le fichier temporaire (si il a déjà été créé ici, c'est que
		 * le temps a été entièrement écoulé)
		 */
		File tmp = (File)request.getSession().getAttribute("tmp");
		if (tmp != null) {
			tmp.delete();
			request.getSession().removeAttribute("tmp");
		}
		try{
			
			String qcm = request.getParameter("QCM");
			if(qcm == null || qcm.matches(""))
			{
				List<Cours> lc = DAOBase.getListCours();
				for (int i=0; i<lc.size() ; i++)
				{
					java.util.List<QCM> lqcm = DAOBase.getMesQCMs(lc.get(i));
					for(int j=0; j<lqcm.size() ; j++)
					{
						lqcm.get(j).readXML();
						lc.get(i).addQCM(lqcm.get(j));
					}
				}			
				request.getSession().setAttribute("ListCours", lc);
				RequestDispatcher dispatch = request.getRequestDispatcher("accueil.jsp");
				dispatch.forward(request, response);
			}
			else
			{
				try{
					int id = Integer.parseInt(request.getParameter("QCM"));
					QCM q = DAOBase.getQCM(id);
					q.readXML();
					request.setAttribute("QCM", q);
					
					request.getSession().setAttribute("temps", q.getTemps());
					// Création d'un fichier temporaire contenant l'heure d'affichage de la page
					tmp = File.createTempFile("nom",".txt");
					FileWriter fout =new FileWriter(tmp);
					Date date = new Date();
					fout.write(String.valueOf(date.getTime()));
					fout.close();
					request.getSession().setAttribute("tmp", tmp);
					request.getSession().setAttribute("id_qcm", id);
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
		/* Si on vient d'upload un fichier, on est rediriger vers l'accueil */
		if(((String)request.getAttribute("upload")) != null
				&& ((String)request.getAttribute("upload")).matches("true"))
		{
			doGet(request, response);
		}
		
		
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
					getUserPassword().equals(request.getParameter("pwd"))) {
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
				request.getSession().removeAttribute("temps");
				request.getSession().removeAttribute("tps");
				request.getSession().removeAttribute("user");
				// regénérer l'id de session
				request.getSession().invalidate();
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		}
	}

}
