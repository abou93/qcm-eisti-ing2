package DAO;

import java.util.List;

import org.hibernate.Session;

import DAO.HibernateUtil;
import Modele.Cours;
import Modele.QCM;


public class DAOBase 
{	

	@SuppressWarnings("unchecked")
	public static List<Cours> getListCours()
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Cours> result = session.createQuery("from Cours").list();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<QCM> getListQCM()
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = session.createQuery("from QCM").list();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<QCM> getMesQCMs(Cours c)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = session.createQuery("from QCM where id_cours = "+c.getId()).list();
		System.out.println("result : "+result.size() );		
		session.close();
		return result;
	}
}
