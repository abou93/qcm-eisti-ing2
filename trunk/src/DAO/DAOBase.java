package DAO;

import java.util.List;

import org.hibernate.Session;

import DAO.HibernateUtil;
import Modele.Cours;
import Modele.QCM;


public class DAOBase 
{	
	public static List<Cours> getListCours()
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Cours> result = session.createQuery("from Cours").list();
		session.close();
		return result;
	}
	
	public static List<QCM> getListQCM()
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = session.createQuery("from QCM").list();
		session.close();
		return result;
	}
	
	public static List<QCM> getMesQCMs(Cours c)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = session.createQuery("from QCM where id_cours = "+c.getId()).list();	
		session.close();
		return result;
	}
	
	public static QCM getQCM(int id)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		QCM result = (QCM) session.createQuery("from QCM where id = "+id).uniqueResult();	
		session.close();
		return result;
	}
	
	public static void saveResultat(QCM q, int id_user)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.createQuery("update QCM set id_user = "+id_user+" where id = "+q.getId());
		session.close();
	}
}
