package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.GroupManager;
import model.service.UserManager;

public class DeleteGroupController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		
		List<User> userList = groupManager.findUserList(group_id);
		userManager.deleteGroup(group_id, userList);
		
		return "redirect:/group/main";
	}

}
