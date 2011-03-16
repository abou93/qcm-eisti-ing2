package Controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOBase;
import Modele.QCM;

public class ControleurAffichageQCM extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAffichageQCM() {
        // TODO Auto-generated constructor stub
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Yoo!!!");
		int id = Integer.parseInt((String)request.getAttribute("QCM"));
		QCM q = DAOBase.getQCM(id);
		request.setAttribute("QCM", q);
		RequestDispatcher dispatch = request.getRequestDispatcher("AffichageQCM.jsp");
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}