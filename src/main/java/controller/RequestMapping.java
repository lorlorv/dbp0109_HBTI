package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.group.*;
import controller.challenge.*;
import controller.todo.*;


public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        
        // 로그인
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        
        // 회원가입
        mappings.put("/user/join", new JoinUserController());
        mappings.put("/user/hbtiTest", new HBTIController());
        mappings.put("/user/hbtiTestResult", new HBTIController());
        
        // 메인 페이지
        mappings.put("/main", new MainController());
        
        // 마이페이지 (회원 정보 수정 & 회원 탈퇴 & 그룹 탈퇴)
        mappings.put("/user/myPage", new MyPageController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/quit", new QuitUserController());
        mappings.put("/group/quit", new QuitGroupController());
        
        // 로그 아웃
        mappings.put("/user/logout", new LogoutController());
        
        // 투두 조회
         mappings.put("/todo/view", new ViewTodoController());
         mappings.put("/todo/date", new ViewTodoController());
         
        // 투두 수정
        mappings.put("/todo/modifyForm", new ModifyTodoController());
        mappings.put("/todo/modify", new ModifyTodoController()); //변경내용 redirection
        
        mappings.put("/todo/doCheck", new CheckTodoController());
        mappings.put("/todo/doNotCheck", new CheckTodoController());
        //투두 추가
        mappings.put("/todo/addForm", new ForwardController("/todo/addForm.jsp"));
        mappings.put("/todo/add", new AddTodoController());
       
        //투두 삭제
        mappings.put("/todo/delete", new DeleteTodoController());
        
        // 그룹 메인 (검색 & 가입)
        mappings.put("/group/main", new ViewGroupController());
        mappings.put("/group/search", new SearchGroupController());
        mappings.put("/group/join", new JoinGroupController());
        
        // 그룹 생성
        mappings.put("/group/createForm", new ForwardController("/group/createForm.jsp"));
        mappings.put("/group/create", new CreateGroupController());
        
        // 그룹 정보 수정 & 그룹원 관리
        mappings.put("/group/updateForm", new UpdateGroupController());
        mappings.put("/group/update", new UpdateGroupController());
        mappings.put("/group/manageUser", new UpdateGroupController());
        
        // 그룹 삭제
        mappings.put("/group/delete", new DeleteGroupController());
        
        // 챌린지 조회
        mappings.put("/challenge/view", new ViewChallengeController());
        mappings.put("/challenge/myView", new ViewChallengeController());
        
        // 챌린지 추가
        mappings.put("/challenge/addForm", new AddChallengeController());
        mappings.put("/challenge/add", new AddChallengeController());
        
        // 챌린지 수정
        mappings.put("/challenge/updateForm", new UpdateChallengeController());
        mappings.put("/challenge/update", new UpdateChallengeController());
        mappings.put("/challenge/updateLike_btn", new UpdateChallengeController());
        
        // 챌린지 삭제
        mappings.put("/challenge/delete", new DeleteChallengeController());
       
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
