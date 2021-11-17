package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.HBTI;

public class HBTIDAO {
	private JDBCUtil jdbcUtil = null;
	
	public HBTIDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public HBTI findHBTI(int hbti_id)throws SQLException{
		String sql = "SELECT name, goodhbti, badhbti, icon, good_descr, bad_descr, exercise " + "FROM HBTI "
				+ "WHERE hbti_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { hbti_id }); 

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) { 
				HBTI hbti = new HBTI(
						rs.getString("name"), hbti_id, rs.getInt("goodhbti"), rs.getInt("badhbti"), rs.getString("icon"),
						rs.getString("good_descr"), rs.getString("bad_descr"), rs.getString("exercise"));
				return hbti;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
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
	
	public String findHbtiImg(String name)throws SQLException {
		String sql = "SELECT icon " + "FROM HBTI " + "WHERE name=? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { name });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String hbti_img = rs.getString("icon");
				return hbti_img;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
}