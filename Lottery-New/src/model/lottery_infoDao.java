package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class lottery_infoDao {
	public boolean  insertLottery(Date time,int gift_id,String wechat_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "insert into lottery_info (time,wechat_id,gift_id) values (?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(time);
		params.add(wechat_id);
		params.add(gift_id);
		try {
			boolean flag = jdbc.updateByPreparedStatement(sql, params);

			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return false;
	}
	
	public boolean updateLottery(String person_name,String person_phone,boolean is_exchanged,String wechat_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update lottery_info set person_name = ? ,person_phone=? ,is_exchanged =? where wechat_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(person_name);
		params.add(person_phone);
		params.add(is_exchanged);
		params.add(wechat_id);
		try {

		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Map<String, Object> selectLottery(String wechat_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "select * from lottery_info where wechat_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(wechat_id);
		try {

			Map<String, Object> m = jdbc.findSimpleResult(sql, params);
			
		
		return m;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
