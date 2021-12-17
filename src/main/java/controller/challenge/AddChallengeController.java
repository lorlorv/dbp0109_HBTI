package controller.challenge;

import java.util.List;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ChallengePost;
import model.User;
import model.service.exception.ExistingChallengePostException;
import model.service.GroupManager;
import model.service.UserManager;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
import org.apache.commons.fileupload.servlet.*;

public class AddChallengeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();
		
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		ChallengePost post;
		
		if(request.getServletPath().equals("/challenge/addForm")) {
			try {
				groupManager.isPost(user_id);
				return "/challenge/addForm.jsp";
				
			} catch (ExistingChallengePostException e) {
				request.setAttribute("addFailed", true);
				request.setAttribute("Exception", e);
				
				post = groupManager.findPost(user_id);
				request.setAttribute("postInfo", post);
				
				// post 작성자 여부
				request.setAttribute("isWriter", true);
				
				return "/challenge/view.jsp";
			}
		}
		String content = null;
		String fileName = null;
		
		boolean check = ServletFileUpload.isMultipartContent(request);
		if(check) {
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			
			if(!dir.exists()) dir.mkdir();

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
	          
	            factory.setSizeThreshold(10 * 1024);
	            factory.setRepository(dir);  
	            
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            
	            upload.setSizeMax(10 * 1024 * 1024);
	            upload.setHeaderEncoding("utf-8");
	          
	            List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
	            
	            for(int i = 0; i < items.size(); ++i) {
	            	FileItem item = (FileItem)items.get(i);
	            	String value = item.getString("utf-8");
	          	
	            	if(item.isFormField()) {              		
	            		if(item.getFieldName().equals("content")) content = value;
	            		
	            	}
	            	else {
	            		if(item.getFieldName().equals("image")) {
	            		
	            			fileName = item.getName();
	            			if(fileName == null || fileName.trim().length() == 0) continue;
	            		
	            			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	            			
	            			File file = new File(dir, fileName);
	            			item.write(file);
	            		
	            		}
	            	}
	            }
			} catch(SizeLimitExceededException e) {
				e.printStackTrace();           
            }catch(FileUploadException e) {
                    e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
			try {
				User user = userManager.findUser(user_id);
		
				int group_id = userManager.belongToGroup(user_id);
				
				post = new ChallengePost(
						user.getName(),
						user_id,
						content,
						fileName
						);
				post.setGroup_id(group_id);
				groupManager.addPost(post);
				
				return "redirect:/challenge/myView";
			} catch (Exception e) { e.printStackTrace(); }
		}	
		return null;
	}
}
