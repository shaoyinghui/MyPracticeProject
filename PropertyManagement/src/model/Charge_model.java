package model;

import java.util.*;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import util.HibernateUtil;

public class Charge_model {	
	
	
	public static void main(String[] args){
		System.out.println(getHistoryUnit_price());
//		List<Unit_price> list=getHistoryUnit_price();
//		for(int i=0;i<list.size();i++){
//			Unit_price un=(Unit_price)list.get(i);
//			System.out.println(un.getUnit_price_id());
//		}
	}

	public static boolean setUnit_price(float unit_price) {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Unit_price up=new Unit_price();
			up.setUnit_price(unit_price);
			up.setDate(new Date());
			session.save(up);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}

	public static float getEstate_fee(int account_id){
	Session session = null;
	try{
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Owner_account owner_account = (Owner_account)session.get(Owner_account.class, new Integer(account_id));
		if(owner_account==null){
	        session.getTransaction().commit();
			return 0;
		}

		House house = (House)session.get(House.class, new Integer(owner_account.getHouse().getHouse_id()));
		float house_area = house.getHouse_area();
		SQLQuery q = session.createSQLQuery("select * from unit_price where unit_price_id=(select max(unit_price_id) from unit_price) ").addEntity(Unit_price.class);
	    List<Unit_price> l = q.list();
	    if(l.size()==0){
	        session.getTransaction().commit();
	    	return 0;
	    }
		float unit_price = ((Unit_price)l.get(0)).getUnit_price();
		float estate_fee = house_area * unit_price;
		session.getTransaction().commit();
		return estate_fee;
	}catch(Exception e){
		e.printStackTrace();
		session.getTransaction().rollback();
		return 0;
	}
}
	
public static List<Estate_fee> getHistoryEstate(int account_id){
	Session session = null;
	try{
		session = HibernateUtil.getSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from estate_fee where account_id="+account_id).addEntity(Estate_fee.class);
	    List<Estate_fee> l = q.list();
	    if(l.size()==0){
	        session.getTransaction().commit();
	    	return new ArrayList<Estate_fee>();
	    }
	    List<Estate_fee> list = new ArrayList<Estate_fee>();  
	    if(l.size()<25){
        for(int i = 0 ; i < l.size(); i++){
            list.add((Estate_fee)l.get(i));
        	}
        }  
	    else{
	    	for(int i = 0 ; i < l.size(); i++){
	            list.add((Estate_fee)l.get(i));
	        	}
	    }
	    session.getTransaction().commit();
	    return list;
	}catch(Exception e){
		e.printStackTrace();
		session.getTransaction().rollback();
		return new ArrayList<Estate_fee>();
	}
}

public static List<Unit_price> getHistoryUnit_price(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery("select * from unit_price").addEntity(Unit_price.class);
		    List<Unit_price> l = q.list();
		    
			List<Unit_price> list = new ArrayList<Unit_price>();
			if(l.size()<25){
				for(int i=0;i<l.size();i++){
					 list.add((Unit_price)l.get(i));
				}
			}
			else{
				for(int i=0;i<24;i++){
					 list.add((Unit_price)l.get(i));
					}
			}
			session.getTransaction().commit();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
}
}

