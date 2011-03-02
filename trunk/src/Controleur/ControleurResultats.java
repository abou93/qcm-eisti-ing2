package Controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import Modele.*;


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
    /*
     * doGet communique avec un utilisateur professeur
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// on n'a pas fini les choix d'utilisateur et de résultat
		ServletContext context = getServletContext();
		Modele m;
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
			if (request.getParameter("fini")==null)
			{
				ArrayList<String> liste = new ArrayList<String>();
				/* Si on n'a pas choisi l'utilisateur dont on affiche les résultats */
				if (request.getParameter("choix")==null) {
					/* Permet de lister le contenu du dossier */
					String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
							File.separator + "Resultats")).list();
					
					/* Pour chaque dossier trouvé, on vérifie si son nom est bien
					 * celui d'un utilisateur de la liste. Si c'est le cas on
					 * l'ajoute à la liste que l'on enverra à la vue.
					 */
					for (int i = 0; i < s.length; i++) {
						if (UtilisateurManager.getUser(s[i]) == null) continue;
						liste.add(s[i]);
					}
				}
				/* Si on a déjà choisi l'utilisateur et qu'on veut le choix du résultat */
				else {
					String user = request.getParameter("choix");
					String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
							File.separator + "Resultats" + File.separator + user)).list();
					
					/* On transforme le tableau en liste
					 */
					for (int i = 0; i < s.length; i++) {
						if (s[i].equals("Resultats.xml")) continue; // c'est la banque de résultats
						if (s[i].endsWith(".xml")==false) continue; // ce n'est pas un fichier xml (svn ou autre)
						liste.add(s[i].substring(0,s[i].length()-4));
					}
					request.getSession().setAttribute("fin", true);
				}
				request.getSession().setAttribute("liste", liste);
				
				RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
				dispatch.forward(request, response);
			}
			// on a fini les choix, on affiche les resultats
			else {
				String nomFichierXML = (String) request.getSession().getAttribute("choix");
				System.out.println("TODO @ STITCH");
				System.out.println("fichier à afficher : " + nomFichierXML);
				RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
				dispatch.forward(request, response);
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * doPost communique avec un utilisateur simple
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on n'a pas choisi le résultat
		if (request.getParameter("fini")== null) {
			ArrayList<String> liste = new ArrayList<String>();
			String user = (String)request.getSession().getAttribute("user");
			String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats" + File.separator + user)).list();
			
			/* On transforme le tableau en liste */
			for (int i = 0; i < s.length; i++) {
				if (s[i].equals("Resultats.xml")) continue; // c'est la banque de résultats
				if (s[i].endsWith(".xml")==false) continue; // ce n'est pas un fichier xml (svn ou autre)
				liste.add(s[i].substring(0,s[i].length()-4));
			}
			request.getSession().setAttribute("fin", true);
			request.getSession().setAttribute("liste", liste);
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
		// on a choisi le résultat
		else {
			String nomFichierXML = (String) request.getSession().getAttribute("choix");
			System.out.println("TODO @ STITCH");
			System.out.println("fichier à afficher : " + nomFichierXML);
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
	}

}
