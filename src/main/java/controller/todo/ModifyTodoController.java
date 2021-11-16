package controller.todo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Todo;
import model.service.TodoManager;
import controller.Controller;
import controller.user.UserSessionUtils;


public class ModifyTodoController implements Controller {
	

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
    	
    	TodoManager manager = TodoManager.getInstance();
		
    	if (request.getMethod().equals("GET")) {	
    	
			List<Todo> todoList = manager.findTodoList(user_id);
			request.setAttribute("todoList", todoList);	
				
				return "/todo/modifyForm.jsp";   
			}    
    	
    	// POST request (회원정보가 parameter로 전송됨)
  
    	List<Todo> todoList = new ArrayList<Todo>();
    	request.setAttribute("todoList", todoList);
   
        return "redirect:/todo/view";	
    	}
    }

