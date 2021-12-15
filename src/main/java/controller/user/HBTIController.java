package controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;

public class HBTIController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(HBTIController.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			
			log.debug("hbtiTestForm Request");

			return "/user/hbtiTestForm.jsp";
		}
		
		if(request.getServletPath().equals("/user/hbtiTest")) {
			return "/user/hbtiTestForm.jsp";
		}

		String[] testRst = new String[12];
		for (int i = 0; i < 12; i++) {
			testRst[i] = request.getParameter("a" + (i + 1));
		}

		
		UserManager manager = UserManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		int oldHbti = manager.findHBTI(user_id); 
		
		
		if(oldHbti != 0) {
			manager.updateHBTI(user_id, testRst); 
			
			int group_id = manager.belongToGroup(user_id);
			
			if(group_id != 0) {
				manager.updateHBTIGroup(user_id, oldHbti, group_id);
			}
		} else {
			manager.updateHBTI(user_id, testRst); 
		}
    
		HttpSession session = request.getSession();
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);
		
		return "redirect:/main";

	}
}
