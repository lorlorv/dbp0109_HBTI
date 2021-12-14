package controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.HBTI;
import model.User;
import model.service.HBTIManager;
import model.service.UserManager;

public class MainController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (request.getServletPath().equals("/main") || request.getMethod().equals("GET")) {

			UserManager manager = UserManager.getInstance();
			HBTIManager hbtiManager = HBTIManager.getInstance();
			String user_id = UserSessionUtils.getLoginUserId(request.getSession());

			/* HBTI Profile */
			User user = manager.findUser(user_id);
			HBTI hbti = hbtiManager.findHBTI(manager.findUser(user_id).getHbti_id());
			String goodName = hbtiManager.findHbtiName(hbti.getGoodHbti());
			String badName = hbtiManager.findHbtiName(hbti.getBadHbti());

			request.setAttribute("user", user);
			request.setAttribute("hbti", hbti);
			request.setAttribute("goodName", goodName);
			request.setAttribute("badName", badName);

			/* Ranking */

			Map<String, Double> percentMap = new HashMap<String, Double>();
			for (int i = 0; i < 16; i++) {
				String hbtiName = manager.findHbtiName(i + 1);
				double percentage = manager.percentOfChallenge(i + 1); // hbti별로 퍼센트 받아오기
				percentMap.put(hbtiName, percentage);
			}

			// Map.Entry 리스트 작성
			List<Entry<String, Double>> list_entries = new ArrayList<Entry<String, Double>>(percentMap.entrySet());

			// 비교함수 Comparator를 사용하여 내림 차순으로 정렬
			Collections.sort(list_entries, new Comparator<Entry<String, Double>>() {
				// compare로 값을 비교
				public int compare(Entry<String, Double> obj1, Entry<String, Double> obj2) {
					// 내림 차순으로 정렬
					return obj2.getValue().compareTo(obj1.getValue());
				}
			});


			Map<String, String> imgMap = new LinkedHashMap<String, String>();
			imgMap.put(list_entries.get(1).getKey(), hbtiManager.findHbtiImg(list_entries.get(1).getKey()));
			imgMap.put(list_entries.get(0).getKey(), hbtiManager.findHbtiImg(list_entries.get(0).getKey()));
			imgMap.put(list_entries.get(2).getKey(), hbtiManager.findHbtiImg(list_entries.get(2).getKey()));

			List<Double> rankValue = new ArrayList<>();
			rankValue.add(list_entries.get(1).getValue());
			rankValue.add(list_entries.get(0).getValue());
			rankValue.add(list_entries.get(2).getValue());

			request.setAttribute("rank", list_entries);
			request.setAttribute("rank_img", imgMap);
			request.setAttribute("rankValue", rankValue);
		}
		return "/main/main.jsp";
	}
}