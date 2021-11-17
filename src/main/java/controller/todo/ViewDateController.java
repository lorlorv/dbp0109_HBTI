package controller.todo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Todo;
import model.service.TodoManager;
import controller.Controller;
import controller.user.UserSessionUtils;

public class ViewDateController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	Todo todo = null;
		TodoManager manager = TodoManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		String d = request.getParameter("todo_date");
		
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd"); 
        Date n = Date.parse(d);
		long date = n.getTime();
		java.sql.Date todo_date = new java.sql.Date(date);
		
		List<Todo> todoList = manager.findDateTodoList(todo_date, user_id);				
		request.setAttribute("todoList", todoList);	
		request.setAttribute("todo", todo);
		return "/todo/main.jsp";			
    }
}
