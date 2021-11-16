package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Group;
import model.User;
import model.service.exception.DoNotQuitLeaderException;
import model.service.GroupManager;
import model.service.exception.OverTheLimitException;
import model.service.UserManager;

public class UpdateGroupController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GroupManager groupManager = GroupManager.getInstance();
		UserManager userManager = UserManager.getInstance();

		String user_id = UserSessionUtils.getLoginUserId(request.getSession());

		if (request.getServletPath().equals("/group/updateForm")) {
			int group_id = userManager.belongToGroup(user_id);

			// form에 미리 출력할 그룹 정보를 얻어옴.
			Group group = userManager.findGroup(group_id);

			// form에 출력할 그룹원 정보를 얻어옴.
			List<User> userList = groupManager.findUserList(group_id);

			request.setAttribute("groupInfo", group);
			request.setAttribute("userList", userList);

			return "/group/updateForm.jsp";
		}
		if (request.getServletPath().equals("/group/manageUser")) {
			// 그룹 강퇴 요청
			String quit_id = request.getParameter("user_id");
			int group_id = Integer.parseInt(request.getParameter("group_id"));

			try {
				groupManager.quitMember(group_id, quit_id, user_id);
				return "redirect:/group/updateForm";
			} catch (DoNotQuitLeaderException e) {
				request.setAttribute("quitFailed", true);
				request.setAttribute("Exception", e);

				Group group = userManager.findGroup(group_id);
				List<User> userList = groupManager.findUserList(group.getGroup_id());

				request.setAttribute("userList", userList);
				request.setAttribute("groupInfo", group);

				return "/group/updateForm.jsp";
			}

		}
		if(request.getServletPath().equals("/group/update")) {
			Group group = new Group(request.getParameter("name"), request.getParameter("descr"),
					request.getParameter("icon"), user_id, Integer.parseInt(request.getParameter("limit")),
					userManager.findHBTI(user_id));
			group.setGroup_id(Integer.parseInt(request.getParameter("group_id")));

			try {
				userManager.updateGroup(group);
				return "redirect:/group/main";
			} catch (OverTheLimitException e) {
				request.setAttribute("updateFailed", true);
				request.setAttribute("Exception", e);

				List<User> userList = groupManager.findUserList(group.getGroup_id());

				request.setAttribute("userList", userList);
				request.setAttribute("groupInfo", group);

				return "/group/updateForm.jsp";
			}
		}
		return null;
	}

}
