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
		
		String user_id = "jeongmin";
				//UserSessionUtils.getLoginUserId(request.getSession());
		
		// 현재 로그인한 user_Id가 그룹에 속해져 있는지를 확인
		int group_id = userManager.belongToGroup(user_id);
		
		if(group_id != 0) {
			// 그룹이 있다면 메인 페이지를 보여주어야 한다
			
			// 그룹 정보를 출력
			Group group = userManager.findGroup(group_id);
			group.setGroup_id(group_id);
			
			String leader = groupManager.findLeaderName(group_id);
			group.setLeader_name(leader);
			
			// 그룹장이라면 그룹장임을 나타내는 정보를 넘겨줌.
			if(user_id.equals(group.getLeader_id())) {
				request.setAttribute("isLeader", true);
			}
			// 그룹원 정보를 출력
			List<User> userList = groupManager.findUserList(group_id);
			
			// 챌린지 list를 출력
			List<ChallengePost> postList = groupManager.findPostList(group_id);
			
			request.setAttribute("groupInfo", group);
			request.setAttribute("userList", userList);
			request.setAttribute("postList", postList);
			
			return "/group/main.jsp";
			
		} else {
			// 그룹이 없다면 그룹 리스트 페이지를 보여주어야 한다.
			int user_HBTI = userManager.findHBTI(user_id);
			
			List<Group> groupList = userManager.findGroupList(user_HBTI);
			
			request.setAttribute("groupList", groupList);
			
			return "/group/list.jsp";
		}
	}

}
