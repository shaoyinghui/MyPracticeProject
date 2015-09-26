

/*****************************************************************************************************************************
Due to Environmental Reason, The Packages may be incomplete,and that some of the spellings may not be accurate.
Please Change Manually.

How to use this class? (@ package property)
e.g.
	OriCode:

	public static Session init(){
		Configuration cfg = new Configuration(); 
	    SessionFactory sf = cfg.configure().buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
	
	public  static House findHouse(int account_id){
		init();
		if(init().get(House.class, new Integer(account_id))==null){
			return null;
		}
		else{
			House house=(House)init().get(House.class, new Integer(account_id));
			return house;
		}
		
	}

	Current:
    import package util;
    ...


     
	public  static House findHouse(int account_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			if(session.get(House.class, new Integer(account_id))==null){
				return null;
			}
			else{
				House house=(House)session.get(House.class, new Integer(account_id));
				//session.save();
				//session.delelte();
				//...
				session.getTransaction().commit();
				return house;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();//Roll back abnormal activity,important~
		}
		finally{
	        HibernateUtil.closeSession();
		}
		
	}
*****************************************************************************************************************************/
package util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
private static SessionFactory sessionFactory = null;

	static {
		try{
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties())
														  .buildServiceRegistry());
		}
		catch(Exception e){
			System.err.println("Fail Establishing SessionFactory");
			e.printStackTrace();
		}
	}




public static Session getSession() throws HibernateException{
	Session session = (Session)threadLocal.get();
	if(session == null || !session.isOpen()){
		if(sessionFactory == null){
			rebuildSessionFactory();
		}
		session = sessionFactory.getCurrentSession();
		threadLocal.set(session);
	}
	return session;
}


public static void rebuildSessionFactory(){
		try{
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties())
														  .buildServiceRegistry());
		}
		catch(Exception e){
			System.err.println("Fail Establishing SessionFactory");
			e.printStackTrace();
		}
}

public static SessionFactory getSessionFactory(){
	return sessionFactory;
}

public static void closeSession() throws HibernateException{
	Session session = (Session)threadLocal.get();
	threadLocal.set(null);
	if(session != null){
		session.close();
		System.out.println("close");
	}
}
}
