package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.UserManager;


public class MyPageController implements Controller { 
    private static final Logger log = LoggerFactory.getLogger(MyPageController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	UserManager manager = UserManager.getInstance();
    	
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
    	if(request.getServletPath().equals("/group/main")) {
    		return "redirect:/group/main";
    	}
		/* User Profile */
		User user = manager.findUser(user_id);
		String group_name = manager.findGroupName(user.getGroup_id());
		int groupNum = manager.findGroupCnt(user.getHbti_id());
		String name = manager.findHbtiName(user.getHbti_id());
		
		/* Calendar */
		List<String> is_todo = manager.isTodo(user_id);

		log.debug("MyPage User : {}", user);
		
    	request.setAttribute("user", user);
    	request.setAttribute("group_name", group_name);
    	request.setAttribute("groupNum", groupNum);
    	request.setAttribute("hbti_name", name);
    	request.setAttribute("isTodo", is_todo);
    	
		return "/user/myPage.jsp";
    }
}
