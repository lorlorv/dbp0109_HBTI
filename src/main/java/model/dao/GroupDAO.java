package model.dao;

import model.Group;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class GroupDAO {

	private JDBCUtil jdbcUtil = null;
	
	public GroupDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public List<Group> findGroupList(int user_hbti, int currentPage) {
		 String sql = "SELECT name, descr, icon "
     			+ "FROM Group "
     			+ "WHERE hbti_id=? ";  
		 jdbcUtil.setSqlAndParameters(sql, new Object[] {user_hbti});	// JDBCUtil에 query문과 매개 변수 설정
		 List<Group> groupList = new ArrayList<Group>();
		 try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				while(rs.next()) {
					Group g = new Group(
							rs.getInt("group_id"),
							rs.getString("name"),
							rs.getString("descr"),
							rs.getString("icon"));
					groupList.add(g);
				}
				return groupList;
		 } catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
	}
}
