package model.dao.mybatis.mapper;

import java.util.List;

import model.ChallengePost;

public interface PostMapper {

	// user_id의 오늘 날짜 post를 찾음
	public ChallengePost selectPostByUserId(String user_id);
	
	// group_id의 오늘 날짜의 post list를 구함.
	public List<ChallengePost> selectPostListByGroupId(int group_id);
	
	// post 추가
	public int insertPost(ChallengePost post);
	
	// post 수정
	public int updatePost(ChallengePost post);
	
	// 좋아요 버튼 1 추가
	public int updatePostLikeByPostId(int post_id);
	
	// post 삭제
	public int deletePost(int post_id);
	
	// group_id의 모든 포스트 삭제 (그룹 삭제)
	public int deleteAllPostByGroupId(int group_id);
	
	// user_id의 모든 포스트 삭제 (그룹 탈퇴)
	public int deleteAllPostByUserId(String writer_id);
}
