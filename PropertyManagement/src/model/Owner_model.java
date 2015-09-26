package model;

import java.lang.reflect.Type;
import java.util.*;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import util.*;
public class Owner_model {

	/**
	 * 通过账号ID找到所有家庭成员
	 * @param account_id
	 * @return
	 */
	
	
	
	public static void main(String args[]){
		House h = new House();
		h.setHouse_building("8");
		h.setHouse_floor(8);
		h.setHouse_unit(8);
		h.setHouse_num(8);
	 
		List<model.Owner> lo= Owner_model.findOwner(h);
		
		if(!lo.isEmpty()){
			Gson gson_Owner=new GsonBuilder().registerTypeAdapter(model.Owner.class, new JsonSerializer<model.Owner>() {
	            public JsonElement serialize(model.Owner  src, Type typeOfSrc,
	                    JsonSerializationContext context) {
	                JsonObject o=new JsonObject();
	                o.addProperty("owner_name", src.getOwner_name());
	                o.addProperty("owner_phone", src.getOwner_phone());
	                o.addProperty("owner_gender", src.getOwner_gender());
	                o.addProperty("owner_email", src.getOwner_email());
	                o.addProperty("owner_age", src.getOwner_age());
	                return o;
	            }
	        }).create();
			System.out.println(gson_Owner.toJson(lo));
		}else{
			System.out.println("failed");
		}				
			
 	}


		
		
		
	
	public static List<Owner>  findOwner(int account_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from owner where account_id="+account_id).addEntity(Owner.class);

			  List<Owner> al =q.list();
		      session.getTransaction().commit();
			  return al;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}
	/**
	 * 通过房子的楼号，层数，单元，房号来查询所有家庭成员
	 * @param h
	 * @return
	 */
	public static List<Owner>  findOwner(House h){
		Session session = null;
		try{
			
			House house = House_model.findHouse(h.getHouse_building(),
					                            h.getHouse_floor(),
					                            h.getHouse_unit(),
					                            h.getHouse_num());
					
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			if(house==null){
				List<Owner> list=new ArrayList<Owner>();
		        session.getTransaction().commit();
				  return  list;
			}
		  SQLQuery q1 = session.createSQLQuery("select * from Owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
		  List<Owner_account> a1 =q1.list();
		  if(a1.size()==0){
			  List<Owner> list=new ArrayList<Owner>();
		        session.getTransaction().commit();
			  return  list;
		  }
		  Owner_account o = (Owner_account)a1.get(0);
	      SQLQuery q2 = session.createSQLQuery("select * from owner where account_id="+o.getAccount_id()).addEntity(Owner.class);
		  List<Owner> al =q2.list();
	      session.getTransaction().commit();
		  return al;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	/**
	 * 添加家庭成员
	 * @param account_id
	 * @param o
	 * @return
	 */
//	public static boolean addOwner(int account_id,Owner o){
//		Session session = null;
//		try{
//			session = HibernateUtil.getSession();
//			session.beginTransaction();
//			Owner_account owner_account = (Owner_account)session.get(Owner_account.class, new Integer(account_id));
//			if(owner_account==null){
//		        session.getTransaction().commit();
//				return false;
//			}
//			o.setOwner_account(owner_account);
//			session.save(o);
//			session.getTransaction().commit();
//			return true;
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			session.getTransaction().rollback();
//			return false;
//		}
//	}
	
	
	public static Owner addOwner(int account_id,Owner o){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Owner_account owner_account = (Owner_account)session.get(Owner_account.class, new Integer(account_id));
			if(owner_account==null){
		        session.getTransaction().commit();
				return null;
			}
			o.setOwner_account(owner_account);
			session.save(o);
			session.getTransaction().commit();
			
			
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();  
			SQLQuery q = sess.createSQLQuery("select * from owner where account_id="+account_id+" and owner_name = '"+o.getOwner_name()+"' and owner_age = "+o.getOwner_age()).addEntity(Owner.class);
			List<Owner> al =q.list();
		    sess.getTransaction().commit();
			
			return (Owner)al.get(0);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		    return null;
		}
	}
	
	
	
	/**
	 * 删除账户
	 * @param owner_id
	 * @return
	 */

	public static boolean deleteOwner(int owner_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
		if(((Owner)session.get(Owner.class,new Integer(owner_id))==null)){
	        session.getTransaction().commit();
			return false;
		}
		Owner o = (Owner)session.get(Owner.class,new Integer(owner_id));
		session.delete(o);
		session.getTransaction().commit();
		return true;
		
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}
	
	/**
	 * 更新账户
	 * @param Owner_id
	 * @param o
	 * @return
	 */
 public static boolean updateOwner(int Owner_id,Owner o){
		 
	 Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(  "select * from owner where owner_id="+Owner_id).addEntity(Owner.class);															
			List<Owner> al = q.list();
			if(al.size()==0){
			      session.getTransaction().commit();
		 		 return false;
		 		 }
			Owner o1 = al.get(0);
		 	  o1.setOwner_age(o.getOwner_age());
		 	  o1.setOwner_email(o.getOwner_email());
		 	  o1.setOwner_gender(o.getOwner_gender());
		 	  o1.setOwner_name(o.getOwner_name());
		 	  o1.setOwner_phone(o.getOwner_phone());
	         session.update(o1);
	         session.getTransaction().commit();
		 	 return true;	 
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}
//	public static int isOwner(String username, String password) {
//		// TODO Auto-generated method stub
//		return 0;
//	}



	


	}
	

