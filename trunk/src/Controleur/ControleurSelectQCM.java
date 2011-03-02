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
				Resultat r;
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
					System.err.println("verif");
					String qcm = request.getParameter("ordre");
					r = new Resultat(m.getQCMCourant());
					for (int i = 0; i < r.getQcmResultat().getNbQuestions(); i++)
					{
						for (int j = 0; j < r.getQcmResultat().getQuestion(i).getNbReponses(); j++)
						{
							if (request.getParameter("q" + i + "r" + j) == null)
							{
								r.setReponse(i, j, false);
								System.out.println("q" + i + " r" + j + ": null");
							}
							else
							{
								System.out.println("q" + i + " r" + j + ": " + request.getParameter("q" + i + "r" + j));
								if (!(request.getParameter("q" + i + "r" + j).matches("")))
									r.setReponse(i, j, true);
							}
						}
					}
					System.out.println("\n resultat: ");
					for (int i = 0; i < r.getQcmResultat().getNbQuestions(); i++)
					{
						for (int j = 0; j < r.getQcmResultat().getQuestion(i).getNbReponses(); j++)
						{
							System.out.print(r.getQcmResultat().getQuestion(i).getReponse(j).getExpression());
							System.out.print(r.getQcmResultat().getQuestion(i).getReponse(j).isSelect());
							System.out.println(r.getQcmResultat().getQuestion(i).getReponse(j).isTrue());
						}
					}
					m.addResultat(r);
					request.getSession().setAttribute("m", m);
					request.getRequestDispatcher("AffichageResultQCM.jsp").forward(request, response);
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
	
	/*private boolean verification (Modele m, HttpServletRequest request) {
		boolean correct = false;
		for (int i = 0; i < m.getQCMCourant().getNbQuestions(); i++)
		{
			for (int j = 0; j < m.getQCMCourant().getQuestion(i).getNbReponses(); j++)
			{
				if (request.getParameter("q" + i + "r" + j) != null)
				{
					if (!(request.getParameter("q" + i + "r" + j).matches("")))
						correct = true;
				}
			}
			if (correct == false)
			{				
				return false;
			}
		}
		return correct;
	}*/

}
