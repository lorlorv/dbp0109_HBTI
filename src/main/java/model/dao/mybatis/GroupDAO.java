package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.Group;
import model.User;
import model.dao.mybatis.mapper.GroupMapper;

public class GroupDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	
	public GroupDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	public List<Group> findGroupList(int hbti_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupListByGroupId(hbti_id);
		} finally {
			sqlSession.close();
		}
	}
	

	public List<Group> searchGroupList(int hbti_id, String keyword) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupListByKeyword(hbti_id, keyword);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<User> findUserList(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectUserListByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	

	public Group findGroup(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int findGroupUserId(String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupIdByUserId(user_id);
		} finally {
			sqlSession.close();
		}
	}
	

	public int findNumberOfMember(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupNumByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public String findGroupName(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupNameByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int findGroupCnt(int hbti_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupCntByHbtiId(hbti_id);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public Group findLeader(int group_id) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupLeaderByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	

	public String findNextLeader(String user_id, int group_id) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectNextLeaderByGroupId(user_id, group_id);
		} finally {
			sqlSession.close();
		}
	}
	

	
	public int updateLeader(String leader_id, int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).updateLeader(leader_id, group_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	
	public boolean existingGroupName(String group_name) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String name = sqlSession.getMapper(GroupMapper.class).selectExistingGroupName(group_name);
			
			if(name != null) return true;
			return false;
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int findGroupId(String group_name) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupIdByGroupName(group_name);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int create(Group group) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).insertGroup(group);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	

	public int update(Group group) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).updateGroup(group);
			System.out.println(result);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int delete(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).deleteGroup(group_id);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int cntOfChallengeList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectChallengeListCnt();
		} finally {
			sqlSession.close();
		}
	}

	public int assignChallenge(int cntList) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).updateChallengeList(cntList);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
		
}
