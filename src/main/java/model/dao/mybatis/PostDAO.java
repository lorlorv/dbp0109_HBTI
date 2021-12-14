package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.ChallengePost;
import model.dao.mybatis.mapper.PostMapper;

public class PostDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	// sqlSessionFactory 생성
	public PostDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	// user_id의 오늘 날짜 post를 찾음
	public ChallengePost findPost(String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.getMapper(PostMapper.class).selectPostByUserId(user_id);
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id의 오늘 날짜의 post list를 구함.
	public List<ChallengePost> findPostList(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.getMapper(PostMapper.class).selectPostListByGroupId(group_id);
		} finally {
			sqlSession.close();
		}
	}
	
	//post 추가
	public int addPost(ChallengePost post) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).insertPost(post);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
		
	//post 수정
	public int updatePost(ChallengePost post) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).updatePost(post);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	// 좋아요 버튼 1 추가
	public int updatePostLike(int post_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).updatePostLikeByPostId(post_id);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	// post 삭제
	public int deletePost(int post_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).deletePost(post_id);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	// group_id의 모든 포스트 삭제 (그룹 삭제용)
	public int deleteAllPost(int group_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).deleteAllPostByGroupId(group_id);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	// user_id의 모든 포스트 삭제 (그룹 탈퇴용)
	public int deleteUserAllPost(String user_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(PostMapper.class).deleteAllPostByUserId(user_id);
			if(result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
}
