package model.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Group;
import model.User;

public interface GroupMapper {
	
	public List<Group> selectGroupListByGroupId(int hbti_id);
	
	
	public List<Group> selectGroupListByKeyword(@Param("hbti_id") int hbti_id,
										@Param("keyword") String keyword);

	public List<User> selectUserListByGroupId(int group_id);
	
	
	public Group selectGroupByGroupId(int group_id);
	

	public int selectGroupIdByUserId(String user_id);
	
	
	public int selectGroupNumByGroupId(int group_id);
	

	public String selectGroupNameByGroupId(int group_id);
	
	
	public int selectGroupCntByHbtiId(int hbti_id);
	

	public Group selectGroupLeaderByGroupId(int group_id);
	
	
	public String selectNextLeaderByGroupId(@Param("leader_id") String leader_id,
											@Param("group_id") int group_id);
	
	
	public int updateLeader(@Param("leader_id") String leader_id,
							@Param("group_id") int group_id);
	
	public String selectExistingGroupName(String group_name);
	
	
	public int selectGroupIdByGroupName(String group_name);
	
	
	public int insertGroup(Group group);
	
	
	public int updateGroup(Group group);
	
	
	public int deleteGroup(int group_id);
	

	public int selectChallengeListCnt();
	

	public int updateChallengeList(int cntList);
}
