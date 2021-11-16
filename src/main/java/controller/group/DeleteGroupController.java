package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.GroupManager;
import model.service.UserManager;

public class DeleteGroupController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		
		System.out.println(group_id);
		
		groupManager.deleteAllPost(group_id);
		userManager.deleteGroup(group_id);
		
		return "redirect:/group/main";	
	}

}
