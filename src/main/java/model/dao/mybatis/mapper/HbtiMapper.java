package model.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.HBTI;

public interface HbtiMapper {
	public HBTI selectHbtiByHbtiId(int hbti_id);

	public List<Integer> selectGroupByHbtiId(int hbti_id);

	public int selectGroupMemberNumByGroupId(int group_id);

	public List<String> selectUserListByGroupId(int group_id);
	
	public int countTodayChallengeUserByUserId(Map<String, String[]> userList);
}
