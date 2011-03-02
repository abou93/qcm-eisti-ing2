package Controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import Modele.UtilisateurManager;


/**
 * Servlet implementation class ControleurResultats
 */
public class ControleurResultats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurResultats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/* Si l'utilisateur est un prof on affiche les utilisateurs ... */
		if ((Boolean)request.getSession().getAttribute("estProf")) {
			String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats")).list();
			ArrayList<String> liste = new ArrayList<String>();
			
			/* Pour chaque dossier trouvé, on vérifie si son nom est bien
			 * celui d'un utilisateur de la liste. Si c'est le cas on
			 * l'ajoute à la liste que l'on enverra à la vue.
			 */
			for (int i = 0; i < s.length; i++) {
				if (UtilisateurManager.getUser(s[i]) == null) continue;
				liste.add(s[i]);
			}
			request.getSession().setAttribute("liste", liste);
		}
		/* Si l'utilisateur est un étudiant on affiche ses résultats */
		else {
			ServletContext context = getServletContext();
			String urlUser = context.getRealPath(File.separator + "Data" +
					File.separator + "Resultats");
			urlUser += File.separator + (String)request.getSession().getAttribute("user");
			/* TODO urlUser contient l'adresse du dossier de reponses de 
			 * l'utilisateur. Il faut parser le fichier Resultats.xml pour
			 * pouvoir le mettre dans un objet, et envoyer cet objet à la
			 * vue pour affichage et sélection du résultat.
			 */
			/*
			 * Pour les tests avant la mise en place du parsage, on utilise
			 * une liste contenant le nom des fichiers xml contenus dans le
			 * dossier.
			 */
			String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats" + File.separator + 
					request.getSession().getAttribute("user"))).list();
			ArrayList<String> liste = new ArrayList<String>();
			
			for (int i = 0; i < s.length; i++) {
				if (s[i].endsWith(".xml")==false) continue;
				liste.add(s[i]);
			}
			request.getSession().setAttribute("liste", liste);
			/* Fin du code utilisé pour le test */
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Si c'est un prof et que "prof", on vient d'afficher les étudiants, il faut
		 * afficher les résultats pour que le prof choisisse */
		if (((Boolean)request.getSession().getAttribute("estProf")) && (request.getSession().getAttribute("etuTrouve") == null)) {
			// 
		}
		/* Sinon, on doit afficher le résultat sélectionné */
		else {
			
		}
		
		String urlFicUser = (String)request.getSession().getAttribute("choix");
		System.out.println(urlFicUser);
	}

}
