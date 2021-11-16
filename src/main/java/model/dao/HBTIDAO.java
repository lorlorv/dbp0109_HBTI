package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HBTIDAO {
	private JDBCUtil jdbcUtil = null;
	
	public HBTIDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	// hbti_id의 hbti의 이름을 반환
	public String findHbtiName(int hbti_id) throws SQLException {
		String sql = "SELECT name " + "FROM HBTI " + " WHERE hbti_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { hbti_id}); 

		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			if (rs.next()) { 
				String name = rs.getString("name");
				return name;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;

	}
	
}