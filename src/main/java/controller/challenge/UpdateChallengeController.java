package controller.challenge;

import java.util.List;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ChallengePost;
import model.service.GroupManager;
import model.service.exception.WriterMismatchException;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
import org.apache.commons.fileupload.servlet.*;

public class UpdateChallengeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		GroupManager groupManager = GroupManager.getInstance();

		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		ChallengePost post = groupManager.findPost(user_id);

		
		if (request.getServletPath().equals("/challenge/updateForm")) {
			String writer_id = request.getParameter("writer_id");
			
			
			try {
				if(!user_id.equals(writer_id)) {
					 post = groupManager.findPost(writer_id);
					throw new WriterMismatchException("�Խù� �ۼ��ڸ� ������ �����մϴ�.");
				}
				request.setAttribute("postInfo", post);
				return "/challenge/updateForm.jsp";
			} catch (WriterMismatchException e) {
			
				request.setAttribute("updateFailed", true);
				request.setAttribute("Exception", e);

				request.setAttribute("postInfo", post);
				return "/challenge/view.jsp";
			}
		} else if (request.getServletPath().equals("/challenge/update")) {
		
			String exist_img = post.getImage();
			
			String content = null;
			String fileName = null;

			
			boolean check = ServletFileUpload.isMultipartContent(request);

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
							if (item.getFieldName().equals("content"))
								content = value;
							
						} else {
							if (item.getFieldName().equals("image")) {
								
								fileName = item.getName();
								if (fileName == null || fileName.trim().length() == 0) {
									fileName = exist_img;
									continue;
								}
							
								fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
								
								File file = new File(dir, fileName);
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
				try {
					
					post = groupManager.findPost(user_id);
				
					post.setContent(content);
					post.setImage(fileName);

					groupManager.updatePost(post);

					return "redirect:/challenge/myView";
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else if(request.getServletPath().equals("/challenge/updateLike_btn")) {
			
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			String writer_id = request.getParameter("writer_id");
			
			groupManager.updatePostLike(post_id);
			
			ChallengePost like_post = groupManager.findPost(writer_id);
			request.setAttribute("postInfo", like_post);
			
			return "/challenge/view.jsp";
		}

		return null;
	}

}
