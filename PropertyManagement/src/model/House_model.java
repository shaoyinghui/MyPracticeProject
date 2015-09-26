package model;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import util.HibernateUtil;

public class House_model {
	public static void main(String[] args){
		System.out.println(getHouse(3));
		System.out.println(getHouse(4));
		System.out.println(getHouse(9));
	}


	/**
	 * 通过房子对应的账号ID找到房子
	 * @param account_id
	 * @return
	 */
	
	
	public  static House findHouse(int account_id){
		Session session = null;
		
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Owner_account o = (Owner_account)session.get(Owner_account.class, new Integer(account_id));
			House h = o.getHouse();
		    if(h==null){
		        session.getTransaction().commit();
		    	return null;
		    }else{
		        session.getTransaction().commit();
		    	return h;
		    }
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return null;
		}
		
	}
	/**
	 * 通过房子ID来更新房屋信息
	 * @param house_id
	 * @param h
	 * @return
	 */
	public static boolean updateHouse(int  house_id, House h){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			House h1 = (House)session.get(House.class, new Integer (house_id));
				if(h1==null){
			        session.getTransaction().commit();
				return false;
			}
			

	        h1.setHouse_building(h.getHouse_building());
	        h1.setHouse_floor(h.getHouse_floor());
	        h1.setHouse_num(h.getHouse_num());
	        h1.setHouse_unit(h.getHouse_unit());
	        h1.setHouse_area(h.getHouse_area());
	        h1.setHouse_remark(h.getHouse_remark());
	        h1.setHouse_type(h.getHouse_type());
	           
	        session.update(h1);
	        session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}
	/**
	 * 检查房子是否被重复占用
	 * @param h
	 * @return
	 */
	public static boolean isRepeat(House h){

		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(  "select * from house where house_building= '"+h.getHouse_building()																
																	 +"' and house_floor="+h.getHouse_floor()
																	 +" and house_num="+h.getHouse_num()
																	 +" and house_unit="+h.getHouse_unit()).addEntity(House.class);
			List<House> al = q.list();
			if(al.size()==0){
		        session.getTransaction().commit();
				return false;
			}
			House house = al.get(0);
			SQLQuery q2 = session.createSQLQuery("select * from owner_account where house_id="+house.getHouse_id()).addEntity(Owner_account.class);
			List<Owner_account> l2 = q.list();
			if(l2.size()==0){
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
	
	/**
	 * 检查房子是否被占用
	 * @param house_id
	 * @return
	 */
//	private static boolean HouseBeOccupied(int house_id) {
//		
//		System.out.println("HouseBeOccupied");
//		
//		int Abnormalflag = 0;
//		Session session = null;
//		session = HibernateUtil.getSession();
//		session.beginTransaction();
//		SQLQuery q = session.createSQLQuery(  "select * from owner_account where house_id="+house_id).addEntity(Owner_account.class);
//		List<Owner_account> al = q.list();
//		if(al != null){
//			  if(al.size() == 0){
//				  System.out.println("No ArrayList<House>");
//				 return false;
//			  }
//			  else{
//				  return true;
//			  }
//		  }
//		  else{
//			  Abnormalflag = 1;
//			  System.out.println("No ArrayList<Owner_account>");
//		  }
//		  return false;
//	}
	
	/**
	 * 由房子的楼号，单元，层数，房号找到对应房子
	 * @param house_building
	 * @param house_floor
	 * @param house_unit
	 * @param house_num
	 * @return
	 */
   public static House findHouse(String house_building,int house_floor,int house_unit,int house_num){
	    Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(  "select * from house where house_building= '"+house_building																
					 																		   +"' and house_floor="+house_floor
					 																		   +" and house_num="+house_num
					 																		   +" and house_unit="+house_unit).addEntity(House.class);
			List<House> al = q.list();
			if(al.size()==0){
		        session.getTransaction().commit();
				return null;
			}
	        session.getTransaction().commit();
			return (House)al.get(0);
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
}
		  

   
	public static int getAccountID(int house_id){
		   Session session = null;
			try{
				session = HibernateUtil.getSession();
				session.beginTransaction();
				SQLQuery q = session.createSQLQuery(  "select * from owner_account where house_id ="+house_id).addEntity(Owner_account.class);
				List<Owner_account> al = q.list();
				if(al.size()==0){
					
					session.getTransaction().commit();
					return -1;
				}
				session.getTransaction().commit();
				return ((Owner_account)al.get(0)).getAccount_id();
			}catch(Exception e){
				e.printStackTrace();
				session.getTransaction().rollback();
				return -1;
			}
	}
   
   

	   public static int getTotalHouse(){
		   Session session = null;
			try{
				session = HibernateUtil.getSession();
				session.beginTransaction();
				SQLQuery q = session.createSQLQuery(  "select * from house ").addEntity(House.class);
				List<House> al = q.list();
				session.getTransaction().commit();
				return al.size();
				}catch(Exception e){
					e.printStackTrace();
					session.getTransaction().rollback();
					return -1;
				}
		}




		public static int getOccupiedHouse(){
				   Session session = null;
					try{
						session = HibernateUtil.getSession();
						session.beginTransaction();
						SQLQuery q = session.createSQLQuery(  "select * from owner_account where house_id !=''").addEntity(Owner_account.class);
						List<House> al = q.list();
						session.getTransaction().commit();
						return al.size();
						}catch(Exception e){
							e.printStackTrace();
							session.getTransaction().rollback();
							return -1;
						}
				}
   
   

		public static House getHouse(int house_id){
				Session session = null;
				try{
					session = HibernateUtil.getSession();
					session.beginTransaction();
					House h = (House)session.get(House.class, new Integer (house_id));
					if(h==null){
						session.getTransaction().commit();
						return null;
					}
					session.getTransaction().commit();
					return h;
				}catch(Exception e){
					e.printStackTrace();
					session.getTransaction().rollback();
					return null;
				}
		}

}


 
 
	  
 
  