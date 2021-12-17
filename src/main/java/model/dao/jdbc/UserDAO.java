package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {
		jdbcUtil = new JDBCUtil(); //JDBCUtil ��ü ����
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
	// ���� ����
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
	
	//user_id�� �����ϴ��� Ȯ��
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
	
	// ���� ���� ����
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
	
	// ���� ����
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
	
	// �̸��� exitstingGroup���� ����?
	// user�� �׷��� �ִ��� Ȯ�� + groupId�� �����´�.
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
		return 0; // �׷� ������ ������ 0�� ��ȯ
	}
	
	// �ش� �׷쿡 ���� ��� ������ �׷� ������ ����
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
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	//user_id�� HBTI_id�� ã��
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
		return 0; //hbti_id�� ���ٸ� 0�� ��ȯ
	}
	
	// user ������ ã�Ƽ� ��ȯ
	public User findUser(String user_id) throws SQLException {
	      String sql = "SELECT password, name, descr, image, NVL(group_id, 0) AS group_id, hbti_id "
	            + "FROM USERINFO " 
	    		+ "WHERE user_id=?";
	      jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id }); // JDBCUtil   query문과 ׺��  �     ��  

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
	
	// ������ �׷� ���� ����
	public int updateUserGroupInfo(int group_id, String user_id) {
		String sql = "UPDATE UserInfo " + "SET group_id=?" + "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id, user_id });

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	//�ش� ������ �׷��� Ż��
	public int quitGroup(String user_id) {
		String sql = "UPDATE UserInfo " 
					+ "SET group_id=null " 
					+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id });

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}
	
	//hbti�� ������Ʈ
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
		String sql = "SELECT TO_CHAR(todo_date, 'yyyy/mm/dd') AS todo_date "
				+ "FROM TODO "
				+ "WHERE user_id=? AND is_done=1";
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
	
	public List<String> isChallenged(String user_id) throws SQLException {
		String sql = "SELECT TO_CHAR(write_date, 'yyyy/mm/dd') AS write_date "
				+ "FROM CHALLENGEPOST "
				+ "WHERE writer_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id}); 

		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<String> isChallenged = new ArrayList<String>();
			while (rs.next()) { 
				isChallenged.add(rs.getString("write_date"));
			}
			return isChallenged;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		return null;
	}
	
	/* hbti�� hbti_id�� group_id ��ȯ */
	public List<Integer> group_idByHBTI(int hbti_id) {
		String sql = "SELECT group_id FROM GROUPINFO WHERE hbti_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { hbti_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Integer> groupList = new ArrayList<>();
			while (rs.next()) {
				groupList.add(rs.getInt("group_id"));
			}
			return groupList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* �� group�� �ش��ϴ� member�� ���ϱ� */
	public int getNumberOfUsersInGroup(int group_id) {
		String sql = "SELECT COUNT(user_id) FROM UserInfo " + "WHERE group_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return 0;
	}

	/* �� group�� �ش��ϴ� user_id ����Ʈ ���ϱ� */
	public List<String> userListEachGroup(int group_id){
		String sql = "SELECT user_id FROM UserInfo WHERE group_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<String> userList = new ArrayList<>();
			while (rs.next()) {
				userList.add(rs.getString("user_id"));
			}
			return userList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* ���� �� user�� ç������ �ߴ���? */
	public boolean didChallengeUser(String user_id) {
		String sql = "SELECT COUNT(writer_id) AS cnt "
				+ "FROM CHALLENGEPOST "
				+ "WHERE writer_id=? AND write_date >= TO_DATE(sysdate)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				if (rs.getInt("cnt") > 0)
					return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}
}