package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.UserManager;

public class QuitGroupController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserManager manager = UserManager.getInstance();
		
		String groupQuit_user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int group_id = manager.belongToGroup(groupQuit_user_id);
		
		System.out.println(group_id);
		
		manager.quitGroup(groupQuit_user_id, group_id);
		
		return "redirect:/user/myPage";
	}

}
