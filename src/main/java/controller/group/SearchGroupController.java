package controller.group;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Group;
import model.service.UserManager;

public class SearchGroupController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	
    	// 로그인 여부 확인 추가
    	UserManager userManager = UserManager.getInstance();	
    	
    	String keyword = request.getParameter("searchKeyword");
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
    			//UserSessionUtils.getLoginUserId(request.getSession());
    	
    	// 쿼리를 위해 세팅
    	keyword = keyword + "%";
    	System.out.println(keyword);
    	
    	int hbti_id = userManager.findHBTI(user_id);
    	System.out.println(hbti_id);
    	List<Group> searchList = userManager.searchGroupList(hbti_id, keyword);
    	
    	request.setAttribute("groupList", searchList);
    	return "/group/list.jsp";
    }
}
