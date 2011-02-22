package Controleur;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import Modele.Modele;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Modele m;
		if ((Modele)request.getSession().getAttribute("m") == null)
		{
	        m = new Modele();
		}
		else
		{
			m = (Modele)request.getSession().getAttribute("m");
		}
		String qcm = request.getParameter("QCM");
		if(qcm == null)
		{ 
			request.getSession().setAttribute("m", m);
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		}
		else
		{
	        m.initialiser(qcm);
			request.getSession().setAttribute("m", m);
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageQCM.jsp");
	        dispatch.forward(request, response);
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
