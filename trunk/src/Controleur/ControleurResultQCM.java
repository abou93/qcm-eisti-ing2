package Controleur;

import java.io.File;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.DAOBase;
import Modele.Modele;
import Modele.QCM;
import Modele.UtilisateurManager;

/**
 * Servlet implementation class ControleurResultQCM
 */
public class ControleurResultQCM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurResultQCM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		Modele m;
		try{
			String ordre = request.getParameter("ordre");
			if(ordre.matches("Retour"))
			{
				request.getSession().removeAttribute("QCM");
				RequestDispatcher dispatch = request.getRequestDispatcher("ControleurListeQCMs");
				dispatch.forward(request, response);
			}
			if(ordre.matches("Enregistrer"))
			{
				try{
					QCM q = (QCM) request.getSession().getAttribute("QCM");
					System.out.println("user="+(String) request.getSession().getAttribute("user"));
					System.out.println("id_user="+UtilisateurManager.getId((String) request.getSession().getAttribute("user")));
					DAOBase.saveResultat(q, UtilisateurManager.getId((String) request.getSession().getAttribute("user")));
					RequestDispatcher dispatch = request.getRequestDispatcher("ControleurListeQCMs");
					dispatch.forward(request, response);
				}
				catch(Exception e){
					System.out.println(e.toString());
					RequestDispatcher dispatch = request.getRequestDispatcher("AffichageErreurEnregistrement.jsp");
					dispatch.forward(request, response);
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
		doGet(request, response);
	}

}
