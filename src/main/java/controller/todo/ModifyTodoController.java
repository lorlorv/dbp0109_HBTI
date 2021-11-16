package controller.todo;

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
		
    	if (request.getServletPath().equals("/todo/modifyForm")) {	
    	
			List<Todo> todoList = manager.findTodoList(user_id);
			request.setAttribute("todoList", todoList);	
				
				return "/todo/modifyForm.jsp";   
			}    else {
				int todo_id = Integer.parseInt(request.getParameter("todo_id"));
				String content = request.getParameter("content");
    	
    	manager.update(todo_id, content);
   
        return "redirect:/todo/modifyForm";	
			}
    	
    	}
    }

