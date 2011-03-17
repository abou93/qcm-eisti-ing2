package DAO;

import java.util.List;

import org.hibernate.Session;

import DAO.HibernateUtil;
import Modele.Cours;
import Modele.QCM;
import Modele.Utilisateur;


public class DAOBase 
{	
	public static List<Utilisateur> getListUsers()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Utilisateur> result = (List<Utilisateur>) session.createQuery("from Utilisateur").list();
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		return result;
	}
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
		q.setXML();
		System.out.println("id : " +q.getId());
		q.setId(2323);
		System.out.println("new id : " +q.getId());
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("--- save(q) ");
		session.save(q);
		System.out.println("--- update(q) [id_user="+id_user+"]");
		session.createQuery("update QCM set id_user = "+id_user+" where id = "+q.getId()).executeUpdate();
		System.out.println("--- commit() ");
		session.getTransaction().commit();
		System.out.println("--- close() ");
		session.close();
	}
}
