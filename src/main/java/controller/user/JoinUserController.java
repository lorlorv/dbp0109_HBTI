package controller.user;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.exception.ExistingUserException;
import model.service.UserManager;

public class JoinUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(JoinUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			log.debug("RegisterForm Request");
			return "/user/joinForm.jsp";
		}

		// POST request
		boolean check = ServletFileUpload.isMultipartContent(request);
	
		String user_id = null;
		String password = null;
		String name = null;
		String descr = null;

		String filename = null;

		if (check) {

			
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);

			

			if (!dir.exists())
				dir.mkdir();
		

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(10 * 1024);
				
			
				factory.setRepository(dir);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
			
				upload.setSizeMax(10 * 1024 * 1024);
				
				upload.setHeaderEncoding("utf-8");
				
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

				
				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);
				

					String value = item.getString("utf-8");
				

					if (item.isFormField()) {

					
						if (item.getFieldName().equals("user_id"))
							user_id = value;
						
						else if (item.getFieldName().equals("password"))
							password = value;
					
						else if (item.getFieldName().equals("name"))
							name = value;
						else if (item.getFieldName().equals("descr"))
							descr = value;

					} else {
						if (item.getFieldName().equals("image")) {
						
							filename = item.getName();
							if (filename == null || filename.trim().length() == 0)
								continue;
						
							filename = filename.substring(filename.lastIndexOf("\\") + 1);
							
							
							File file = new File(dir, filename);
							item.write(file);
							
						}
					}
				}

			} catch (SizeLimitExceededException e) {
				
				e.printStackTrace();
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
		User user = new User(user_id, password, name, descr, filename);
		
		log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			manager.updateLoginDate(user.getUser_id());

			HttpSession session = request.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);

			return "redirect:/user/hbtiTest";

		} catch (ExistingUserException e) { 
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/joinForm.jsp";
		}

	}
}
