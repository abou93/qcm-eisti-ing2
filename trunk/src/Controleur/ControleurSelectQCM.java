package Controleur;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.Modele;
import Modele.Resultat;


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
		String tps = request.getParameter("temps");
		if (tps != null) {
			if (!tps.equals("ecoule")) {
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
			for (int i = 0; i < m.getQCMCourant().getNbQuestions(); i++)
			{
				for (int j = 0; j < m.getQCMCourant().getQuestion(i).getNbReponses(); j++)
				{
					if (request.getParameter("q" + i + "r" + j) == null)
					{
						m.setReponse(i, j, false);
					}
					else
					{
						if (!(request.getParameter("q" + i + "r" + j).matches("")))
							m.setReponse(i, j, true);
					}
				catch (Exception e){
					System.out.println(e.toString());
				}
			}
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
