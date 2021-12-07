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
	
	// sqlSessionFactory 생성
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
	
	// hbti_id의 그룹 리스트를 구함 O
	public List<Group> findGroupList(int hbti_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupListByGroupId(hbti_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// 키워드에 맞는 그룹 리스트를 반환 O
	public List<Group> searchGroupList(int hbti_id, String keyword) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupListByKeyword(hbti_id, keyword);
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id의 그룹원 리스트를 구함 O
	public List<User> findUserList(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectUserListByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id의 그룹 정보를 반환 O
	public Group findGroup(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// 유저ID의 그룹ID를 찾아옴
	public int findGroupUserId(String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupIdByUserId(user_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id의 그룹원 인원을 반환
	public int findNumberOfMember(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupNumByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id를 통해 그룹 이름을 반환
	public String findGroupName(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupNameByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// 생성된 그룹의 총 개수 반환 O
	public int findGroupCnt(int hbti_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupCntByHbtiId(hbti_id);
		} finally {
			sqlSession.close();
		}
	}
	
	//group_id의 리더 정보(name, id) 반환
	public Group findLeader(int group_id) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupLeaderByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// 다음 리더 후보를 반환
	public String findNextLeader(String user_id, int group_id) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectNextLeaderByGroupId(user_id, group_id);
		} finally {
			sqlSession.close();
		}
	}
	

	// 그룹의 리더 변경
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
	
	// 그룹 이름이 존재하는지 확인
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
	
	// 그룹 이름으로 그룹 아이디를 찾아냄
	public int findGroupId(String group_name) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectGroupIdByGroupName(group_name);
		} finally {
			sqlSession.close();
		}
	}
	
	// 그룹 생성 O
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
	
	// 그룹 정보 수정 O
	public int update(Group group) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(GroupMapper.class).updateGroup(group);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	// 그룹 삭제
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
	
	// 챌린지 리스트 개수 반환
	public int cntOfChallengeList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(GroupMapper.class).selectChallengeListCnt();
		} finally {
			sqlSession.close();
		}
	}
	// 랜덤으로 챌린지 배정
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