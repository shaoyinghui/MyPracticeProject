package model;

import java.util.*;



import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.*;








public class Vehicle_model {

	
	
	public static void main(String args[]){
	House h = new House();
	h.setHouse_building("5");
	h.setHouse_floor(5);
	h.setHouse_unit(5);
	h.setHouse_num(5);
	
	Vehicle v1 = new Vehicle();
	v1.setVehicle_plate("123455");
	Vehicle lv =	Vehicle_model.addVehicle(h,v1);
	
	//System.out.println(lv.getVehicle_plate());
}




//	public static boolean addVehicle(House h,Vehicle v){
//		Session session = null;
//		try{
//		session = HibernateUtil.getSession();
//		session.beginTransaction();
//
//		SQLQuery q = session.createSQLQuery(  "select * from house where house_building= '"+h.getHouse_building()																
//				 +"' and house_floor="+h.getHouse_floor()
//				 +" and house_num="+h.getHouse_num()
//				 +" and house_unit="+h.getHouse_unit()).addEntity(House.class);
//        List<House> l = q.list();
//        System.out.println(l);
//        if(l.size()==0){
//        	session.getTransaction().commit();
//			return false;
//		}
//		SQLQuery qq  = session.createSQLQuery("select * from owner_account where house_id="+((House)l.get(0)).getHouse_id()).addEntity(Owner_account.class);
//    	List<Owner_account> ll = qq.list();
//		if(ll.size()==0){
//			session.getTransaction().commit();
//			
//			return false;
//		}
//		v.setOwner_account((Owner_account)ll.get(0));
//		session.save(v);
//		session.getTransaction().commit();
//		
//		return true;
//
//	   }catch(Exception e){
//		e.printStackTrace();
//		session.getTransaction().commit();
//		
//		return false;
//	}
//	}
	
public static Vehicle addVehicle(House h,Vehicle v){
		
		Session session = null;
		try{
		Session ses = HibernateUtil.getSession();
		ses.beginTransaction();
		SQLQuery sq = ses.createSQLQuery(" select * from vehicle where vehicle_plate= '"+v.getVehicle_plate()+"'");
		List<Vehicle> lsq = sq.list();
    	if(lsq.size() != 0)  {System.out.println("之前已经登记");
		                      ses.getTransaction().commit();
		                      return null;}
		ses.getTransaction().commit();	
		session = HibernateUtil.getSession();
		session.beginTransaction();

		SQLQuery q = session.createSQLQuery(  "select * from house where house_building= '"+h.getHouse_building()																
				 +"' and house_floor="+h.getHouse_floor()
				 +" and house_num="+h.getHouse_num()
				 +" and house_unit="+h.getHouse_unit()).addEntity(House.class);
        List<House> l = q.list();
        System.out.println(l);
        if(l.size()==0){
        	session.getTransaction().commit();
			return null;
		}
		SQLQuery qq  = session.createSQLQuery("select * from owner_account where house_id="+((House)l.get(0)).getHouse_id()).addEntity(Owner_account.class);
    	List<Owner_account> ll = qq.list();
		if(ll.size()==0){
			session.getTransaction().commit();
			
			return null;
		}
		v.setOwner_account((Owner_account)ll.get(0));
		v.setVehicle_register_time(new Date());
		session.save(v);
		session.getTransaction().commit();
		Session sess = HibernateUtil.getSession();
		sess.beginTransaction();
		SQLQuery qu  = sess.createSQLQuery("select * from vehicle where vehicle_plate='"+v.getVehicle_plate()+"'").addEntity(Vehicle.class);
		List<Vehicle> lv = qu.list();
		sess.getTransaction().commit();
		return (Vehicle)lv.get(0);

	   }catch(Exception e){
		e.printStackTrace();
		session.getTransaction().commit();
		
		return null;
	}
	}

	public static boolean deleteVehicle(int vehicle_id){
		Session session = null;
		try {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Vehicle  v = (Vehicle)session.get(Vehicle.class,new Integer(vehicle_id));
		if(v==null){
			return false;
		}
		session.delete(v);
		session.getTransaction().commit();
		return true;
        }catch(Exception e){
		e.printStackTrace();
		session.getTransaction().rollback();
		return false;
	   }

	}
	public static ArrayList<Vehicle> findVehicle (int account_id){
		Session session = null;
		try{
		session = HibernateUtil.getSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from vehicle where account_id="+account_id).addEntity(Vehicle.class);
		  ArrayList<Vehicle> al =(ArrayList<Vehicle>) q.list();
		  if(al.size()==0){
			  session.getTransaction().commit();
			  return new ArrayList<Vehicle>();

		  }
		  session.getTransaction().commit();
		  return al;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		  
			return new ArrayList<Vehicle>();
		   }
	}
	
	
	public static List<Vehicle> findVehicle (House h){
		Session session = null;
		try{
		
		Owner_account oa = Owner_account_model.findOwnerAccount(h);
		session = HibernateUtil.getSession();
		session.beginTransaction();
		SQLQuery q =session.createSQLQuery("select * from vehicle where account_id="+oa.getAccount_id()).addEntity(Vehicle.class);
		 ArrayList<Vehicle> al =(ArrayList<Vehicle>) q.list();
		  if(al.size()==0){
			  session.getTransaction().commit();
			  return new ArrayList<Vehicle>();
		  }
		   session.getTransaction().commit();
		   return al;
		}catch(Exception e){
			e.printStackTrace();
			 session.getTransaction().rollback();
			
			return new ArrayList<Vehicle>();
		   }
	}
	public static boolean updateVehicle(Vehicle v,int vehicle_id){
		Session session = null;
		try{
		session = HibernateUtil.getSession();
	    session.beginTransaction();
		Vehicle v1 = (Vehicle)session.get(Vehicle.class,new Integer (vehicle_id));

		if(v1==null){ session.getTransaction().commit();return false;}
		if(v.getOwner_account()!=null)v1.setOwner_account(v.getOwner_account());
        if(v.getVehicle_plate()!=null)v1.setVehicle_plate(v.getVehicle_plate());
        if(v.getVehicle_register_time()!=null)v1.setVehicle_register_time(v.getVehicle_register_time());
        session.update(v1);
        session.getTransaction().commit();
     	return true;
		}catch(Exception e){
			e.printStackTrace();
			
			session.getTransaction().rollback();
			return false;
		   }
	}
}
