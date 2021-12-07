package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ChallengePost;

public class PostDAO {

	private JDBCUtil jdbcUtil = null;

	public PostDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	// user_id의 오늘 날짜 post를 찾음
	public ChallengePost findPost(String user_id) throws SQLException {
		String sql = "SELECT post_id, name, writer_id, content, p.image AS postImage, like_btn "
				+ "FROM ChallengePost p JOIN UserInfo u ON writer_id = user_id "
				+ "WHERE u.user_id = ? AND write_date >= TO_DATE(sysdate) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { user_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				ChallengePost post = new ChallengePost(rs.getInt("post_id"), rs.getString("name"),
						rs.getString("writer_id"), rs.getString("content"), rs.getString("postImage"),
						rs.getInt("like_btn"));
				return post;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// group_id의 오늘 날짜의 post list를 구함.
	public List<ChallengePost> findPostList(int group_id) throws SQLException {
		String sql = "SELECT post_id, name, writer_id, content, p.image AS postImage, like_btn "
				+ "FROM ChallengePost p JOIN UserInfo u ON writer_id = user_id "
				+ "WHERE p.group_id = ? AND write_date >= TO_DATE(sysdate) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { group_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ChallengePost> postList = new ArrayList<ChallengePost>();

			while (rs.next()) {
				ChallengePost post = new ChallengePost(rs.getInt("post_id"), rs.getString("name"),
						rs.getString("writer_id"), rs.getString("content"), rs.getString("postImage"),
						rs.getInt("like_btn"));
				postList.add(post);
			}
			return postList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	//post 추가
	public int addPost(ChallengePost post, int group_id) throws SQLException {
		String sql = "INSERT INTO ChallengePost VALUES (challenge_seq.nextval, sysdate, ?, ?, 0, ?, ?)";
		Object[] param = new Object[] { post.getContent(), post.getImage(), group_id, post.getWriter_id() };
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

	//post 수정
	public int updatePost(ChallengePost post) {
		String sql = "UPDATE ChallengePost " + "SET content=?, image=? " + "WHERE post_id=?";
		Object[] param = new Object[] { post.getContent(), post.getImage(), post.getPost_id() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
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

	// 좋아요 버튼 1 추가
	public int updatePostLike(int post_id) {
		String sql = "UPDATE ChallengePost " + "SET like_btn = like_btn + 1 " + "WHERE post_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { post_id });
		try {
			int result = jdbcUtil.executeUpdate();
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

	// post 삭제
	public int deletePost(int post_id) throws SQLException {
		String sql = "DELETE FROM ChallengePost " + "WHERE post_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { post_id });

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

	// group_id의 모든 포스트 삭제 (그룹 삭제)
	public int deleteAllPost(int group_id) throws SQLException {
		String sql = "DELETE FROM ChallengePost " + "WHERE group_id=? ";
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
	
	// user_id의 모든 포스트 삭제 (그룹 탈퇴)
	public int deleteUserAllPost(String user_id) {
		String sql = "DELETE FROM CHALLENGEPOST WHERE writer_id=?";
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
}