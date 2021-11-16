package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {
		jdbcUtil = new JDBCUtil(); //JDBCUtil 객체 생성
	}
	
	public int updateLoginDate(String user_id) throws SQLException {
		String sql = "UPDATE USERINFO " 
					+ "SET login_date=sysdate " 
					+ "WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] { user_id });

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
	// 유저 생성
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO UserInfo (user_id, password, name, descr, image) VALUES (?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUser_id(), user.getPassword(), user.getName(),
				(user.getDescr() != "") ? user.getDescr() : null, (user.getImage() != "") ? user.getImage() : null };
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
	
	//user_id가 존재하는지 확인
	public boolean existingUser(String user_id) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id }); 

		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		return false;
	}
	
	// 유저 정보 수정
	public int update(User user) throws SQLException {
		String sql = "UPDATE USERINFO "
					+ "SET password=?, name=?, descr=?, image=? "
					+ "WHERE user_id=?";
		Object[] param = new Object[] { user.getPassword(), user.getName(),
				(user.getDescr() != "") ? user.getDescr() : null, (user.getImage() != "") ? user.getImage() : null,
				user.getUser_id() };
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
	
	// 유저 삭제
	public int remove(String user_id) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id }); 

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
	
	// 이름을 exitstingGroup으로 변경?
	// user가 그룹이 있는지 확인 + groupId를 가져온다.
	public int belongToGroup(String user_id) throws SQLException {
		String sql = "SELECT group_id "
				+ "FROM UserInfo "
				+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				int group_id = rs.getInt("group_id");
				return group_id;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0; // 그룹 정보가 없으면 0을 반환
	}
	
	// 해당 그룹에 속한 모든 유저의 그룹 정보를 삭제
	public int deleteGroup(int group_id) {
		String sql = "UPDATE UserInfo "
				+ "SET group_id=null "
				+ "WHERE group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	//user_id의 HBTI_id를 찾음
	public int findHBTI(String user_id) throws SQLException {
		String sql = "SELECT NVL(hbti_id, 0) AS hbti_id "
				+ "FROM UserInfo "
				+ "WHERE user_id=? ";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				int hbti_id = rs.getInt("hbti_id");
				return hbti_id;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0; //hbti_id가 없다면 0을 반환
	}
	
	// user 정보를 찾아서 반환
	public User findUser(String user_id) throws SQLException {
	      String sql = "SELECT password, name, descr, image, NVL(group_id, 0) AS group_id, hbti_id "
	            + "FROM USERINFO " 
	    		+ "WHERE user_id=?";
	      jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id }); // JDBCUtil   query臾멸낵 留ㅺ  蹂     ㅼ  

	      try {
	         ResultSet rs = jdbcUtil.executeQuery(); 
	         if (rs.next()) {  
	            User user = new User(   
	                  user_id, rs.getString("password"), rs.getString("name"), rs.getString("descr"),
	                  rs.getString("image"), rs.getInt("group_id"), rs.getInt("hbti_id") );
	            return user;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();  
	      }
	      return null;
	   }
	
	// 유저의 그룹 정보 변경
	public int updateUserGroupInfo(int group_id, String user_id) {
		String sql = "UPDATE UserInfo " + "SET group_id=?" + "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id, user_id });

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
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

	//해당 유저는 그룹을 탈퇴
	public int quitGroup(String user_id) {
		String sql = "UPDATE UserInfo " 
					+ "SET group_id=null " 
					+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id });

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
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
	
	//hbti를 업데이트
	public int updateHBTI(String user_id, int hbti_id) throws SQLException {
		String sql = "UPDATE USERINFO " + "SET hbti_id=? " + "WHERE user_id=?";
		Object[] param = new Object[] { hbti_id, user_id };
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
	
	public List<String> isTodo(String user_id) throws SQLException {
		String sql = "SELECT TO_CHAR(todo_date, 'yyyy/mm/dd') AS todo_date FROM TODO WHERE user_id=? AND is_done=1";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id}); 

		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<String> isTodo = new ArrayList<String>();
			while (rs.next()) { 
				isTodo.add(rs.getString("todo_date"));
			}
			return isTodo;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		return null;
	}
}
