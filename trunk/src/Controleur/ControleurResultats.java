package Controleur;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.DAOBase;
import Modele.*;


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
    /*
     * doGet communique avec un utilisateur professeur
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// on n'a pas fini les choix d'utilisateur et de résultat
		try{
			/* Si on n'a pas choisi l'utilisateur dont on veut afficher les résultats */
			if (request.getParameter("choixUser")==null) {
				ArrayList<String> liste = new ArrayList<String>();
				List<Utilisateur> lu = DAOBase.getListUsers();
//				Vector<Utilisateur> lu = UtilisateurManager.getLesUtilisateurs();
				for(int i=0; i<lu.size() ; i++)
				{
					liste.add(lu.get(i).getUserLogin());
				}
				request.getSession().setAttribute("listeUsers", liste);
//				request.getSession().setAttribute("choix", null);
			}
			/* Si on a déjà choisi l'utilisateur et qu'on veut le choix du résultat */
			else {	
				ArrayList<String> liste = new ArrayList<String>();
				ArrayList<Integer> listeId = new ArrayList<Integer>();
				String user = request.getParameter("choixUser");
				int id_user = UtilisateurManager.getId(user);
				List<Cours> lc = DAOBase.getListCoursResultat(id_user);
				
				for(int i=0; i<lc.size() ; i++)
				{
					for(int j=0; j<lc.get(i).getLesQCMs().size() ; j++)
					{
						liste.add(lc.get(i).getNom()+" - Difficulté : "
								+ Integer.toString(lc.get(i).getLesQCMs().get(j).getDifficulte())
								+ " ("+new SimpleDateFormat("dd/MM/yyyy HH:ss").format(lc.get(i).getLesQCMs().get(j).getDate())
								+ ")");
						listeId.add(lc.get(i).getLesQCMs().get(j).getId());
					}
				}
				request.getSession().setAttribute("choixUser", user);
				request.getSession().setAttribute("liste", liste);
				request.getSession().setAttribute("listeId", listeId);
				request.getSession().setAttribute("choix", null);
			}
			
			if(request.getParameter("choix") != null)
			{
				/* On veut décocher le choix*/
				if((request.getSession().getAttribute("choix")!=null)
						&&
						( Integer.parseInt(request.getParameter("choix")) == (Integer) request.getSession().getAttribute("choix")))
				{
					request.getSession().setAttribute("choix", null);
				}
				/* On veut afficher le choix*/
				else
				{
					int id_qcm = Integer.parseInt(request.getParameter("choix"));
					QCM q = DAOBase.getQCM(id_qcm);
					q.readXML();
					
					request.getSession().setAttribute("choix", id_qcm);
					request.getSession().setAttribute("qcm_select", q);
				}
			}
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * doPost communique avec un utilisateur simple
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on n'a pas choisi le résultat
		if (request.getParameter("choix")== null) {
			ArrayList<String> listeUsers = new ArrayList<String>();
			listeUsers.add((String) request.getSession().getAttribute("user"));
			ArrayList<String> liste = new ArrayList<String>();
			ArrayList<Integer> listeId = new ArrayList<Integer>();
			int id_user = UtilisateurManager.getId((String) request.getSession().getAttribute("user"));
			List<Cours> lc = DAOBase.getListCoursResultat(id_user);
			
			for(int i=0; i<lc.size() ; i++)
			{
				for(int j=0; j<lc.get(i).getLesQCMs().size() ; j++)
				{
					liste.add(lc.get(i).getNom()+" - Difficulté : "
							+ Integer.toString(lc.get(i).getLesQCMs().get(j).getDifficulte())
							+ " ("+new SimpleDateFormat("dd/MM/yyyy HH:ss").format(lc.get(i).getLesQCMs().get(j).getDate())
							+ ")");
					listeId.add(lc.get(i).getLesQCMs().get(j).getId());
				}
			}
			request.getSession().setAttribute("listeUsers", listeUsers);
			request.getSession().setAttribute("choixUser", (String) request.getSession().getAttribute("user"));
			request.getSession().setAttribute("liste", liste);
			request.getSession().setAttribute("listeId", listeId);
			request.getSession().setAttribute("choix", null);
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
		// on a choisi le résultat
		else {
			/* On veut décocher le choix*/
			if((request.getSession().getAttribute("choix")!=null)
					&&
					( Integer.parseInt(request.getParameter("choix")) == (Integer) request.getSession().getAttribute("choix")))
			{
				request.getSession().setAttribute("choix", null);
			}
			/* On veut afficher le choix*/
			else
			{
				int id_qcm = Integer.parseInt(request.getParameter("choix"));
				QCM q = DAOBase.getQCM(id_qcm);
				q.readXML();
				
				request.getSession().setAttribute("choix", id_qcm);
				request.getSession().setAttribute("qcm_select", q);
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageResultats.jsp");
			dispatch.forward(request, response);
		}
	}

}
