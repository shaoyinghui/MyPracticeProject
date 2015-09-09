package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class personDao {


	public boolean  insertPerson(String wechat_id,String nickname){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "insert into person (wechat_id,nickname) values (?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(wechat_id);
		params.add(nickname);
				
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);

			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return false;
	}
	
	
	public boolean updatePerson1(String wechat_id,int remaining_number){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update person set remaining_number = ?  where wechat_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(remaining_number);
		params.add(wechat_id);
		try {
		
		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePerson2(String wechat_id,boolean is_custom,int remaining_number,String person_name,String identity_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update person set is_custom = ? ,remaining_number=?,person_name=?, identity_id=?  where wechat_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(is_custom);
		params.add(remaining_number);
		params.add(person_name);
		params.add(identity_id);
		params.add(wechat_id);
		try {
		
		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int selectPerson(String wechat_id){
		
		
		try {
			System.out.println("3333");
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();

		String sql = "select * from person where wechat_id=? ";

		List<Object> params = new ArrayList<Object>();
		params.add(wechat_id);
			Map<String, Object> m = jdbc.findSimpleResult(sql, params);
			System.out.println(m);
			if(m.size()==0){
				return -1;
			}
		    else{
			String num1 = m.get("remaining_number").toString();
			System.out.println(num1);


			
			int num = Integer.valueOf(num1).intValue(); 
		return num;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
}
