package controller.todo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Todo;
import model.service.TodoManager;
import controller.Controller;
import controller.user.UserSessionUtils;

public class DeleteTodoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteTodoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id =  UserSessionUtils.getLoginUserId(request.getSession());
		
		int deleteId = Integer.parseInt(request.getParameter("todo_id"));
    	log.debug("Delete User : {}", deleteId);
    	
		TodoManager manager = TodoManager.getInstance();
		manager.delete(deleteId);
		
		Todo selectTodo = manager.findTodo(deleteId, user_id);
		request.setAttribute("selectTodo", selectTodo);
		
		List<Todo> todoList = manager.findNotSelectTodoList(deleteId, user_id);
		request.setAttribute("todoList", todoList);	
		
		return "/todo/modifyForm.jsp";	
		
	}

	
}