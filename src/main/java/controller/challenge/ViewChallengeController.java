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
		
		if(request.getServletPath().equals("/challenge/myView")) {	
			ChallengePost post = groupManager.findPost(user_id);
			
			request.setAttribute("postInfo", post);
			
			boolean isWriter = true;
			request.setAttribute("isWriter", isWriter);
			
			return "/challenge/view.jsp";
		}
		
		String writer_id = request.getParameter("writer_id");
		
		ChallengePost post = groupManager.findPost(writer_id);
		request.setAttribute("postInfo", post);
		
		boolean isWriter;
		if(user_id.equals(writer_id))
			isWriter = true;
		else 
			isWriter = false;
		
		request.setAttribute("isWriter", isWriter);
		
		return "/challenge/view.jsp";
	}

}
