package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.OverTheLimitException;
import model.service.UserManager;

import model.Group;

import controller.Controller;

public class CreateGroupController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager userManager = UserManager.getInstance();
		
		String user_id = "jeongmin";
		//UserSessionUtils.getLoginUserId(request.getSession());
		
		Group group = new Group(
				request.getParameter("name"),
				request.getParameter("descr"),
				request.getParameter("icon"),
				user_id,
				Integer.parseInt(request.getParameter("limit")),
				userManager.findHBTI(user_id)
				);

		try {
			userManager.createGroup(group);
			return "redirect:/group/main";
		} catch(OverTheLimitException e) {
			request.setAttribute("updateFailed", true);
			request.setAttribute("Exception", e);
			request.setAttribute("group", group);
			return "/group/createForm.jsp";
		}
	}

}
