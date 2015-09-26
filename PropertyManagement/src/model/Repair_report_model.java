package model;

import java.lang.reflect.Type;
import java.util.*;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import util.*;

public class Repair_report_model {

	/**
	 * 删除id 定义的报修记录
	 * @param repair_report_id
	 * @return
	 */
	
	
	public static void main(String args[]){
		//Gson gson = new Gson();
			
		
//		
//		Repair_report_model.setRepairPlan(10, "guojiaqi", "13131313");
//		Repair_report_model.setRepairResult(10, "ijii", 10);
		//System.out.println(Repair_report_model.getAllRepair(0).size());
		System.out.print(Repair_report_model.selectRepairDyDate("2015-03-17", "2015-07-19").size());
		
	}
	
	 public static List<Repair_report> selectRepairDyDate(String startdate, String enddate){

			Session session = null;
			try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//System.out.print("222222222");
			SQLQuery q  = session.createSQLQuery("select * from Repair_report where apply_time >="+"'"+startdate+"'"+"and apply_time <="+"'"+enddate+"'").addEntity(Repair_report.class);
			//System.out.print("3333333");
			List<Repair_report> l = (List<Repair_report>)q.list();
			if (l.size()==0){
			return new ArrayList<Repair_report>();
			}
		
			session.getTransaction().commit();
			return l;
			
	          }catch(Exception e){
			            e.printStackTrace();
		             	session.getTransaction().rollback();
		             	return new ArrayList<Repair_report>();
			
			//Roll back abnormal activity,important~
		      }
	 }

	public static boolean deleteRepair(int repair_report_id){
		Session  sess = null;
      try{
			
		sess = HibernateUtil.getSession(); 
		sess.beginTransaction();
		Repair_report rp = ((Repair_report)sess.get(Repair_report.class,new Integer(repair_report_id)));
		System.out.println(rp);
		if(rp==null){
			sess.getTransaction().commit();
			return false;
		}
		else{
		Repair_report  r = (Repair_report)sess.get(Repair_report.class,new Integer(repair_report_id));
		sess.delete(r);
		sess.getTransaction().commit();
		//return true;
		}
	   }catch(Exception e){
		  e.printStackTrace();
	      sess.getTransaction().rollback();
		
		  return false;
		//Roll back abnormal activity,important~
	}

		return true;
		
	}
	
	
	/**
	 * 为某个账户添加报修
	 * @param account_id
	 * @param repair_description
	 * @param repair_object
	 * @param object_position
	 * @return
	 */
	
	public static Repair_report addRepair(int account_id,String repair_description,String repair_object,String object_position){
		Session  sess=null;
		try{
		sess = HibernateUtil.getSession();
		sess.beginTransaction();
		
		Repair_report r = new Repair_report();
		Owner_account oa=(Owner_account)sess.get(Owner_account.class, new Integer(account_id));
		
		if(oa==null){
			sess.getTransaction().commit();
			return null;
		}
		
		House house = oa.getHouse();
		Date d = new Date();
		r.setApply_time(d);
		r.setHouse(house);
		r.setRepair_description(repair_description);
		r.setRepair_object(repair_object);
		r.setObject_position(object_position);
		r.setRepair_time(null);
		sess.save(r);
		sess.getTransaction().commit();
		Date d1 = r.getApply_time();
		Session session = null;
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Repair_report rr = new Repair_report();
	
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = d.getHours();
		int min = d.getMinutes();
		int sec = d.getSeconds();
		String Y = null;
		String M = null;
		String D = null;
		String MIN = null;
		String H = null;
		String S = null;
	
		if(year > 9)Y = year+"";else Y = "0"+year;
		if(month > 9)M = ""+month;else M = "0"+month;
		if(day > 9)D = ""+day;else D = "0"+day;
		if(hour > 9 )H = ""+hour;else H = "0"+hour;
		if(min > 9) MIN = ""+min ;else MIN = "0"+min;
		if (sec > 9) S = ""+sec;else S = "0"+sec;
		
		String date = "'"+Y+"-"+M+"-"+D+" "+H+":"+MIN+":"+S+"'";
		SQLQuery q  = session.createSQLQuery("select * from Repair_report where apply_time = "+date).addEntity(Repair_report.class);
		List<Repair_report> l = (List<Repair_report>)q.list();
		session.getTransaction().commit();
		return (Repair_report)l.get(0);
		}catch(Exception e){
			e.printStackTrace();
			sess.getTransaction().rollback();
     		return null;		
		}
	}


	
//	public static boolean addRepair(int account_id,String repair_description,String repair_object,String object_position){
//		Session  sess=null;
//		try{
//		sess = HibernateUtil.getSession();
//		sess.beginTransaction();
//		
//		Repair_report r = new Repair_report();
//		System.out.println("11");
//		Owner_account oa=(Owner_account)sess.get(Owner_account.class, new Integer(account_id));
//		System.out.println("12");
//		System.out.println(oa);
//		if(oa==null){
//			sess.getTransaction().commit();
//			return false;
//		}
//		House h = oa.getHouse();
//		System.out.println(h);
//		r.setHouse(h);
//		r.setApply_time(new Date());
//		r.setRepair_description(repair_description);
//		r.setRepair_object(repair_object);
//		r.setObject_position(object_position);
//		r.setRepair_time(null);
//		sess.save(r);
//	
//		sess.getTransaction().commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			sess.getTransaction().rollback();
//			
//			return false;
//			//Roll back abnormal activity,important~
//		}
//		return true;
//		
//	}

	
	
	
	/**
	 * 返回某个账户的所有报修申请
	 * @param account_id
	 * @return
	 */
	public static List<Repair_report> getRepair(int account_id){
		Session sess = null;
		try{
		sess = HibernateUtil.getSession();
		sess.beginTransaction();
		SQLQuery q  = sess.createSQLQuery("select * from Owner_account where account_id="+account_id).addEntity(Owner_account.class);
		List<Owner_account> l = (List<Owner_account>)q.list();
		if(l.size()==0){
			  sess.getTransaction().commit();
			  return new ArrayList<Repair_report>();
		  }
		House h = (House)(l.get(0).getHouse());
		int house_id = h.getHouse_id();
		SQLQuery qq = sess.createSQLQuery("select * from Repair_report  where house_id ="+house_id).addEntity(Repair_report.class);
		List<Repair_report> ll = (List<Repair_report>)qq.list();
		sess.getTransaction().commit();
		 return ll;
		 
	   }catch(Exception e){
           e.printStackTrace();
        	sess.getTransaction().rollback();
        	return new ArrayList<Repair_report>();
           }
        }
	/**为报修安排人员
	 * 
	 * @param repair_report_id
	 * @param repairer_name
	 * @param repairer_phone
	 * @return
	 */
	public static boolean setRepairPlan(int repair_report_id,String repairer_name,String repairer_phone){
		System.out.print(repair_report_id);
			Session  sess = null;
	    try{
		sess = HibernateUtil.getSession();
		sess.beginTransaction();
		Repair_report  r = (Repair_report)sess.get(Repair_report.class,new Integer(repair_report_id));
		if(r==null){
			 sess.getTransaction().commit();
			return false;
		}
		r.setRepairer_name(repairer_name);
		r.setRepairer_phone(repairer_phone);
		sess.update(r);
		sess.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			sess.getTransaction().rollback();
		
			return false;
			//Roll back abnormal activity,important~
		}
        return true;
	}	
	
	/**
	 * 修理后 添加维修结果和费用
	 * @param repair_report_id
	 * @param Result
	 * @param fee
	 * @return
	 */
	public static boolean setRepairResult(int repair_report_id,String Result,float fee){
		Session sess = null;
		try{
		sess = HibernateUtil.getSession();
		sess.beginTransaction();
		//System.out.println("222222");
		Repair_report  r = (Repair_report)sess.get(Repair_report.class,new Integer(repair_report_id));
		//System.out.println("333333");
		if(r==null){
			sess.getTransaction().commit();
			return false;
		}
		r.setRepair_time(new Date());
		r.setRepair_result(Result);
	    r.setRepair_fee(new Float(fee));
		sess.update(r);
		sess.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			sess.getTransaction().rollback();
         //   System.out.println("44444");
			return false;
			//Roll back abnormal activity,important~
		}
		return true;
	}


	public static List<Repair_report> getAllRepair(int flag) {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery q  = session.createSQLQuery("select * from Repair_report" ).addEntity(Repair_report.class);
			List<Repair_report> l = (List<Repair_report>)q.list();
			if(l.size()==0){
				  session.getTransaction().commit();
				  return new ArrayList<Repair_report>();
			  }
			List<Repair_report> lr = new ArrayList<Repair_report>();
		        for (int i = l.size()-flag-1;i>l.size()-flag-11;i--){
		         if (i<0) break;
		         lr.add((Repair_report)l.get(i));
		    
		    }
		    return lr;
		    }catch(Exception e){
				e.printStackTrace();
				session.getTransaction().rollback();
				return new ArrayList<Repair_report>();
		    }
		//return null;
	}
	
	
	
	
	

	public static List<Repair_report> getRepair(House h) {
		Owner_account oa = Owner_account_model.findOwnerAccount(h);
		if(oa==null){
			return new ArrayList<Repair_report>();
		}
		else{
			return getRepair(oa.getAccount_id());
		}
		// TODO Auto-generated method stub
	}

	
	
	
	
}

