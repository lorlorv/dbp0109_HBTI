package controller.challenge;

import java.util.List;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ChallengePost;
import model.User;
import model.service.ExistingChallengePostException;
import model.service.GroupManager;
import model.service.UserManager;

//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class AddChallengeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		UserManager userManager = UserManager.getInstance();
		GroupManager groupManager = GroupManager.getInstance();
		String user_id = "jeongmin";
		
		ChallengePost post;
		
		if(request.getServletPath().equals("/challenge/addForm")) {
			try {
				// 오늘 날짜의 ChallengePost가 있는지 확인
				groupManager.isPost(user_id);
				return "/challenge/addForm.jsp";
				
			} catch (ExistingChallengePostException e) {
				request.setAttribute("addFailed", true);
				request.setAttribute("Exception", e);
				
				System.out.println(e.getMessage());
				post = groupManager.findPost(user_id);
				request.setAttribute("postInfo", post);
				
				return "/challenge/view.jsp";
			}
		}
		// 파일 전송 파라미터를 처리하는 코드
		String content = null;
		String fileName = null;
		
		//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		boolean check = ServletFileUpload.isMultipartContent(request);
		
		if(check) {
			// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨 
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			
			if(!dir.exists()) dir.mkdir();
		
		
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
	            //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
	            factory.setSizeThreshold(10 * 1024);
	            //메모리에 한번에 저장할 데이터의 크기를 설정한다.
	            //10kb 씩 메모리에 데이터를 읽어 들인다.
	            factory.setRepository(dir);
	            //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.        
	            
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
	            upload.setSizeMax(10 * 1024 * 1024);
	            //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
	            upload.setHeaderEncoding("utf-8");
	            //업로드 되는 내용의 인코딩을 설정한다.
	            
	            List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
	            
	            //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
	            for(int i = 0; i < items.size(); ++i) {
	            	FileItem item = (FileItem)items.get(i);
	            	//commons-fileupload를 사용하여 전송받으면 
	            	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
	            	
	            	String value = item.getString("utf-8");
	            	//넘어온 값에 대한 한글 처리를 한다.
	            	
	            	if(item.isFormField()) {//일반 폼 데이터라면...                		
	            		if(item.getFieldName().equals("content")) content = value;
	            		//key 값이 content이면 content 변수에 값을 저장한다.
	            	}
	            	else {//파일이라면...
	            		if(item.getFieldName().equals("image")) {
	            		//key 값이 picture이면 파일 저장을 한다.
	            			fileName = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
	            			if(fileName == null || fileName.trim().length() == 0) continue;
	            			//파일이 전송되어 오지 않았다면 건너 뛴다.
	            			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	            			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
	            			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
	            			File file = new File(dir, fileName);
	            			item.write(file);
	            			//파일을 upload 경로에 실제로 저장한다.
	            			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
	            		}
	            	}
	            }
			} catch(SizeLimitExceededException e) {
			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
			try {
				User user = userManager.findUser(user_id);
				// 현재 로그인한 user_Id가 그룹 아이디를 가져옴.
				int group_id = userManager.belongToGroup(user_id);
				
				post = new ChallengePost(
						user.getName(),
						user_id,
						content,
						fileName,
						0
						);
				groupManager.addPost(post, group_id);
				
				request.setAttribute("postInfo", post);
				
				return "/challenge/view.jsp";
			} catch (Exception e) { e.printStackTrace(); }
		
		}
		
		return null;
	}

}
