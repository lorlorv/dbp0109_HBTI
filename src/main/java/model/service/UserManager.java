package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Group;
import model.User;
import model.dao.GroupDAO;
import model.dao.HBTIDAO;
import model.dao.PostDAO;
import model.dao.UserDAO;

public class UserManager {

	private GroupDAO groupDAO;
	private UserDAO userDAO;
	private HBTIDAO hbtiDAO;
	private PostDAO postDAO;
	private UserHBTIMatching matchRlt;
	
	public UserManager() {
		try {
			userDAO = new UserDAO();
			groupDAO = new GroupDAO();
			hbtiDAO = new HBTIDAO();
			postDAO = new PostDAO();
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
	
	//아이디와 패스워드로 로그인
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
		if (userDAO.existingUser(user.getUser_id()) == true) {
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
		return userDAO.remove(user_id);
	}
	
	// 그룹 탈퇴 + user_id의 모든 post 삭제
	public int quitGroup(String user_id, int group_id) throws SQLException {
		if(user_id.equals(groupDAO.findLeaderId(group_id))) { //leader라면
			String newLeader_id = groupDAO.findNextLeader(user_id, group_id);//다음 리더 찾고
			
			if(newLeader_id != null) {
				groupDAO.updateLeader(newLeader_id, group_id);
			}
			else { // 그룹에 남는 멤버가 없다면 그룹을 삭제
				postDAO.deleteAllPost(group_id);
				return deleteGroup(group_id);
			}
		}
		postDAO.deleteUserAllPost(user_id);
		return userDAO.quitGroup(user_id);
	}
	
	//HBTI가 업데이트되면 그룹 정보도 초기화
	public void updateHBTIGroup(String user_id, int oldHbti, int group_id) throws SQLException, UserHbtiException{	
		int nowHbti = findHBTI(user_id);
		if(oldHbti != nowHbti) { //hbti가 바뀌었다면
			//그룹이 있는 지 없는 지 판단
			if(group_id != 0) { //그룹이 있으면 leader인지 아닌지 확인
				if(user_id.equals(groupDAO.findLeaderId(group_id))) { //leader라면
					String nextLeader = groupDAO.findNextLeader(user_id, group_id);//다음 리더 찾고
					groupDAO.updateLeader(nextLeader, group_id);
				}
				//그룹 탈퇴
				userDAO.quitGroup(user_id);
			}
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
	
	
	//user의 hbti ID를 반환 (HBTI 테스트를 안했다면 0이 반환)
	public int findHBTI(String user_id) throws SQLException, UserHbtiException{
		int hbti_id = userDAO.findHBTI(user_id);
		
		if (hbti_id == 0) {
			throw new UserHbtiException(user_id + "의 HBTI가 존재하지 않습니다.");
		}
		return hbti_id;
	}
	
	//hbti의 이름을 반환
		public String findHbtiName(int hbti_id) throws SQLException, UserNotFoundException {
			String name = hbtiDAO.findHbtiName(hbti_id);

			if (name == null) {
				throw new UserNotFoundException(hbti_id + "는 존재하지 않습니다.");
			}
			return name;
		}
		
		// group_id의 그룹 이름 반환
		public String findGroupName(int group_id) throws SQLException, UserNotFoundException {
			return groupDAO.findGroupName(group_id);
		}
		
		// 현재 만들어진 그룹의 개수 반환 **hbti_id에 따라 나올 수 있게 수정 필요**
		public int findGroupCnt() throws SQLException {
			return groupDAO.findGroupCnt();
		}
		
		// 테스트 결과에 따라 HBTI 매칭
		public int updateHBTI(String user_id, String[] testRst) throws SQLException, UserNotFoundException {
			return matchRlt.matchingHBTIResult(user_id, testRst);
		}
		
	// 그룹에 소속되었는지를 확인
	public int belongToGroup(String user_id) throws SQLException{
		int group_id = userDAO.belongToGroup(user_id);
		
		return group_id;
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
	
	// 그룹 정보를 얻어옴
	public Group findGroup(int group_id) throws SQLException {
		// 그룹 기본 정보
		Group group = groupDAO.findGroup(group_id);
		
		System.out.println(group.getName() + "hellos");
		
		// 그룹 인원 정보
		int numOfMem = groupDAO.findNumberOfMember(group_id);
		
		group.setNumberOfMem(numOfMem);
		
		return group;
	}
	
	// 그룹 검색
	public List<Group> searchGroupList(int hbti_id, String keyword) throws SQLException {
		return groupDAO.searchGroupList(hbti_id, keyword);
	}
	
	// 그룹 가입
	public int joinGroup(int group_id, String user_id) throws SQLException, OverTheLimitException {
		Group g = groupDAO.findGroup(group_id);
		int num = groupDAO.findNumberOfMember(group_id);
		
		if(g.getLimitation() == num) {
			throw new OverTheLimitException("그룹 정원이 초과되었습니다.");
		}
		return userDAO.updateUserGroupInfo(group_id, user_id);
	}
	
	//그룹 생성
	public int createGroup(Group group) throws SQLException, OverTheLimitException {
		 if(group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if(group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
		groupDAO.create(group);
		int group_id = groupDAO.findGroupId(group.getName());
		return userDAO.updateUserGroupInfo(group_id, group.getLeader_id());
	}
	
	// 그룹 정보 수정
	public int updateGroup(Group group) throws SQLException, OverTheLimitException {		
		int numOfMem = groupDAO.findNumberOfMember(group.getGroup_id());
		
		if(numOfMem > group.getLimitation()) {
			throw new OverTheLimitException("그룹 정원을 늘리세요.");
		} else if(group.getLimitation() > 30) {
			throw new OverTheLimitException("그룹 정원은 30명을 초과할 수 없습니다.");
		} else if(group.getLimitation() < 2) {
			throw new OverTheLimitException("그룹 정원은 적어도 2명 이상이어야 합니다.");
		}
		return groupDAO.update(group);	
	}
	
	// 그룹 삭제
	public int deleteGroup(int group_id) throws SQLException{
		userDAO.deleteGroup(group_id);
		return groupDAO.delete(group_id);
	}
	
	// user_id의 todo 정보를 받아옴 ** 개선사항 : 날짜 파라미터를 받아 해당하는 달의 레코드만 select **
	public List<String> isTodo(String user_id) throws SQLException {
		return userDAO.isTodo(user_id);
	}
}
