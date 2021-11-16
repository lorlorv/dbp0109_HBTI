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
import model.service.UserManager;
import model.service.exception.WriterMismatchException;

//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class UpdateChallengeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();

		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		ChallengePost post = groupManager.findPost(user_id);

		// updateForm URI 요청
		if (request.getServletPath().equals("/challenge/updateForm")) {
			String writer_id = request.getParameter("writer_id");
			// 로그인한 user_id와 writer_id와 비교하여 같다면 updateForm으로 가게끔
			try {
				if(!user_id.equals(writer_id)) {
					throw new WriterMismatchException("게시물 작성자만 수정이 가능합니다.");
				}
				request.setAttribute("postInfo", post);
				return "/challenge/updateForm.jsp";
			} catch (WriterMismatchException e) {
				// 같지 않다면 Exception 발생
				request.setAttribute("updateFailed", true);
				request.setAttribute("Exception", e);

				request.setAttribute("postInfo", post);
				return "/challenge/view.jsp";
			}
		} else if (request.getServletPath().equals("/challenge/update")) {
			// update URI 요청
			String exist_img = post.getImage();
			// 파일 전송 파라미터를 처리하는 코드
			String content = null;
			String fileName = null;

			// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
			boolean check = ServletFileUpload.isMultipartContent(request);

			if (check) {
				// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨
				ServletContext context = request.getServletContext();
				String path = context.getRealPath("/upload");
				File dir = new File(path);

				if (!dir.exists())
					dir.mkdir();

				try {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
					factory.setSizeThreshold(10 * 1024);
					// 메모리에 한번에 저장할 데이터의 크기를 설정한다.
					// 10kb 씩 메모리에 데이터를 읽어 들인다.
					factory.setRepository(dir);
					// 전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.

					ServletFileUpload upload = new ServletFileUpload(factory);
					// Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
					upload.setSizeMax(10 * 1024 * 1024);
					// 업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
					upload.setHeaderEncoding("utf-8");
					// 업로드 되는 내용의 인코딩을 설정한다.

					List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

					// upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
					for (int i = 0; i < items.size(); ++i) {
						FileItem item = (FileItem) items.get(i);
						// commons-fileupload를 사용하여 전송받으면
						// 모든 parameter는 FileItem 클래스에 하나씩 저장된다.

						String value = item.getString("utf-8");
						// 넘어온 값에 대한 한글 처리를 한다.

						if (item.isFormField()) {// 일반 폼 데이터라면...
							if (item.getFieldName().equals("content"))
								content = value;
							// key 값이 content이면 content 변수에 값을 저장한다.
						} else {// 파일이라면...
							if (item.getFieldName().equals("image")) {
								// key 값이 picture이면 파일 저장을 한다.
								fileName = item.getName();// 파일 이름 획득 (자동 한글 처리 됨)
								if (fileName == null || fileName.trim().length() == 0) {
									fileName = exist_img;
									continue;
								}
								// 파일이 전송되어 오지 않았다면 건너 뛴다.
								fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
								// 파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
								// 실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
								File file = new File(dir, fileName);
								item.write(file);
								// 파일을 upload 경로에 실제로 저장한다.
								// FileItem 객체를 통해 바로 출력 저장할 수 있다.
							}
						}
					}
				} catch (SizeLimitExceededException e) {
					// 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
					e.printStackTrace();
				} catch (FileUploadException e) {
					// 파일 업로드와 관련되어 발생할 수 있는 예외 처리
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// 오늘 날짜의 user_id의 post를 가져옴
					post = groupManager.findPost(user_id);
					// 가져온 post DTO에 정보를 변경하여 다시 보냄
					post.setContent(content);
					post.setImage(fileName);

					groupManager.updatePost(post);

					return "redirect:/challenge/view";
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else if(request.getServletPath().equals("/challenge/updateLike_btn")) {
			//좋아요 개수 업데이트
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			
			groupManager.updatePostLike(post_id);
			
			return "redirect:/challenge/view";
		}

		return null;
	}

}
