package model;

import java.util.List;

import util.HibernateUtil;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Root;


public class Root_model {
	public static void main(String[] args){
		//System.out.println(Root_model.isRepeat("a"));
		//System.out.println(Root_model.isRoot("a", "11221"));
	//	System.out.println(Root_model.updatePassword("a", "234"));
		System.out.println(Root_model.updatePassword(1,"23ddddd"));
//		System.out.println(Root_model.isRepeat("a"));
//		System.out.println(Root_model.isRoot("a", "11221"));
//		
	}
	
	public static boolean isRepeat(String root_account){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from root where root_account='"+root_account+"'").addEntity(Root.class);
			List<Root> list=query.list();
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
	

	public static boolean updatePassword(int root_id,String newpassword){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from root where id="+root_id).addEntity(Root.class);
		    List<Root> list=query.list();
			if(list.size() == 0){
		    	session.getTransaction().commit();
				return false;
		    }	
			
			else{
				Root root=(Root)list.get(0);
				root.setRoot_password(newpassword);
				session.update(root);
				session.getTransaction().commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	
	
	public static int isRoot(String root_account,String root_password){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			if(session.get(Root.class, root_account)==null){
				session.getTransaction().commit();
				return -1;
			}
			else if (((Root)session.get(Root.class, root_account)).getRoot_password().equals(root_password)==false) {
				session.getTransaction().commit();
				return -1;
			}
			else{
				session.getTransaction().commit();
				return ((Root)session.get(Root.class, root_account)).getRoot_id();
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return -1;
		}
	}
}
