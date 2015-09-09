package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class database {

	public boolean  insert(String city,int fee_table_id,String fee_type,int fee_number,int is_student,String pay_status,String order_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "insert into payfee (city,fee_table_id, fee_type, fee_number,is_student,pay_status,order_id) values (?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(city);
		params.add(fee_table_id);
		params.add(fee_type);
		params.add(fee_number);
		params.add(is_student);
		params.add(pay_status);
		params.add(order_id);
				
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);

			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return false;
	}
	
	public boolean updateResult(String order_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update payfee set pay_status = ? where order_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add("ÒÑÖ§¸¶");
		params.add(order_id);
		try {
		
		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePerson(String person_name,String person_phone){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update payfee set person_name = ? ,person_phone =?  where order_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(person_name);
		params.add(person_phone);
		try {
		
		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	
//	
	
	
//	 public boolean updateFrom(int id,int sex,String name){ 
//
//	     boolean flag = false; 
//
//	     Connection conn = null; 
//
//	     conn = JdbcCon.jdbcConMysql(); 
//
//	     String sql = "update person set sex=?,name=? where id=?"; 
//
//	     PreparedStatement ps = null; 
//
//	     try { 
//
//	ps = conn.prepareStatement(sql); 
//
//	ps.setInt(1, sex); 
//
//	ps.setString(2, name); 
//
//	ps.setInt(3, id); 
//
//	int i = ps.executeUpdate(); 
//
//	if(i != 0){ 
//
//	flag = true; 
//
//	} 
//
//	} catch (SQLException e) { 
//
//	e.printStackTrace(); 
//
//	} finally { 
//
//	JdbcCon.closeAll(null, ps, conn); 
//
//	} 
//
//	     return flag; 
//
//	    } 
//	
//	
	
	
	
//	public List search(){
//		String sql2 = "select * from userinfo ";
//		List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null);
//		System.out.println(list);
//		return list;
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
//	}
//}	


	

}

