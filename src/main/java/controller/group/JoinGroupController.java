package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.UserManager;
import model.Group;
import model.service.exception.OverTheLimitException;

import controller.Controller;
import controller.user.UserSessionUtils;

public class JoinGroupController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String group_id = request.getParameter("group_id");
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		UserManager userManager = UserManager.getInstance();
		
		try {
			userManager.joinGroup(Integer.parseInt(group_id), user_id);
			return "redirect:/group/main";
		} catch(OverTheLimitException e) {
		
			request.setAttribute("joinFailed", true);
			request.setAttribute("exception", e);
			
			int user_HBTI = userManager.findHBTI(user_id);
			List<Group> groupList = userManager.findGroupList(user_HBTI);
			
			request.setAttribute("groupList", groupList);
			
			return "/group/list.jsp";
		}
	}

}
