package Controleur;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Modele.Modele;

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
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
					while (it.hasNext())
					{
						DiskFileItem fileItem = (DiskFileItem)it.next();
						File dest = new File(System.getProperty("wtp.deploy") + request.getContextPath() + "/Data/QCMs/", request.getParameter("titre") + ".xml");
						FileOutputStream fos = new FileOutputStream(dest);
						fos.write(fileItem.get());
						fos.close();
					}
				}
				catch (FileUploadException e)
				{
					e.printStackTrace();
				}
			}
			request.getSession().setAttribute("m", m);
			RequestDispatcher dispatch = request.getRequestDispatcher("ControleurListeQCMs");
			dispatch.forward(request, response);
		}
		catch (Exception e){
			System.out.println(e.toString());
			RequestDispatcher dispatch = request.getRequestDispatcher("AffichageErreurUpload.jsp");
			dispatch.forward(request, response);
		}
	}

}
