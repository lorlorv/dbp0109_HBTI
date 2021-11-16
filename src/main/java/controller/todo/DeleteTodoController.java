package controller.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.service.TodoManager;
import controller.Controller;

public class DeleteTodoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteTodoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int deleteId = Integer.parseInt(request.getParameter("todo_id"));
    	log.debug("Delete User : {}", deleteId);
    	
		TodoManager manager = TodoManager.getInstance();
		manager.delete(deleteId);
		
		return "redirect:/todo/modifyForm";
		
	}

	
}
