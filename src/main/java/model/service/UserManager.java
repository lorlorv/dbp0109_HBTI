package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Group;
import model.User;
import model.dao.GroupDAO;
import model.dao.UserDAO;

public class UserManager {

	private GroupDAO groupDAO;
	private UserDAO userDAO;
	
	public UserManager() {
		try {
			userDAO = new UserDAO();
			groupDAO = new GroupDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private static UserManager userManager = new UserManager();
	
	public static UserManager getInstance() {
		return userManager;
	}
	
	public int isMatchWriter(String user_id, String writer_id) throws WriterMismatchException{
		if(!user_id.contentEquals(writer_id)) {
			throw new WriterMismatchException("게시물 작성자만 수정이 가능합니다.");
		}
		return 1;
	}
	
	// 그룹에 소속되었는지를 확인
	public int belongToGroup(String user_id) throws SQLException{
		int group_id = userDAO.belongToGroup(user_id);
		
		return group_id;
	}
	
	//user의 hbti ID를 반환
	public int findHBTI(String user_id) throws SQLException{
		int hbti_id = userDAO.findHBTI(user_id);
		return hbti_id;
	}
	
	// hbti에 따라 그룹 리스트를 반환
	public List<Group> findGroupList(int user_HBTI) throws SQLException {
		List<Group> groupList = groupDAO.findGroupList(user_HBTI);
		
		// 얻어온 그룹 리스트에 멤버 인원을 추가
		for(int i = 0; i < groupList.size(); i++) {
			Group group = groupList.get(i);
			int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());
			group.setNumberOfMem(numOfMem);
		}
		return groupList;		
	}
	
	public Group findGroup(int group_id) throws SQLException {
		// 그룹 기본 정보
		Group group = groupDAO.findGroup(group_id);
		
		// 그룹 인원 정보
		int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());
		group.setNumberOfMem(numOfMem);
		
		return group;
	}
	
	public List<Group> searchGroupList(int hbti_id, String keyword) throws SQLException {
		return groupDAO.searchGroupList(hbti_id, keyword);
	}
	
	public int joinGroup(int group_id, String user_id) throws OverTheLimitException {
		Group g = groupDAO.findGroup(group_id);
		int num = groupDAO.findNumberOfMember(group_id);
		
		if(g.getLimitation() == num) {
			throw new OverTheLimitException("그룹 정원이 초과되었습니다.");
		}
		return groupDAO.updateMember(group_id, user_id);
	}
	
	public int createGroup(Group group) throws OverTheLimitException {
		int num = groupDAO.findNumberOfMember(group.getGroup_id());
		
		if(num > group.getLimitation()) {
			throw new OverTheLimitException("그룹 정원을 늘리세요.");
		} else if(group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if(group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
		groupDAO.create(group);
		int group_id = groupDAO.findGroupId(group.getName());
		return groupDAO.updateMember(group_id, group.getLeader_id());
	}
	
	public User findUser(String user_id) throws SQLException {
		return userDAO.findUser(user_id);
	}
	
	public int updateGroup(Group group) throws OverTheLimitException {		
		int num = groupDAO.findNumberOfMember(group.getGroup_id());
		
		if(num > group.getLimitation()) {
			throw new OverTheLimitException("그룹 정원을 늘리세요.");
		} else if(group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if(group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
		return groupDAO.update(group);	
	}
	
	public int deleteGroup(int group_id, List<User> userList) {
		for(int i = 0; i < userList.size(); i++) {
			groupDAO.quitMember(userList.get(i).getUser_id());
		}
		return groupDAO.delete(group_id);
	}
}
