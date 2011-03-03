package Controleur;

import Modele.*;

import java.io.*;
import java.util.*;

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
		// On r�cup�re le temps �coul� dans une chaine de caract�re
		String tps = request.getParameter("temps");
		// On vide �ventuellement le(s) pr�c�dent(s) message(s) d'erreur
		request.getSession().removeAttribute("msgErr");
		long diff = -1;
		if (tps != null) {
			// s'il a �t� initialis�, on r�cup�re le fichier temporaire dans lequel
			// on a stock� la date (en milisecondes) de d�but du QCM
			File tmp = (File)request.getSession().getAttribute("tmp");
			if (tmp != null) {
				try {
					FileReader fin =new FileReader(tmp);
					BufferedReader br = new BufferedReader(fin);
					diff = (new Date()).getTime() - Long.parseLong(br.readLine());
					diff = diff / 1000;
					
					br.close();
					tmp.delete();
				}
				catch (Exception e) {
					System.err.println("ERREUR !!! relancer l'application");
					tmp.delete();
				}
				System.out.println("vous avez mis " + (300 - Integer.parseInt(tps)) + " secondes.");
				System.out.println("l'ordinateur dit que vous avez mis " + 
						String.valueOf(diff) + " secondes.");
			}
			if (!tps.equals("ecoule")) {
				int temps = 300 - Integer.parseInt(tps);
				if (temps == diff) {
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
							}
						}
						m.evaluerScoreQCM();
						request.getSession().setAttribute("m", m);
						request.getRequestDispatcher("AffichageResultQCM.jsp").forward(request, response);
					}
					catch (Exception e){
						System.out.println(e.toString());
					}
				}
				else {
					// L'utilisateur a trich� !!!
					request.getSession().setAttribute("msgErr", "Vous avez lamentablement " +
							"essay� de tricher au pr�c�dent QCM. C'est minable.");
					request.getRequestDispatcher("accueil.jsp").forward(request, response);
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
