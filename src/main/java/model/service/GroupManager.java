package model.service;

import java.sql.SQLException;
import java.util.List;

import model.ChallengePost;
import model.Group;
import model.User;
import model.dao.GroupDAO;
import model.dao.UserDAO;
import model.dao.PostDAO;

public class GroupManager {
	private GroupDAO groupDAO;
	private UserDAO userDAO;
	private PostDAO postDAO;
	
	public GroupManager() {
		try {
			groupDAO = new GroupDAO();
			userDAO = new UserDAO();
			postDAO = new PostDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static GroupManager groupManager = new GroupManager();
	
	public static GroupManager getInstance() {
		return groupManager;
	}
	
	public List<User> findUserList(int group_id) {
		return groupDAO.findUserList(group_id);
	}
	
	public List<ChallengePost> findPostList(int group_id) {
		return groupDAO.findPostList(group_id);
	}
	
	public String findLeaderName(int group_id) {
		return groupDAO.findLeaderName(group_id);
	}
	
	// user가 챌린지 post를 등록하였는지 확인
	public boolean isPost(String user_id) throws ExistingChallengePostException {
		if(groupDAO.findPost(user_id) != null) {
			throw new ExistingChallengePostException("이미 챌린지를 인증하셨습니다. 내일의 챌린지를 기대해주세요!");
		}
		return false;
	}
	// user가 등록한 post를 가져옴.
	public ChallengePost findPost (String user_id) {
		return groupDAO.findPost(user_id);
	}
	
	public int addPost(ChallengePost post, int group_id) {
		return groupDAO.addPost(post, group_id);
	}
	
	public int quitMember(int group_id, String quit_id, String user_id) throws DoNotQuitLeaderException {
		if(quit_id.equals(user_id)) {
			throw new DoNotQuitLeaderException("그룹장은 강퇴할 수 없습니다.");
		}
		return groupDAO.quitMember(quit_id);
	}
}
