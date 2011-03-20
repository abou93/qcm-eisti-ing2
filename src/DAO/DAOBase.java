package DAO;

import java.util.List;

import org.hibernate.SQLQuery;
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
		List<Utilisateur> result = (List<Utilisateur>) session.createQuery("from Utilisateur where user_id != 0").list();
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
		List<QCM> result = (List<QCM>) session.createQuery("from QCM where id_user = 0 and id_cours = "+c.getId()).list();	
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
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.createQuery("update QCM set id_user = "+id_user+" where id = "+q.getId()).executeUpdate();
		session.createQuery("update QCM set id_cours = "+q.getId_cours()+" where id = "+q.getId()).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	public static int getIdCours(int id) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		int result = (Integer) session.createQuery(
				"select id_cours from QCM where id ="+id+" and id_user=0").uniqueResult();	
		session.close();
		return result;
	}
	public static List<Cours> getListCoursResultat(int id_user) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Cours> lc = session.createQuery("from Cours").list();
		session.close();
		
		for(int i=0; i<lc.size(); i++)
		{
			List<QCM> lq = getQCMUserCours(lc.get(i).getId(),id_user);
			for(int j=0; j<lq.size();j++)
			{
				lq.get(j).readXML();
				lc.get(i).addQCM(lq.get(j));
			}
		}
		return lc;
	}
	public static List<QCM> getQCMUserCours(int id_cours, int id_user)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = (List<QCM>) session.createQuery(
				"from QCM where id_user = "+id_user+" and id_cours="+id_cours).list();	
		session.close();
		return result;
	}
	
	public static List<QCM> getQCMUser(int id_user)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<QCM> result = (List<QCM>) session.createQuery(
				"from QCM where id_user = "+id_user).list();	
		session.close();
		return result;
	}
	
	public static void setCours(String cours, QCM q)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object result = (Object) session.createQuery(
				"from Cours where nom = \'"+cours+"\'").uniqueResult();	
		session.close();
		
		/*Il s'agit d'un nouveau cours*/
		if(result == null)
		{
			Session session2= HibernateUtil.getSessionFactory().openSession();
			session2.beginTransaction();
			Cours c = new Cours(cours);
			session2.save(c); /*Ajout du cours*/
			session2.getTransaction().commit();
			session2.close();
			
			q.setId_cours(c.getId());
			
			Session session3= HibernateUtil.getSessionFactory().openSession();
			session3.beginTransaction();
			session3.save(q);
			/* id_user = 0 -> c'est un qcm (pas un résultat) */
			session3.createQuery("update QCM set id_user = 0 where id = "+q.getId()).executeUpdate();
			session3.getTransaction().commit();
			session3.close();
		}
		/*Le cours existe déjà*/
		else
		{
			Cours c = (Cours) result;
			q.setId_cours(c.getId());
			
			Session session3= HibernateUtil.getSessionFactory().openSession();
			session3.beginTransaction();
			session3.save(q);
			/* id_user = 0 -> c'est un qcm (pas un résultat) */
			session3.createQuery("update QCM set id_user = 0 where id = "+q.getId()).executeUpdate();
			session3.getTransaction().commit();
			session3.close();
			
		}
	}
}
