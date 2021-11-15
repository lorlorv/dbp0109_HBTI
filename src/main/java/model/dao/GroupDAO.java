package model.dao;

import model.Group;
import model.User;
import model.ChallengePost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GroupDAO {

	private JDBCUtil jdbcUtil = null;
	
	public GroupDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	// 최근에 생선된 그룹부터 보여줌
	public List<Group> findGroupList(int user_hbti) {
		 String sql = "SELECT group_id, name, icon, descr ,limitation "
     			+ "FROM GroupInfo "
     			+ "WHERE hbti_id=? "
     			+ "ORDER BY group_id DESC "; 
		 jdbcUtil.setSqlAndParameters(sql, new Object[] {user_hbti});	// JDBCUtil에 query문과 매개 변수 설정
		 List<Group> groupList = new ArrayList<Group>();
		 try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				while(rs.next()) {
					Group g = new Group(
							rs.getInt("group_id"),
							rs.getString("name"),
							rs.getString("icon"),
							rs.getString("descr"),
							rs.getInt("limitation"));
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
	
	// 그룹원 인원을 반환
	public int findNumberOfMember (int group_id) {
		String sql = "SELECT count(*) AS cnt "
				+ "FROM UserInfo JOIN GroupInfo USING (group_id) "
				+ "WHERE group_id = ? "
				+ "GROUP BY group_id ";
		 jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		 int countMem;
		 try {
			 ResultSet rs = jdbcUtil.executeQuery();
			 if(rs.next()) {
				 countMem = rs.getInt("cnt");
				 return countMem;
			 }
		 } catch (Exception e) { e.printStackTrace(); }
		 finally { jdbcUtil.close(); }
		 return 0;
	}
	
	// 그룹 정보 반환
	public Group findGroup(int group_id) {
		String sql = "SELECT  g.name AS g_name, icon, g.descr AS g_descr, leader_id , g.hbti_id AS hbti_id, content, limitation "
				+ "FROM GroupInfo g, DayOfChallenge d, Challenge c "
				+ "WHERE g.hbti_id = d.hbti_id AND d.challenge_id = c.challenge_id " + 
				"AND g.group_id = ? ";
		 jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		 try {
			 ResultSet rs = jdbcUtil.executeQuery();
			 if(rs.next()) {
				 Group group = new Group(
						 group_id,
						 rs.getString("g_name"),
						 rs.getString("icon"),
						 rs.getString("g_descr"),
						 rs.getInt("limitation")
						 );
				 group.setLeader_id(rs.getString("leader_id"));
				 group.setHbti_id(rs.getInt("hbti_id"));
				 group.setChallengeContent(rs.getString("content"));
				 
				 return group;
			 }
		 } catch (Exception e) { e.printStackTrace(); }
		 finally { jdbcUtil.close(); }
		 return null;
	}
	public String findLeaderName(int group_id) {
		String sql = "SELECT u.name AS name "
				+ "FROM GroupInfo g JOIN UserInfo u ON g.leader_id = u.user_id "
				+ "WHERE g.group_id=? ";
		 jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		 try {
			 ResultSet rs = jdbcUtil.executeQuery();
			 if(rs.next()) {
				 return rs.getString("name");
			 }
		 } catch (Exception e) { e.printStackTrace(); }
		 finally { jdbcUtil.close(); }
		 return null;
	}
	
	// 그룹원 리스트를 구함
	public List<User> findUserList(int group_id) {
		String sql = "SELECT user_id, name, image, login_date "
				+ "FROM UserInfo "
				+ "WHERE group_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		 try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				List<User> userList = new ArrayList<User>();
				while(rs.next()) {
					userList.add(new User(
							rs.getString("user_id"),
							rs.getString("name"),
							rs.getString("image"),
							rs.getString("login_date")));
				}
				return userList;
		 } catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
	}
	
	// 오늘 날짜의 post list를 구함.
	public List<ChallengePost> findPostList(int group_id) {
		String sql = "SELECT name, writer_id, content, p.image AS postImage, like_btn "
				+ "FROM ChallengePost p JOIN UserInfo u ON writer_id = user_id "
				+ "WHERE p.group_id = ? AND write_date >= TO_DATE(sysdate) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ChallengePost> postList = new ArrayList<ChallengePost>();
			
			while(rs.next()) {
				ChallengePost post = new ChallengePost(
						rs.getString("name"),
						rs.getString("writer_id"),
						rs.getString("content"),
						rs.getString("postImage"),
						rs.getInt("like_btn"));
				postList.add(post);
			}
			return postList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Group> searchGroupList(int hbti_id, String keyword) {
		String sql = "SELECT group_id, name, icon, descr, limitation "
     			+ "FROM GroupInfo "
     			+ "WHERE hbti_id=? AND name LIKE ? "
     			+ "ORDER BY group_id DESC "; 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {hbti_id, keyword});
		List<Group> groupList = new ArrayList<Group>();
		 try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				while(rs.next()) {
					Group g = new Group(
							rs.getInt("group_id"),
							rs.getString("name"),
							rs.getString("icon"),
							rs.getString("descr"),
							rs.getInt("limitation"));
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
	
	public int updateMember(int group_id, String user_id){
		String sql = "UPDATE UserInfo "
				+ "SET group_id=?"
				+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id, user_id});
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	public int quitMember(String user_id) {
		String sql = "UPDATE UserInfo "
				+ "SET group_id=null "
				+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	public int create(Group group) {
		String sql = "INSERT INTO GroupInfo VALUES (GROUP_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {group.getName(), group.getIcon(), group.getDescr(), group.getHbti_id(), group.getLeader_id(), group.getLimitation()}; 
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
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

	public int update(Group group) {
		String sql = "UPDATE GroupInfo "
				+ "SET name=?, icon=?, descr=?, limitation=? "
				+ "WHERE group_id=?";
		Object[] param = new Object[] {group.getName(), group.getIcon(), group.getDescr(), group.getLimitation(), group.getGroup_id()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
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
	
	public boolean existingGroupName(String group_name) {
		String sql = "SELECT name "
				+ "FROM GroupInfo "
				+ "WHERE name = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_name});
		 try {
			 ResultSet rs = jdbcUtil.executeQuery();
			 if(rs.next()) {
				 return true;
			 }
		 } catch (Exception e) { e.printStackTrace(); }
		 finally { jdbcUtil.close(); }
		 return false;
	}
	
	public int findGroupId(String name) {
		String sql = "SELECT group_id "
				+ "FROM GroupInfo "
				+ "WHERE name=? AND ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {name});
		 try {
			 ResultSet rs = jdbcUtil.executeQuery();
			 if(rs.next()) {
				 return rs.getInt("group_id");
			 }
		 } catch (Exception e) { e.printStackTrace(); }
		 finally { jdbcUtil.close(); }
		 return 0;
	}
	
	public ChallengePost findPost(String user_id) {
		String sql = "SELECT name, writer_id, content, p.image AS postImage, like_btn "
				+ "FROM ChallengePost p JOIN UserInfo u ON writer_id = user_id "
				+ "WHERE u.user_id = ? AND write_date >= TO_DATE(sysdate) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				ChallengePost post = new ChallengePost(
						rs.getString("name"),
						rs.getString("writer_id"),
						rs.getString("content"),
						rs.getString("postImage"),
						rs.getInt("like_btn"));
				return post;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public int addPost(ChallengePost post, int group_id) {
		String sql = "INSERT INTO ChallengePost VALUES (challenge_seq.nextval, sysdate, ?, ?, 0, ?, ?)";
		Object[] param = new Object[] {post.getContent(), post.getImage(), group_id, post.getWriter_id()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
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
	
	public int delete(int group_id) {
		String sql = "DELETE FROM GroupInfo "
				+ "WHERE group_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {group_id});
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
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
}
