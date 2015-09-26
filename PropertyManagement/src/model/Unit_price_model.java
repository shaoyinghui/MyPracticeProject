package model;


import java.util.Date;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Unit_price;;


public class Unit_price_model {
	public static void main(String[] args){
		
	}
	
	public static boolean setUnit_price(float unit_price) {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Unit_price up=new Unit_price();
			up.setUnit_price(unit_price);
			up.setDate(new Date());
			session.save(up);
			tx.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}

}
