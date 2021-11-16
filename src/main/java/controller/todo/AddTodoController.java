package controller.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Todo;
import model.service.TodoManager;


public class AddTodoController implements Controller {
	 
	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
	    	TodoManager manager = TodoManager.getInstance();
	    	String user_id =  UserSessionUtils.getLoginUserId(request.getSession());
	    	
	    	Todo todo = new Todo(request.getParameter("content"), user_id);
	    	manager.addTodo(todo);
	    	
	    	return "redirect:/todo/modifyForm";
	    	
	    }
	}
