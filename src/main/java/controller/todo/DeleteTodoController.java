package controller.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
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

		TodoManager manager = TodoManager.getInstance();
    	
		 if (request.getServletPath().equals("/todo/delete")) {
			
			int deleteId = Integer.parseInt(request.getParameter("todo_id"));
			manager.delete(deleteId);
			
			Todo selectTodo = manager.findTodo(deleteId, user_id);
			request.setAttribute("selectTodo", selectTodo);
			
			List<Todo> todoList = manager.findNotSelectTodoList(deleteId, user_id);
			request.setAttribute("todoList", todoList);	
	
			return "/todo/modifyForm.jsp";  
		 }
		 
		 else {
		
				int deleteId = Integer.parseInt(request.getParameter("todo_id"));
				manager.delete(deleteId);
			
				String date = request.getParameter("todo_date");
	     		
	     		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	     		Date todo_date = (Date) sdf.parse(date);
	    
	     		
				Todo selectTodo = manager.findTodo(todo_date, deleteId, user_id);
	     		request.setAttribute("selectTodo", selectTodo);
	     		
	 			List<Todo> todoList = manager.findNotSelectTodoList(todo_date, deleteId, user_id);
	 			request.setAttribute("todoList", todoList);	
		
				return "/todo/modifyDateForm.jsp";  
			 }
		 }
			
			
	}
