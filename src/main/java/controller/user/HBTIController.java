package controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;

public class HBTIController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(HBTIController.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			// GET request: hbti test form 요청
			log.debug("hbtiTestForm Request");

			return "/user/hbtiTestForm.jsp";
		}
		
		if(request.getServletPath().equals("/user/hbtiTest")) {
			return "/user/hbtiTestForm.jsp";
		}

		// POST request
		//검사 결과 파라미터로 받아오기
		String[] testRst = new String[12];
		for (int i = 0; i < 12; i++) {
			testRst[i] = request.getParameter("a" + (i + 1));
		}

		// Join에서 넘겨준 user_id값 가져오기 || Login에서 넘겨준 user_id값 가져오기
		UserManager manager = UserManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		int oldHbti = manager.findHBTI(user_id); //update전 hbti 저장
		
		// hbti가 존재한다면 그룹 정보도 바꾸어 주어야 한다.
		if(oldHbti != 0) {
			manager.updateHBTI(user_id, testRst); // type결정 -> hbti_id로 변경 -> updateHBTI()
			
			int group_id = manager.belongToGroup(user_id);
			//그룹 판별 후 그룹 있으면 +  leader이면 변경 후 탈퇴 아니면 그냥 탈퇴, 그룹 없으면 그냥 탈퇴  -> manager에게 역할 위임
			if(group_id != 0) {
				manager.updateHBTIGroup(user_id, oldHbti, group_id);
			}
		} else {
			manager.updateHBTI(user_id, testRst); // type결정 -> hbti_id로 변경 -> updateHBTI()
		}
		
		
		// 세션에 사용자 이이디 저장
		HttpSession session = request.getSession();
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user_id);
		
		return "redirect:/main";

	}
}
