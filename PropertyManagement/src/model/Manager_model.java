package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Manager;


public class Manager_model {
	
	public static void main(String[] args){
		/*	Manager manager=new Manager();
			manager.setManager_account("bbb");
			manager.setManager_password("123");
			manager.setManager_name("aaa");
			manager.setManager_phone("qwe");
			manager.setManager_tel("123");
			manager.setManager_email("123");
			manager.setManager_gender(1);
			manager.setManager_age(0);
			manager.setEntry_time(new Date());
			System.out.println(Manager_model.addManager(manager));
			System.out.println(Manager_model.isRepeat("aaa"));
			System.out.println(Manager_model.isRepeat("bbb"));
			System.out.println(Manager_model.isManager("bbb", "123"));*/
			//System.out.println(Manager_model.deleteManager(3));
			
		//Manager m = getInfo(1);
		//System.out.println(m.getManager_account());
		
		//System.out.println(isRepeat("dddd"));
		
		System.out.println(deleteManager(2));
	}
	
	public static boolean isRepeat(String manager_account){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from manager where manager_account='"+manager_account+"'").addEntity(Manager.class);
			List<Manager> list=query.list();
			if(list.size()==0){
				session.getTransaction().commit();
				return false;
			}
			else {
				session.getTransaction().commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return true;
		}
	}
	
	public static boolean addManager(Manager m){
		Session session = null;
		try{
			if(isRepeat(m.getManager_account())==true){
				session = HibernateUtil.getSession();
				Transaction tx=session.beginTransaction();
				tx.commit();
				return false;
			}
			else{
				session = HibernateUtil.getSession();
				Transaction tx=session.beginTransaction();
				session.save(m);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	
	public static boolean deleteManager(int manager_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			if(session.get(Manager.class, new Integer(manager_id))==null){
				tx.commit();
				return false;
			}
			else{
				Manager manager=(Manager)session.get(Manager.class, new Integer(manager_id));
				session.delete(manager);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	
	public static boolean updateManager(int manager_id,Manager m){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Manager manager=(Manager)session.get(Manager.class, new Integer(manager_id));
			if(manager==null){
				tx.commit();
				return false;
			}
			else{
				manager.setManager_account(m.getManager_account());
				manager.setManager_password(m.getManager_password());
				manager.setManager_name(m.getManager_name());
				manager.setManager_phone(m.getManager_phone());
				manager.setManager_tel(m.getManager_tel());
				manager.setManager_email(m.getManager_email());
				manager.setManager_gender(m.getManager_gender());
				manager.setManager_age(m.getManager_age());
				manager.setEntry_time(new Date());
				session.update(manager);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	

	public static boolean updatePassword(int manager_id,String newpassword){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Manager manager=(Manager)session.get(Manager.class, new Integer(manager_id));
			if(manager==null){
				tx.commit();
				return false;
			}
			else{
				manager.setManager_password(newpassword);
				session.update(manager);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}

	
	

		
	
	public static List<Manager> findManager(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from manager").addEntity(Manager.class);
			List<Manager> list=query.list();
			session.getTransaction().commit();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			List<Manager> list=new ArrayList<Manager>();
            return list;
		}
	}
	
	public static int isManager(String manager_account,String manager_password){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from manager where manager_account='"+manager_account+"'").addEntity(Manager.class);
			List<Manager> list=query.list();
			if(list.size()==0){
				session.getTransaction().commit();
				return -1;
			}
			else if (((Manager)list.get(0)).getManager_password().equals(manager_password)==false) {
				session.getTransaction().commit();
				return -1;
			}
			else{
				session.getTransaction().commit();
				return ((Manager)list.get(0)).getManager_id();
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return -1;
		}
	}
	
	public static Manager getInfo(int manager_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			if(session.get(Manager.class, new Integer(manager_id))==null){
				session.getTransaction().commit();
				return null;
			}
			else{
				Manager manager=(Manager)session.get(Manager.class, new Integer(manager_id));
				session.getTransaction().commit();
				return manager;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return null;
		}
	}
}
