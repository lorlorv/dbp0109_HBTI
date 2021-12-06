package model.dao.mybatis.mapper;

import java.util.List;
import model.User;

public interface UserMapper {

	public int insertUser(User user);   
 
	public int updateLoginDate(String user_id);
	
	public int updateUser(User user);
	
	public int deleteGroup(int group_id);
	
	public int quitGroup(String user_id);
	
	public int updateUserGroup(int group_id, String user_id);
	
	public int updateHBTI(int hbti_id, String user_id);
	
	public int deleteUser(String user_id);
	
	public int existingUser(String user_id);
	
	public int existingGroup(String user_id);
	
	public User selectUserByUserId(String user_id);
	
	public List<String> selectTodoListByUserId(String user_id);
	
	public List<String> selectChallengePostListByUserId(String user_id);
	

}