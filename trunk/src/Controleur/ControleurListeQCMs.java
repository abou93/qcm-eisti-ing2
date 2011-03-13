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

import DAO.HibernateUtil;
import Modele.Modele;
import Modele.Sessions;
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
					request.getSession().setAttribute("temps", m.getQCMCourant().getTemps());
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
		
        if (!init) {
        	/* Initialisation des utilisateurs à l'arrache, sans passer par oracle
			init = true;
			new Utilisateur("rachou", "123", 0);
			new Utilisateur("stitch", "456", 1);
			new Utilisateur("michou", "789", 1);
			new Utilisateur("fransou", "000", 0);
			*/
        	//try {
        		init = true;
        		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        		session.beginTransaction();
        		List result = session.createQuery("from Utilisateur").list(); // requête HQL
        		session.getTransaction().commit();
        		HibernateUtil.getSessionFactory().close();
        		
        		UtilisateurManager.creerListe (result);
        		
        		for (int i = 0; i < result.size(); i++) {
        			System.out.println(result.get(i));
        			//UtilisateurManager.addUser((Utilisateur)result.get(i));
        		}
        	/*}
        	catch (Exception e) {
        		System.err.println("Erreur hibernate : " + e);
        	}*/
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
