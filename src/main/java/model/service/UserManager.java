package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Group;
import model.User;
import model.dao.mybatis.HbtiDAO;
import model.dao.mybatis.UserDAO;
import model.dao.mybatis.GroupDAO;
import model.dao.mybatis.PostDAO;
import model.dao.mybatis.TodoDAO;
import model.service.exception.ExistingGroupException;
import model.service.exception.ExistingUserException;
import model.service.exception.OverTheLimitException;
import model.service.exception.PasswordMismatchException;
import model.service.exception.UserHbtiException;
import model.service.exception.UserNotFoundException;

public class UserManager {

	private GroupDAO groupDAO;
	private UserDAO userDAO;
	private HbtiDAO hbtiDAO;
	private PostDAO postDAO;
	private TodoDAO todoDAO;
	private UserHBTIMatching matchRlt;

	public UserManager() {
		try {
			userDAO = new UserDAO();
			groupDAO = new GroupDAO();
			hbtiDAO = new HbtiDAO();
			postDAO = new PostDAO();
			todoDAO = new TodoDAO();
			matchRlt = new UserHBTIMatching(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static UserManager userManager = new UserManager();

	public static UserManager getInstance() {
		return userManager;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}

	// 아이디와 패스워드로 로그인
	public boolean login(String user_id, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(user_id);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public int updateLoginDate(String user_id) throws SQLException {
		return userDAO.updateLoginDate(user_id);
	}


	// user 생성
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUser_id()) != 0) {
			throw new ExistingUserException(user.getUser_id() + "는 존재하는 아이디입니다.");
		}

		return userDAO.create(user);
	}

	// user 정보 수정
	public int update(User user) throws SQLException, UserNotFoundException {

		return userDAO.update(user);
	}

	// user 삭제
	public int remove(String user_id) throws SQLException, UserNotFoundException {
		int group_id = groupDAO.findGroupUserId(user_id);
		if (group_id != 0) {
			quitGroup(user_id, group_id);
		}
		todoDAO.deleteUserAllTodo(user_id);
		return userDAO.remove(user_id);
	}

	// 그룹 탈퇴 + user_id의 모든 post 삭제
	public int quitGroup(String user_id, int group_id) throws SQLException {
    
		Group g = groupDAO.findLeader(group_id);
		if(user_id.equals(g.getLeader_id())) { //leader라면
			String newLeader_id = groupDAO.findNextLeader(user_id, group_id);//다음 리더 찾고
			
			if(newLeader_id != null) {
				groupDAO.updateLeader(newLeader_id, group_id);
				postDAO.deleteUserAllPost(user_id);
			} else { // 그룹에 남는 멤버가 없다면 그룹을 삭제
				postDAO.deleteAllPost(group_id);
				return deleteGroup(group_id);
			}
		}

		else
			postDAO.deleteUserAllPost(user_id);

		return userDAO.quitGroup(user_id);
	}

	// HBTI가 업데이트되면 그룹 정보도 초기화
	public void updateHBTIGroup(String user_id, int oldHbti, int group_id) throws SQLException, UserHbtiException {
		int nowHbti = findHBTI(user_id);
		if (oldHbti != nowHbti) { // hbti가 바뀌었다면
			quitGroup(user_id, group_id);
		}
		// 바뀌지 않았다면 아무것도 안해도 된다.
	}

	// user_id의 정보를 반환
	public User findUser(String user_id) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(user_id);

		if (user == null) {
			throw new UserNotFoundException(user_id + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}

	// user의 hbti ID를 반환 (HBTI 테스트를 안했다면 0이 반환)
	public int findHBTI(String user_id) throws SQLException, UserHbtiException {
		int hbti_id = userDAO.findUser(user_id).getHbti_id();

		return hbti_id;
	}

	// hbti의 이름을 반환
	public String findHbtiName(int hbti_id) throws SQLException, UserNotFoundException {
		String name = hbtiDAO.findHBTI(hbti_id).getName();
		return name;
	}

	// group_id의 그룹 이름 반환
	public String findGroupName(int group_id) throws SQLException, UserNotFoundException {
		return groupDAO.findGroupName(group_id);
	}

	// 현재 만들어진 그룹의 개수 반환 **hbti_id에 따라 나올 수 있게 수정 필요 -> 수정완료!**
	public int findGroupCnt(int hbti_id) throws SQLException {
		return groupDAO.findGroupCnt(hbti_id);
	}
  
	// 테스트 결과에 따라 HBTI 매칭
	public int updateHBTI(String user_id, String[] testRst) throws SQLException, UserNotFoundException {
		return matchRlt.matchingHBTIResult(user_id, testRst);
	}

	// 그룹에 소속되었는지를 확인
	public int belongToGroup(String user_id) throws SQLException {
		int group_id = userDAO.belongToGroup(user_id);

		return group_id;
	}

	// hbti에 따라 그룹 리스트를 반환
	public List<Group> findGroupList(int user_HBTI) throws SQLException {
		List<Group> groupList = groupDAO.findGroupList(user_HBTI);

		// 얻어온 그룹 리스트에 멤버 인원을 추가
		for (int i = 0; i < groupList.size(); i++) {
			Group group = groupList.get(i);
			int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());
			group.setNumberOfMem(numOfMem);
		}
		return groupList;
	}

	// 그룹 정보를 얻어옴
	public Group findGroup(int group_id) throws SQLException {
		// 그룹 기본 정보
		Group group = groupDAO.findGroup(group_id);

		// 그룹 인원 정보
		int numOfMem = groupDAO.findNumberOfMember(group_id);

		group.setNumberOfMem(numOfMem);

		return group;
	}

	// 그룹 검색
	public List<Group> searchGroupList(int hbti_id, String keyword) throws SQLException {
		List<Group> searcgGroupList = groupDAO.searchGroupList(hbti_id, keyword);
		// 얻어온 그룹 리스트에 멤버 인원을 추가
		for(int i = 0; i < searcgGroupList.size(); i++) {
			Group group = searcgGroupList.get(i);
			int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());
			group.setNumberOfMem(numOfMem);
		}
		return searcgGroupList;
	}

	// 그룹 가입
	public int joinGroup(int group_id, String user_id) throws SQLException, OverTheLimitException {
		Group g = groupDAO.findGroup(group_id);
		int num = groupDAO.findNumberOfMember(group_id);

		if (g.getLimitation() == num) {
			throw new OverTheLimitException("그룹 정원이 초과되었습니다.");
		}
		return userDAO.updateUserGroupInfo(group_id, user_id);
	}

	//그룹 생성
	public int createGroup(Group group) throws SQLException, OverTheLimitException, ExistingGroupException {
		 if(group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if (group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
		 
		 boolean exist = groupDAO.existingGroupName(group.getName());
			if(exist) {
				throw new ExistingGroupException("이미 존재하는 그룹 이름입니다.");
			}
			
		groupDAO.create(group);
		int group_id = groupDAO.findGroupId(group.getName());
		return userDAO.updateUserGroupInfo(group_id, group.getLeader_id());
	}

	// 그룹 정보 수정
	public int updateGroup(Group group) throws OverTheLimitException, ExistingGroupException {		

		int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());

		if (numOfMem > group.getLimitation()) {
			throw new OverTheLimitException("그룹 정원을 늘리세요.");
		} else if (group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if (group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
    
		boolean exist = groupDAO.existingGroupName(group.getName());
		if(exist) {
			throw new ExistingGroupException("이미 존재하는 그룹 이름입니다.");
		}
		return groupDAO.update(group);
	}

	// 그룹 삭제
	public int deleteGroup(int group_id) throws SQLException {
		userDAO.deleteGroup(group_id);
		return groupDAO.delete(group_id);
	}

	// user_id의 todo 정보를 받아옴
	public List<String> isTodo(String user_id) throws SQLException {
		return userDAO.isTodo(user_id);
	}
	// user_id의 Challenge 정보를 받아옴
	public List<String> isChallenged(String user_id) throws SQLException {
		return userDAO.isChallenged(user_id);
	}
}
