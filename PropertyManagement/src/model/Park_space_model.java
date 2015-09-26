package model;

import java.util.*;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import util.*;
public class Park_space_model {


	
	public static void main(String args[]){
		
		House h = new House();
		h.setHouse_building("1");
		h.setHouse_floor(3);
		h.setHouse_unit(3);
		h.setHouse_num(4);
	 
		//int park_space_id = 1;
		
		//System.out.println(addPark(h, park_space_id));
		
		//System.out.println(updatePark(1, 2));
		
		addPark(h, 1);
		
		findPark(h);
		
		updatePark(1, 4);
		
		
		
		
		
//	
	}
	
	
	
	/**
	 * 通过添加车库给房子所属账户
	 * @param h
	 * @param park_id
	 * @return
	 */
	public static boolean addPark(House h ,int park_id){
 		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
		Park_space p = (Park_space)session.get(Park_space.class,new Integer(park_id));
		if(p==null){
		      session.getTransaction().commit();
			return false;
		}
		session.getTransaction().commit();
		House house = House_model.findHouse(h.getHouse_building(),
                h.getHouse_floor(),
                h.getHouse_unit(),
                h.getHouse_num());
		if(house==null){
			return  false;
		}
		
		
		

		
		session = HibernateUtil.getSession();
		session.beginTransaction();
		SQLQuery q  = session.createSQLQuery("select * from owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
		List<Owner_account> l = q.list();
		if(l.size()==0){
		      session.getTransaction().commit();
			return false;
		}
		p.setOwner_account((Owner_account)l.get(0));
		session.update(p);
		session.getTransaction().commit();
		return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}

	}
	/**
	 * 通过车库ID删除车库
	 * @param park_id
	 * @return
	 */
	public static boolean deletePark(int park_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
		if(((Park_space)session.get(Park_space.class,new Integer(park_id))==null)){
		      session.getTransaction().commit();
			return false;
		}
		Park_space  p = (Park_space)session.get(Park_space.class,new Integer(park_id));
		p.setOwner_account(null);
		session.update(p);
		session.getTransaction().commit();
		return true;

		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}

	}
	/**
	 * 通过账户ID来查找车库
	 * @param account_id
	 * @return
	 */
	public static List<Park_space>  findPark(int account_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from park_space where account_id="+account_id).addEntity(Park_space.class);

		  List<Park_space> al = q.list();
		  if(al.size()==0){
			  List<Park_space> l = q.list();
			  session.getTransaction().commit();
			  return  l;
		  }
		  session.getTransaction().commit();
		  return al;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}
	/**
	 * 能运行，但逻辑有问题
	 * @param park_id
	 * @param newpark_id
	 * @return
	 */
	
//	public static boolean updatePark(int park_id,int newpark_id){
//	   
//		Session session = null;
//		try{
//			session = HibernateUtil.getSession();	 
//			session.beginTransaction();
//		    Park_space ps=(Park_space)session.get(Park_space.class, new Integer(park_id));
//		    if(ps==null) {
//		       session.getTransaction().commit();
//			   return false;
//		    }
//	
//	        Owner_account o2 =  ps.getOwner_account();
//	        ps.setOwner_account(null);
//	        session.update(ps);
//	        Park_space newps=(Park_space)session.get(Park_space.class, new Integer(newpark_id));
//	        newps.setOwner_account(o2);
//	        session.update(newps);
//	        session.getTransaction().commit();
//	        return true;
//		}catch(Exception e){
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return false;
//		}
//
//	}

	public static boolean updatePark(int park_id,int newpark_id){
		   
		Session session = null;
		try{
			session = HibernateUtil.getSession();	 
			session.beginTransaction();
		    Park_space ps=(Park_space)session.get(Park_space.class, new Integer(park_id));
		    Park_space newps=(Park_space)session.get(Park_space.class, new Integer(newpark_id));
		    if(ps==null) {
		       session.getTransaction().commit();
			   return false;
		    }
	
	        Owner_account o2 =  ps.getOwner_account();
	        ps.setOwner_account(newps.getOwner_account());
	
	        newps.setOwner_account(o2);
	        session.update(newps);
	        session.update(ps);
	        session.getTransaction().commit();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}

	}
	
	
	/**
	 * 通过房子信息来查找车库
	 * @param h
	 * @return
	 */
	public static List<Park_space>  findPark(House h){
		Session session = null;
		try{
			
			House house = House_model.findHouse(h.getHouse_building(),
	                h.getHouse_floor(),
	                h.getHouse_unit(),
	                h.getHouse_num());
			
			if(house==null){
				List<Park_space> list=new ArrayList<Park_space>();

				  return  list;
			}
			session = HibernateUtil.getSession();	
			session.beginTransaction();
			SQLQuery q  = session.createSQLQuery("select * from owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
			List<Owner_account> l = q.list();
			if(l.size()==0){
				List<Park_space> list=new ArrayList<Park_space>();
			      session.getTransaction().commit();
				  return  list;
			}	
			SQLQuery q1 = session.createSQLQuery("select * from park_space where account_id="+((Owner_account)l.get(0)).getAccount_id()).addEntity(Park_space.class);
			List<Park_space> l2  = (List<Park_space>)q1.list();
			if(l2.size()==0){
				List<Park_space> list=new ArrayList<Park_space>();
			      session.getTransaction().commit();
				  return  list;
			}
			 List<Park_space> l3 = q1.list();
		      session.getTransaction().commit();
			  return  l3;

		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}
	
	
	   public static int getTotalParkSpace(){
		   Session session = null;
			try{
				session = HibernateUtil.getSession();
				session.beginTransaction();
				SQLQuery q = session.createSQLQuery(  "select * from park_space ").addEntity(Park_space.class);
				List<Park_space> al = q.list();
				session.getTransaction().commit();
				return al.size();
				}catch(Exception e){
					e.printStackTrace();
					session.getTransaction().rollback();
					return -1;
				}
		}
	   
	   
		public static int getOccupiedParkSpace(){
			   Session session = null;
				try{
					session = HibernateUtil.getSession();
					session.beginTransaction();
					SQLQuery q = session.createSQLQuery(  "select * from park_space where account_id !=''").addEntity(Park_space.class);
					List<Park_space> al = q.list();
					session.getTransaction().commit();
					return al.size();
					}catch(Exception e){
						e.printStackTrace();
						session.getTransaction().rollback();
						return -1;
					}
			}


	

}


