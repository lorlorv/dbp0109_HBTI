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
				throw new UserHbtiException(user_id +"占쏙옙 HBTI占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십쏙옙占싹댐옙.");
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
			

			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력 */
      
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
