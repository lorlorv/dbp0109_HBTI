package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.PasswordMismatchException;
import model.service.UserHbtiException;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		try {
			// 모델에 로그인 처리를 위임
			UserManager manager = UserManager.getInstance();
			manager.login(user_id, password);
			int hbti_id = manager.findHBTI(user_id);
			if(hbti_id == 0) {
				throw new UserHbtiException(user_id +"의 HBTI가 존재하지 않습니다.");
			}
			manager.updateLoginDate(user_id);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);
            
            return "redirect:/group/main";			
		} catch (UserNotFoundException e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			
            return "/user/loginForm.jsp";			
		}	
		catch (PasswordMismatchException e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
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
