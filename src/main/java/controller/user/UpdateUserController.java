package controller.user;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equals("GET")) {
			
			// GET request: 회원정보 수정 form 요청
			// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행

			UserManager manager = UserManager.getInstance();
			String update_id = UserSessionUtils.getLoginUserId(request.getSession());

			log.debug("UpdateForm Request : {}", update_id);

			User user = manager.findUser(update_id);
			request.setAttribute("user_id", user.getUser_id());
			request.setAttribute("user", user);

			return "/user/UpdateUserForm.jsp"; 
		}
		

		UserManager manager = UserManager.getInstance();
		String tmp_user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		
		User user = manager.findUser(tmp_user_id);
		String image = user.getImage();
		log.debug("image: " , image);
		
		boolean check = ServletFileUpload.isMultipartContent(request);
		

		// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		// 만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
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
							if (filename == null || filename.trim().length() == 0) {
								filename = image;
								continue;
							}
							
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

		User updateUser = new User(user_id, password, name, descr, filename);

		log.debug("Update User : {}", updateUser);

		manager.update(updateUser);
		return "redirect:/user/myPage";
	}
}