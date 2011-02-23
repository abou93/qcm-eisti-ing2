package Controleur;

import java.io.*;
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
			String qcmName = request.getParameter("QCM");
			if(qcmName == null)
			{
				m = new Modele(urlData);
				request.getSession().setAttribute("m", m);
				RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
				dispatch.forward(request, response);
			}
			else
			{
				try{
					urlData = context.getRealPath("/QCMs/"+qcmName+".xml");
					m.initialiser(qcmName, urlData);
					request.getSession().setAttribute("m", m);
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
		doGet(request, response);
	}

}
