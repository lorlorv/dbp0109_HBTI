package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;

public class QuitUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager manager = UserManager.getInstance();
		String remove_user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		//회원 삭제
		manager.remove(remove_user_id);
		
		return "/user/loginForm.jsp";
	}

}
