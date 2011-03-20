package Controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.DAOBase;
import Modele.QCM;

/**
 * Servlet implementation class ControleurUploadQCM
 */
public class ControleurUploadQCM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurUploadQCM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if(ServletFileUpload.isMultipartContent(request))
			{
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setSizeThreshold(40960);
				ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
				servletFileUpload.setSizeMax(81920);
				try
				{
					List<?> fileItemsList = servletFileUpload.parseRequest(request);
					Iterator<?> it = fileItemsList.iterator();
					DiskFileItem fileItem;
					
					it.next(); /* Lecture de la taille max */
					fileItem= (DiskFileItem)it.next(); /* Lecture du fichier xml */
					File f = new File("tmp.xml");
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(fileItem.get());
					fos.close();
					
					String chaine="";
					
					//lecture du fichier xml uploadé
					try{
						InputStream ips=new FileInputStream(f); 
						InputStreamReader ipsr=new InputStreamReader(ips);
						BufferedReader br=new BufferedReader(ipsr);
						String ligne;
						while ((ligne=br.readLine())!=null){
							chaine+=ligne+"\n";
						}
						br.close(); 
					}		
					catch (Exception e){
						System.out.println(e.toString());
					}
					
					
					
					fileItem= (DiskFileItem)it.next();/* Lecture du nom du cours */
					File ff = new File("tmp.txt");
					FileOutputStream ffos = new FileOutputStream(ff);
					ffos.write(fileItem.get());
					ffos.close();
					
					InputStream ips=new FileInputStream(ff); /* Récuperation du nom du cours */
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);			
					String cours = br.readLine();
					System.out.println("Cours = "+cours);
					System.out.println("fichier : \n"+chaine);
					
					QCM qcm = new QCM();
					qcm.readXML(chaine);
					DAOBase.setCours(cours,qcm);
					
				}
				catch (FileUploadException e)
				{
					e.printStackTrace();
				}
			}			
			RequestDispatcher dispatch = request.getRequestDispatcher("accueil.jsp");
			request.setAttribute("upload", "true");
			dispatch.forward(request, response);
		}
		catch (Exception e){
			System.out.println(e.toString());
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageErreurUpload.jsp");
			dispatch.forward(request, response);
		}
	}

}
