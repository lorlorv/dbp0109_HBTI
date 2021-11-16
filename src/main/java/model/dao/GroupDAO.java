package model.dao;

import model.Group;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GroupDAO {

	private JDBCUtil jdbcUtil = null;

	public GroupDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	// hbti_id의 그룹 리스트를 구함
	public List<Group> findGroupList(int hbti_id) throws SQLException {
		String sql = "SELECT group_id, name, icon, descr ,limitation " + "FROM GroupInfo " + "WHERE hbti_id=? "
				+ "ORDER BY group_id DESC ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { hbti_id }); // JDBCUtil에 query문과 매개 변수 설정
		List<Group> groupList = new ArrayList<Group>();
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			while (rs.next()) {
				Group g = new Group(rs.getInt("group_id"), rs.getString("name"), rs.getString("icon"),
						rs.getString("descr"), rs.getInt("limitation"));
				groupList.add(g);
			}
			return groupList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 키워드에 맞는 그룹 리스트를 반환
	public List<Group> searchGroupList(int hbti_id, String keyword) throws SQLException {
		String sql = "SELECT group_id, name, icon, descr, limitation " + "FROM GroupInfo "
				+ "WHERE hbti_id=? AND name LIKE ? " // keyword 'ad%'
				+ "ORDER BY group_id DESC ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { hbti_id, keyword });
		List<Group> groupList = new ArrayList<Group>();
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			while (rs.next()) {
				Group g = new Group(rs.getInt("group_id"), rs.getString("name"), rs.getString("icon"),
						rs.getString("descr"), rs.getInt("limitation"));
				groupList.add(g);
			}
			return groupList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// group_id의 그룹원 리스트를 구함
	public List<User> findUserList(int group_id) throws SQLException {
		String sql = "SELECT user_id, name, image, login_date " + "FROM UserInfo " + "WHERE group_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				userList.add(new User(rs.getString("user_id"), rs.getString("name"), rs.getString("image"),
						rs.getString("login_date")));
			}
			return userList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	
	// group_id의 그룹 정보를 반환
	public Group findGroup(int group_id) throws SQLException {
		String sql = "SELECT  g.name AS g_name, icon, g.descr AS g_descr, leader_id , g.hbti_id AS hbti_id, content, limitation "
				+ "FROM GroupInfo g, DayOfChallenge d, Challenge c "
				+ "WHERE g.hbti_id = d.hbti_id AND d.challenge_id = c.challenge_id " + "AND g.group_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Group group = new Group(group_id, rs.getString("g_name"), rs.getString("icon"), rs.getString("g_descr"),
						rs.getInt("limitation"));
				group.setLeader_id(rs.getString("leader_id"));
				group.setHbti_id(rs.getInt("hbti_id"));
				group.setChallengeContent(rs.getString("content"));

				return group;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	// group_id의 그룹원 인원을 반환
	public int findNumberOfMember(int group_id) throws SQLException {
		String sql = "SELECT count(*) AS cnt " + "FROM UserInfo JOIN GroupInfo USING (group_id) "
				+ "WHERE group_id = ? " + "GROUP BY group_id ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });
		int countMem;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				countMem = rs.getInt("cnt");
				return countMem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0;
	}

	// group_id를 통해 그룹 이름을 반환
	public String findGroupName(int group_id) throws SQLException {
		String sql = "SELECT name " 
					+ "FROM GroupInfo " 
					+ "WHERE group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String group_name = rs.getString("name");
				return group_name;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// 생성된 그룹의 총 개수 반환
	public int findGroupCnt() throws SQLException {
		String sql = "SELECT COUNT(group_id) "
					+ "FROM GroupInfo";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0; //
	}
	
	//group_id의 리더 id를 반환
	public String findLeaderId(int group_id) { //그 그룹의 leader 찾아오기
		String sql = "SELECT leader_id " 
					+ "FROM GROUPINFO " 
					+ "WHERE group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String leader_id = rs.getString("leader_id");
				System.out.println("leader_id : " + leader_id);
				return leader_id;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// group_id의 리더 이름을 반환
	public String findLeaderName(int group_id) throws SQLException {
		String sql = "SELECT u.name AS name " 
					+ "FROM GroupInfo g JOIN UserInfo u ON g.leader_id = u.user_id "
					+ "WHERE g.group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// 다음 리더 후보를 반환
	public String findNextLeader(String user_id, int group_id) { 
		String sql = "SELECT user_id " 
					+ "FROM USERINFO " 
					+ "WHERE group_id=? AND NOT user_id =? "
					+ "AND NOT login_date IS NULL "
					+ "ORDER BY login_date DESC ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id, user_id});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			// 첫번째 레코드만 확인 (가장 최근 접속한 유저)
			if (rs.next()) {
				return rs.getString("user_id");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null; // 그룹장 외에 아무 멤버가 없다면 null을 반환
	}

	// 그룹의 리더 변경
	public int updateLeader(String leader_id, int group_id) throws SQLException{
		String sql = "UPDATE GROUPINFO " 
					+ "SET leader_id=? " 
					+ "WHERE group_id=?";
		Object[] param = new Object[] {leader_id, group_id};
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate(); 
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); 
		}
		return 0;
	}
	
	// 그룹 이름이 존재하는지 확인
	public boolean existingGroupName(String group_name) throws SQLException {
		String sql = "SELECT name " + "FROM GroupInfo " + "WHERE name = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_name });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	// 그룹 이름으로 그룹 아이디를 찾아냄
	public int findGroupId(String name) throws SQLException {
		String sql = "SELECT group_id " + "FROM GroupInfo " + "WHERE name=? AND ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { name });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("group_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0;
	}
	
	// 그룹 생성
	public int create(Group group) throws SQLException {
		String sql = "INSERT INTO GroupInfo VALUES (GROUP_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { group.getName(), group.getIcon(), group.getDescr(), group.getHbti_id(),
				group.getLeader_id(), group.getLimitation() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	// 그룹 정보 수정
	public int update(Group group) {
		String sql = "UPDATE GroupInfo " + "SET name=?, icon=?, descr=?, limitation=? " + "WHERE group_id=?";
		Object[] param = new Object[] { group.getName(), group.getIcon(), group.getDescr(), group.getLimitation(),
				group.getGroup_id() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	// 그룹 삭제
	public int delete(int group_id) throws SQLException {
		String sql = "DELETE FROM GroupInfo " + "WHERE group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

}
