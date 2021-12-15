package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.exception.*;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		try {
			
			UserManager manager = UserManager.getInstance();
			manager.login(user_id, password);
			int hbti_id = manager.findHBTI(user_id);
			if(hbti_id == 0) {
				throw new UserHbtiException(user_id +"�� HBTI�� �������� �ʽ��ϴ�.");
			}
			manager.updateLoginDate(user_id);
	
			
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);
            
            return "redirect:/main";			
		} catch (UserNotFoundException e) {
			
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			
            return "/user/loginForm.jsp";			
		}	
		catch (PasswordMismatchException e) {
			
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			
            return "/user/loginForm.jsp";			
		}	
		catch(UserHbtiException e) {
			HttpSession session = request.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);
			return "redirect:/user/hbtiTest";		
		}
    }
}
