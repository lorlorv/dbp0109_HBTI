package controller.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Todo;
import model.service.TodoManager;


public class ViewTodoController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
    	
		TodoManager manager = TodoManager.getInstance();
		
		if(request.getServletPath().equals("/todo/view")) {
			List<Todo> todoList = manager.findTodoList(user_id);				
		
			request.setAttribute("todoList", todoList);
			// 투두가 하나라도 있다면
			return "/todo/main.jsp";	
		}
		
		String searchDate = request.getParameter("todo_date");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date date = (Date) sdf.parse(searchDate);
		
		System.out.println(date);
		
		List<Todo> todo = manager.findDateTodoList(date, user_id);
		request.setAttribute("todoList", todo);
		
		return "/todo/dateResult.jsp";
    }
}