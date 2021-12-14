package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.UserManager;
import model.Group;
import model.User;
import model.ChallengePost;
import model.service.GroupManager;

public class ViewGroupController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();
		
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int group_id = userManager.belongToGroup(user_id);
		
		if(group_id != 0) {
			Group group = userManager.findGroup(group_id);
			group.setGroup_id(group_id);
			
			String leader = groupManager.findLeaderName(group_id);
			group.setLeader_name(leader);
			
			/*
			 * User leader = new User();
			 * leader.setName(groupManager.findLeaderName(group_id));
			 * group.setLeader(leader);
			 */
			
			if(user_id.equals(group.getLeader_id())) {
				request.setAttribute("isLeader", true);
			}
			List<User> userList = groupManager.findUserList(group_id);
			
			// 챌린지 list를 출력
			List<ChallengePost> postList = groupManager.findPostList(group_id);
						
			request.setAttribute("groupInfo", group);
			request.setAttribute("userList", userList);
			request.setAttribute("postList", postList);
			
			return "/group/main.jsp";
			
		} else {
			// �׷��� ���ٸ� �׷� ����Ʈ �������� �����־�� �Ѵ�.
			int user_HBTI = userManager.findHBTI(user_id);
			
			List<Group> groupList = userManager.findGroupList(user_HBTI);
			
			request.setAttribute("groupList", groupList);
			
			return "/group/list.jsp";
		}
	}

}
