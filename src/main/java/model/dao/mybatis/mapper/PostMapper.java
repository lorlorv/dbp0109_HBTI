package model.dao.mybatis.mapper;

import java.util.List;

import model.ChallengePost;

public interface PostMapper {

	
	public ChallengePost selectPostByUserId(String user_id);
	

	public List<ChallengePost> selectPostListByGroupId(int group_id);
	
	
	public int insertPost(ChallengePost post);
	
	
	public int updatePost(ChallengePost post);
	
	
	public int updatePostLikeByPostId(int post_id);
	

	public int deletePost(int post_id);
	

	public int deleteAllPostByGroupId(int group_id);
	
	
	public int deleteAllPostByUserId(String writer_id);
}
