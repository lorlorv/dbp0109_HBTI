package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.HBTI;
import model.dao.mybatis.mapper.HbtiMapper;

public class HbtiDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public HbtiDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public HBTI findHBTI(int hbti_id)throws SQLException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).selectHbtiByHbtiId(hbti_id);		
		} finally {
			sqlSession.close();
		}
	}	
	
	public String findHbtiImg(String name) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).selectHbtiImgByName(name);
		} finally {
			sqlSession.close();
		}
	}
	
	/* hbti가 hbti_id인 group_id 반환 */
	public List<Integer> group_idByHBTI(int hbti_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).selectGroupByHbtiId(hbti_id);
		} finally {
			sqlSession.close();
		}
	}
	


	/* 그 group에 해당하는 member수 구하기 */
	public int getNumberOfUsersInGroup(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).selectGroupMemberNumByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}

	/* 그 group에 해당하는 user_id 리스트 구하기 */
	public List<String> userListEachGroup(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).selectUserListByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}

	public int todayChallegeUserNum(List<String> userList) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(HbtiMapper.class).countTodayChallengeUserByUserId(userList);
		} finally {
			sqlSession.close();
		}
	}
}
