package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.exception.ExistingGroupException;
import model.service.exception.OverTheLimitException;
import model.service.UserManager;

import model.Group;
import model.User;
import controller.Controller;
import controller.user.UserSessionUtils;

public class CreateGroupController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager userManager = UserManager.getInstance();
		
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		Group group = new Group(
				0,
				request.getParameter("name"),
				request.getParameter("descr"),
				request.getParameter("icon"),
				Integer.parseInt(request.getParameter("limit")),
				userManager.findHBTI(user_id)
				);
		
		group.setLeader_id(user_id);
		try {
			userManager.createGroup(group);
			return "redirect:/group/main";
		} catch(OverTheLimitException e) {
			request.setAttribute("updateFailed", true);
			request.setAttribute("Exception", e);
			request.setAttribute("group", group);
			return "/group/createForm.jsp";
		} catch(ExistingGroupException e) {
			request.setAttribute("existingName", true);
			request.setAttribute("Exception", e);
			request.setAttribute("group", group);
			return "/group/createForm.jsp";
		}
	}

}
