package controller.challenge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.GroupManager;

public class DeleteChallengeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		GroupManager groupManager = GroupManager.getInstance();
		groupManager.deletePost(post_id);
		
		return "redirect:/group/main";
	}
}
