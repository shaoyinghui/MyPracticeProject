package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class giftDao {
	public boolean updateGift(int gift_id){
		jdbcUtils jdbc = new jdbcUtils();
		jdbc.getConnection();
		String sql = "update gift set remaining_gift = remaining_gift-1  where gift_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(gift_id);
		try {
		
		boolean f = jdbc.updateByPreparedStatement(sql, params);
		
		return f;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
