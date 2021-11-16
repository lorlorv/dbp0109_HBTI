package controller.challenge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ChallengePost;
import model.service.GroupManager;

public class ViewChallengeController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GroupManager groupManager = GroupManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		ChallengePost post = groupManager.findPost(user_id);
		request.setAttribute("postInfo", post);
		
		return "/challenge/view.jsp";
	}

}
