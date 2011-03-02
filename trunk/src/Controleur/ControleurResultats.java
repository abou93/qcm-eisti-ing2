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
		System.out.println("coucou !!");
		String [] s;
		ArrayList<String> liste = new ArrayList<String> ();
		if ((Boolean)request.getSession().getAttribute("estProf")) {
			System.out.println("c'est un prof, on affiche la liste des utilisateurs");
			s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats")).list();
			for (int i = 0; i < s.length; i++) {
				if (UtilisateurManager.getUser(s[i]) == null) continue;
				liste.add(s[i]);
				//System.out.println(s[i]);
			}
		}
		else {
			System.out.println("c'est un étudiant, on affiche la liste des QCM");
			s = new File(getServletContext().getRealPath(File.separator + "Data" + 
					File.separator + "Resultats" + File.separator + 
					request.getSession().getAttribute("user"))).list();
			for (int i = 0; i < s.length; i++) {
				liste.add(s[i]);
			}
		}
		request.getSession().setAttribute("liste", liste);
		RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("coucou");
	}

}
