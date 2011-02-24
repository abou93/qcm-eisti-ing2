package Controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.Modele;

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
			String urlData = context.getRealPath("/QCMs/QCMs.xml");
			if ((Modele)request.getSession().getAttribute("m") == null)
			{
				m = new Modele(urlData);
			}
			else
			{
				m = (Modele)request.getSession().getAttribute("m");
			}
			String ordre = request.getParameter("ordre");
			if(ordre.matches("Retour"))
			{
				request.getSession().setAttribute("m", m);
				RequestDispatcher dispatch = request.getRequestDispatcher("ControleurListeQCMs");
				dispatch.forward(request, response);
			}
			if(ordre.matches("Enregistrer"))
			{
				try{
					urlData = context.getRealPath("/QCMs/Resultat.xml");
					m.enregistrer(urlData);
					request.getSession().setAttribute("m", m);
					RequestDispatcher dispatch = request.getRequestDispatcher("ControleurListeQCMs");
					dispatch.forward(request, response);
				}
				catch(Exception e){
					System.out.println(e.toString());
					request.getSession().setAttribute("m", m);
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