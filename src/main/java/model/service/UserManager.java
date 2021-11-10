package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Group;
import model.dao.GroupDAO;

public class UserManager {

	private GroupDAO groupDAO;
	
	public UserManager() {
		try {
			groupDAO = new GroupDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private static UserManager userManager = new UserManager();
	
	public static UserManager getInstance() {
		return userManager;
	}
	
	public List<Group> findGroupList(int user_HBTI, int currentPage) throws SQLException {
		return groupDAO.findGroupList(user_HBTI, currentPage);
	}
}
