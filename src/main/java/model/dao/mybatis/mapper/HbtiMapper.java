package model.dao.mybatis.mapper;

import java.util.List;

import model.HBTI;

public interface HbtiMapper {
	public HBTI selectHbtiByHbtiId(int hbti_id);

	public List<Integer> selectGroupByHbtiId(int hbti_id);

	public String selectHbtiImgByName(String name);
	
	public int selectGroupMemberNumByGroupId(int group_id);

	public List<String> selectUserListByGroupId(int group_id);
	
	public int countTodayChallengeUserByUserId(List<String> userList);
}
