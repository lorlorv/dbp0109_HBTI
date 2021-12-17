package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.User;
import model.dao.mybatis.mapper.UserMapper;

public class UserDAO {
	private SqlSessionFactory sqlSessionFactory;

	public UserDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	// 유저 생성
	public int create(User user) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).insertUser(user);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int updateLoginDate(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).updateLoginDate(user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 유저 정보 수정
	public int update(User user) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).updateUser(user);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 해당 그룹에 속한 모든 유저의 그룹 정보를 삭제
	public int deleteGroup(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).deleteGroup(group_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 해당 유저는 그룹을 탈퇴
	public int quitGroup(String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).quitGroup(user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 유저의 그룹 정보 변경
	public int updateUserGroupInfo(int group_id, String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).updateUserGroup(group_id, user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// hbti를 업데이트
	public int updateHBTI(String user_id, int hbti_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).updateHBTI(hbti_id, user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 유저 삭제
	public int remove(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).deleteUser(user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// user_id가 존재하는지 확인
	public int existingUser(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).existingUser(user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 이름을 exitstingGroup으로 변경?
	// user가 그룹이 있는지 확인 + groupId를 가져온다.
	public int belongToGroup(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).existingGroup(user_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// user 정보를 찾아서 반환
	public User findUser(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(UserMapper.class).selectUserByUserId(user_id);		
		} finally {
			sqlSession.close();
		}
	}

	public List<String> isTodo(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(UserMapper.class).selectTodoListByUserId(user_id);
		} finally {
			sqlSession.close();
		}
	}

	public List<String> isChallenged(String user_id) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(UserMapper.class).selectChallengePostListByUserId(user_id);	
		} finally {
			sqlSession.close();
		}
	}


}
