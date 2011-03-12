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
				
			/* Si on n'a pas choisi l'utilisateur dont on veut afficher les résultats */
			if (request.getParameter("choixUser")==null) {
				ArrayList<String> liste = new ArrayList<String>();
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
				request.getSession().setAttribute("listeUsers", liste);
			}
			/* Si on a déjà choisi l'utilisateur et qu'on veut le choix du résultat */
			else {
				ArrayList<String> liste = new ArrayList<String>();
				String user = request.getParameter("choixUser");
				String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
						File.separator + "Resultats" + File.separator + user)).list();
				
				/* On transforme le tableau en liste
				 */
				for (int i = 0; i < s.length; i++) {
					if (s[i].equals("Resultats.xml")) continue; // c'est la banque de résultats
					if (s[i].endsWith(".xml")==false) continue; // ce n'est pas un fichier xml (svn ou autre)
					liste.add(s[i].substring(0,s[i].length()-4));
				}
				request.getSession().setAttribute("choixUser", user);
				request.getSession().setAttribute("liste", liste);
				request.getSession().setAttribute("choix", null);
			}
			
			if(request.getParameter("choix") != null)
			{
				/* On veut décocher le choix*/
				if((request.getSession().getAttribute("choix")!=null)
					&&
					((String)request.getParameter("choix")).matches((String) request.getSession().getAttribute("choix")))
				{
					request.getSession().setAttribute("choix", null);
				}
				/* On veut afficher le choix*/
				else
				{
					String nomFichierXML = (String) request.getParameter("choix");
					String nameUser = (String) request.getSession().getAttribute("choixUser");
					String urlResult = context.getRealPath("/Data/Resultats/"+nameUser+"/"+nomFichierXML+".xml");
					m.chargerResultat(nomFichierXML, urlResult);
					
					request.getSession().setAttribute("choixUser", nameUser);
					request.getSession().setAttribute("choix", nomFichierXML);
					request.getSession().setAttribute("m", m);
				}
			}
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
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
		ServletContext context = getServletContext();
		Modele m;
		String urlData = context.getRealPath("/Data/QCMs/QCMs.xml");
		if ((Modele)request.getSession().getAttribute("m") == null)
		{
			m = new Modele(urlData);
		}
		else
		{
			m = (Modele)request.getSession().getAttribute("m");
		}
		// on n'a pas choisi le résultat
		if (request.getParameter("choix")== null) {
			ArrayList<String> listeUsers = new ArrayList<String>();
			ArrayList<String> liste = new ArrayList<String>();
			String user = (String)request.getSession().getAttribute("user");
			listeUsers.add(user);
			String [] s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats" + File.separator + user)).list();
			
			if(s != null)
			{
				/* On transforme le tableau en liste */
				for (int i = 0; i < s.length; i++) {
					if (s[i].equals("Resultats.xml")) continue; // c'est la banque de résultats
					if (s[i].endsWith(".xml")==false) continue; // ce n'est pas un fichier xml (svn ou autre)
					liste.add(s[i].substring(0,s[i].length()-4));
				}
			}
			request.getSession().setAttribute("choixUser", user);
			request.getSession().setAttribute("choix", null);
			request.getSession().setAttribute("liste", liste);
			request.getSession().setAttribute("listeUsers", listeUsers);
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
		// on a choisi le résultat
		else {
			if((request.getSession().getAttribute("choix")!=null)
					&&
					((String)request.getParameter("choix")).matches((String) request.getSession().getAttribute("choix")))
			{
				request.getSession().setAttribute("choix", null);
			}
			else
			{
				String nomFichierXML = (String) request.getParameter("choix");
				String nameUser = (String) request.getSession().getAttribute("choixUser");
				String urlResult = context.getRealPath("/Data/Resultats/"+nameUser+"/"+nomFichierXML+".xml");
				m.chargerResultat(nomFichierXML, urlResult);
				
				request.getSession().setAttribute("choixUser", nameUser);
				request.getSession().setAttribute("choix", nomFichierXML);
				request.getSession().setAttribute("m", m);
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
	}

}
