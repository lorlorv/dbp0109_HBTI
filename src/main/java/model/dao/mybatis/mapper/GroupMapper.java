package model.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Group;
import model.User;

public interface GroupMapper {
	// hbti_id의 그룹 리스트를 구함
	public List<Group> selectGroupListByGroupId(int hbti_id);
	
	// 키워드에 맞는 그룹 리스트를 반환
	public List<Group> selectGroupListByKeyword(@Param("hbti_id") int hbti_id,
										@Param("keyword") String keyword);
	// group_id의 그룹원 리스트를 구함
	public List<User> selectUserListByGroupId(int group_id);
	
	//group_id의 그룹 정보 반환
	public Group selectGroupByGroupId(int group_id);
	
	//user_id의 group_id를 구함
	public int selectGroupIdByUserId(String user_id);
	
	// group_id의 그룹원 인원을 반환
	public int selectGroupNumByGroupId(int group_id);
	
	//group_id를 통해 그룹 이름을 반환
	public String selectGroupNameByGroupId(int group_id);
	
	// hbti_id별 생성된 그룹의 총 개수 반환
	public int selectGroupCntByHbtiId(int hbti_id);
	
	//group_id의 리더 id를 반환
	public Group selectGroupLeaderByGroupId(int group_id);
	
	//그룹장 id를 제외한 다음 리더 후보를 반환
	public String selectNextLeaderByGroupId(@Param("leader_id") String leader_id,
											@Param("group_id") int group_id);
	
	// 그룹의 리더 변경
	public int updateLeader(@Param("leader_id") String leader_id,
							@Param("group_id") int group_id);
	
	// 그룹 이름이 존재하는지 확인
	public String selectExistingGroupName(String group_name);
	
	// 그룹 이름으로 그룹 아이디를 찾아냄
	public int selectGroupIdByGroupName(String group_name);
	
	// 그룹 생성
	public int insertGroup(Group group);
	
	// 그룹 정보 수정
	public int updateGroup(Group group);
	
	// 그룹 삭제
	public int deleteGroup(int group_id);
	
	// 챌린지 리스트 개수 반환
	public int selectChallengeListCnt();
	
	// 챌린지 리스트 업데이트 (스케줄러용)
	public int updateChallengeList(int cntList);
}
