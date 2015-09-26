package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Notice_type;

public class Notice_model {
	
	
	public static void main(String[] args){
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.addNotice("b","ad", "89"));
//		System.out.println(Notice_model.addNotice("a","ad", "89"));
//		System.out.println(Notice_model.deleteNotice(4));
//		System.out.println(Notice_model.updateNotice(3, "1", "fff"));
		List<Notice> list=Notice_model.getNotice("gjqs", 0);
		for(int i=0;i<list.size();i++){
			Notice notice=(Notice)list.get(i);
			System.out.println(notice.getNotice_content());
		}
		
	}
	
	
	public static boolean addNotice(String notice_type,String title,String content){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			SQLQuery query=session.createSQLQuery("select * from notice_type where notice_type='"+notice_type+"'").addEntity(Notice_type.class);
			List<Notice_type> list=query.list();
			if(list.size()==0){
				return false;
			}
			Notice_type nt=(Notice_type)list.get(0);
			Notice notice=new Notice();
			notice.setNotice_type(nt);
			notice.setNotice_title(title);
			notice.setNotice_content(content);
			notice.setNotice_publish_time(new Date());
			session.save(notice);
			tx.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	
	public static boolean deleteNotice(int notice_id){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			if(session.get(Notice.class, new Integer(notice_id))==null){
				session.getTransaction().commit();
				return false;
			}
			else{
				Notice notice=(Notice)session.get(Notice.class, new Integer(notice_id));
				session.delete(notice);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	
	public static boolean updateNotice(int notice_id,String title,String content){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			if(session.get(Notice.class, new Integer(notice_id))==null){
				session.getTransaction().commit();
				return false;
			}
			else{
				Notice notice=(Notice)session.get(Notice.class, new Integer(notice_id));
				notice.setNotice_title(title);
				notice.setNotice_content(content);
				session.update(notice);
				tx.commit();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return false;
		}
	}
	public static List<Notice> getNotice(String notice_type,int flag){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			SQLQuery queryt=session.createSQLQuery("select * from notice_type where notice_type='"+notice_type+"'").addEntity(Notice_type.class);
			List<Notice_type>listt=queryt.list();
			if(listt.size()==0){
				session.getTransaction().commit();
				return new ArrayList<Notice>();
			}
			Notice_type nt=(Notice_type)listt.get(0);
			int query_id=nt.getNotice_type_id();
			SQLQuery query=session.createSQLQuery("select * from notice where notice_type_id='"+query_id+"'").addEntity(Notice.class);
			List<Notice> list=query.list();
			List<Notice> arrayList=new ArrayList<Notice>();
			for(int i=list.size()-flag-1;i>list.size()-flag-11;i--){
				if(i<0){
					break;
				}
				else {
					arrayList.add((Notice)list.get(i));
				}
			}
			session.getTransaction().commit();
			return arrayList;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            List<Notice> list=new ArrayList<Notice>();
			return list;
		}
	}
	
	
	
	public static List<Notice> selectNotice(String notice_type,String dateon,String dateoff){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			SQLQuery queryt=session.createSQLQuery("select * from notice_type where notice_type='"+notice_type+"'").addEntity(Notice_type.class);
			List<Notice_type>listt=queryt.list();
			if(listt.size()==0){
				session.getTransaction().commit();
				return new ArrayList<Notice>();
			}
			Notice_type nt=(Notice_type)listt.get(0);
			int query_id=nt.getNotice_type_id();
			if((dateon!=null)&&(dateoff!=null)){
				SQLQuery query=session.createSQLQuery("select * from notice where notice_publish_time>="+"'"+dateon+"'"+" and notice_publish_time<="+"'"+dateoff+"' and notice_type_id='"+query_id+"'").addEntity(Notice.class);
				List<Notice> list=query.list();
				session.getTransaction().commit();
				return list;
			}
			else if ((dateon!=null)&&(dateoff==null)) {
				SQLQuery query=session.createSQLQuery("select * from notice where notice_publish_time>="+"'"+dateon+"' and notice_type_id='"+query_id+"'").addEntity(Notice.class);
				List<Notice> list=query.list();
				session.getTransaction().commit();
				return list;
			}
			else if((dateon==null)&&(dateoff!=null)){
				SQLQuery query=session.createSQLQuery("select * from notice where notice_publish_time<="+"'"+dateoff+"' and notice_type_id='"+query_id+"'").addEntity(Notice.class);
				List<Notice> list=query.list();
				session.getTransaction().commit();
				return list;
			}
			else{
				SQLQuery query=session.createSQLQuery("select * from notice where notice_type_id='"+query_id+"'").addEntity(Notice.class);
				List<Notice> list=query.list();
				session.getTransaction().commit();
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
            return new ArrayList<Notice>();
		}

	}
}
