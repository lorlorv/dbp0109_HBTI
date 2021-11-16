package controller.challenge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ChallengePost;
import model.service.GroupManager;

public class ViewChallengeController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GroupManager groupManager = GroupManager.getInstance();
		String user_id = "jeongmin";
		
		ChallengePost post = groupManager.findPost(user_id);
		request.setAttribute("postInfo", post);
		
		return "/challenge/view.jsp";
	}

}
