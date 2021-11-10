package controller.group;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Group;
import model.service.UserManager;

public class ListGroupController implements Controller{

	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	
    	// 로그인 여부 확인 추가
    	UserManager manager = UserManager.getInstance();
		List<Group> groupList = manager.findGroupList(1, 1);
		
		request.setAttribute("groupList", groupList);	
		
    	return "group/list.jsp";
    }
}
