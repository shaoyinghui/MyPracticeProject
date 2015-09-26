package model;

import java.util.List;

import org.hibernate.LazyInitializationException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.cfg.Configuration;

import util.HibernateUtil;

public class Owner_account_model {

	
	
	public static void main(String args[]){
	
/*		
		House h = new House();
		h.setHouse_building("9");
		h.setHouse_floor(9);
		h.setHouse_unit(9);
		h.setHouse_num(9);
		
	//默认添加一个 属性为业主的Owner
		
		
		
		//oa.setRoom_address(request.getParameter("room_address"));
		
		int account_id = Owner_account_model.addAccount(h,oa);//增加一个账户，没有owner
		
		if(account_id!=-1){
			if(Owner_model.addOwner(account_id,o)){
				System.out.println("successed");
			}else{
				System.out.println("failed");
			}
			
		}else{
			
			
			System.out.println("zhanghuweitianjia     failed");
		}
		*/
		
		House h = new House();
		h.setHouse_building("8");
		h.setHouse_floor(8);
		h.setHouse_unit(8);
		h.setHouse_num(8);
		
		if(deleteAccount(h)){
			System.out.println("successed");
		}else{
			System.out.println("failed");
		}
		
		
	/*	House h = new House();
		h.setHouse_building("8");
		h.setHouse_floor(8);
		h.setHouse_unit(8);
		h.setHouse_num(8);
		Owner_account oa = new Owner_account();
		oa.setAccount_name("jiaqiguo");
		oa.setAccount_password("hahahahhahaha");
		

		
		System.out.println(addAccount(h,oa ));
		
		
		*/
		
		System.out.println(Owner_account_model.updatePassword(5, "e10adc3949ba59abbe56e057f20f883e"
));
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 登录判断
	 * @param account_name
	 * @param account_password
	 * @return
	 */
	public static int isOwner_account(String account_name,String account_password){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery("select * from owner_account where account_name='"+account_name+"'").addEntity(Owner_account.class);
			List<Owner_account> l = q.list();
			if( l.size()==0){
		        session.getTransaction().commit();
				return -1;
			}
			else{
				if(((Owner_account)l.get(0)).getAccount_password().equals(account_password)==false){
			        session.getTransaction().commit();
					return -1;
				}
				session.getTransaction().commit();
				return ((Owner_account)l.get(0)).getAccount_id();
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
}
	
	

	/**
	 * 判断账号名是否存在
	 * @param account_name
	 * @return
	 */
	public static boolean isRepeat(String account_name){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery("select * from owner_account where account_name='"+account_name+"'")
					.addEntity(Owner_account.class);
			List<Owner_account> l = q.list();
			if(l.size()==0){
		        session.getTransaction().commit();
				return false;
			}
	        session.getTransaction().commit();
			return true;
	    }catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
	    }
	}
	
	/**
	 * 创建业主账号
	 * @param h
	 * @param oa
	 * @return
	 */
	public static int addAccount(House h,Owner_account oa){
		Session session = null;
		try{

			House house = House_model.findHouse(h.getHouse_building(), h.getHouse_floor(), h.getHouse_unit(), h.getHouse_num());
			session = HibernateUtil.getSession();
			session.beginTransaction();
			if(house==null){
		        session.getTransaction().commit();
				return -1;
			}
			oa.setHouse(house);
			session.save(oa);
			session.getTransaction().commit();
			return oa.getAccount_id();
		}catch(Exception e){
			e.printStackTrace(System.err);
			System.out.println(e.getMessage());
			return -1;
		}
   }

	/**
	 * 修改接口,明天讨论
	 * @param h
	 * @return
	 */
	public static boolean deleteAccount(House h){
		Session session = null;
		try{

			House house = House_model.findHouse(h.getHouse_building(), h.getHouse_floor(), h.getHouse_unit(), h.getHouse_num());
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery query =session.createSQLQuery("select * from owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
			List<Owner_account> list=query.list();
			if(list.size()==0){
		        session.getTransaction().commit();
				return false;
			}
			else{
				 session.delete((Owner_account)list.get(0));
	        session.getTransaction().commit();
	        return true;
	        
			}
	       
	}catch(Exception e){
		e.toString();
		session.getTransaction().rollback();
		return false;
	}
}
	/**
	 * 通过房子找到账户
	 * @param h
	 * @return
	 */
	public static Owner_account findOwnerAccount(House h){
		Session session = null;
		try{

			House house = House_model.findHouse(h.getHouse_building(), h.getHouse_floor(), h.getHouse_unit(), h.getHouse_num());
			if(house==null){
		    
				return null;
			}
		
			session = HibernateUtil.getSession();
			session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from Owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
	    List<Owner_account> l = q.list();
	    if(l.size()==0){
	        session.getTransaction().commit();
	    	return null;
	    }
        session.getTransaction().commit();
		return (Owner_account)l.get(0);
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}	

	/**
	 * 更改账号密码
	 * @param account_id
	 * @param newpassword
	 * @return
	 */
	public static boolean updatePassword(int account_id,String newpassword){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Owner_account owner_account = (Owner_account)session.get(Owner_account.class, new Integer(account_id));
			if(owner_account==null){
		        session.getTransaction().commit();
				return false;
			}
	        owner_account.setAccount_password(newpassword);
	        session.update(owner_account);
	        session.getTransaction().commit();
			return true;
	}catch(Exception e){
		e.printStackTrace();
		session.getTransaction().rollback();
		return false;
	}
	}
}