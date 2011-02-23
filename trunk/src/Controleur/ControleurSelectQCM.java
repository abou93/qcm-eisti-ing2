package Controleur;

import Modele.*;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Servlet implementation class Controleur
 */
public class ControleurSelectQCM extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurSelectQCM() {
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
			//InputStream ips = context.getResourceAsStream("/QCMs/QCMs.xml");
			if ((Modele)request.getSession().getAttribute("m") == null)
			{
				m = new Modele(urlData);
			}
			else
			{
				m = (Modele)request.getSession().getAttribute("m");
			}
			if(verification(request))
			{
				String qcm = request.getParameter("Submit");
				qcm = qcm.substring(qcm.length());
				request.getSession().setAttribute("m", m);
				request.getRequestDispatcher("AffichageResultQCM.jsp").forward(request, response);
			}
			else
			{
				String qcm = request.getParameter("Submit");
				qcm = qcm.substring(qcm.length());
				request.getSession().removeAttribute("m");
				request.getRequestDispatcher("AffichageErreurQCM.jsp").forward(request, response);			
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
	
	private boolean verification (HttpServletRequest request) {
		for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++) {
			if (request.getParameter("q" + i)==null) return false;
		}
		return true;
	}

}
