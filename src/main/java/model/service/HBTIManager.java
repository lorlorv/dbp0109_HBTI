package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HBTI;

import model.dao.mybatis.HbtiDAO;

public class HBTIManager {
	private static HBTIManager hbtiMan = new HBTIManager();

	private HbtiDAO hbtiDAO;
	
	private HBTIManager() {
		try {
			hbtiDAO = new HbtiDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HBTIManager getInstance() {
		return hbtiMan;
	}
	
	public HBTI findHBTI(int hbti_id) throws SQLException {
		HBTI hbti = hbtiDAO.findHBTI(hbti_id);
		return hbti;
	}
	
	public String findHbtiImg(String name) throws SQLException {
		return hbtiDAO.findHbtiImg(name);
	}
	
	/* Ranking */
	/* hbti_id에 해당하는 그룹의 총 인원 수 */
	public int numOfGroupMem(int hbti_id) {
		// 일단 hbti_id인 group이 뭐가 있는지 group_id 찾아오기
		List<Integer> groupList = new ArrayList<>();
		groupList = hbtiDAO.group_idByHBTI(hbti_id);// groupDAO로 바꾸기

		// groupList의 List하나 씩 돌려보며 그 group의 인원 수 얻어와서 총 인원 수 구하기
		int sum = 0;
		for (int i = 0; i < groupList.size(); i++) {
			int group_id = groupList.get(i);
			sum += hbtiDAO.getNumberOfUsersInGroup(group_id);// groupDAO로 바꾸기
		}

		return sum;
	}

	public int numOfUserWhoDidChallengeInGroup(int hbti_id) {
		// 일단 hbti_id인 group이 뭐가 있는지 group_id 찾아오기
		List<Integer> groupList = new ArrayList<>();
		groupList = hbtiDAO.group_idByHBTI(hbti_id);

		// groupList의 List하나 씩 돌려보며 그 group의 User_id 가져오기 List로
		List<String> userListEachGroup = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < groupList.size(); i++) {
			int group_id = groupList.get(i);

			userListEachGroup = hbtiDAO.userListEachGroup(group_id); // 그룹에 있는 user리스트 불러오기
			
			List<String> userList = new ArrayList<>();
			for (int j = 0; j < userListEachGroup.size(); j++) {
				userList.add(userListEachGroup.get(j));
			}
			
			cnt += hbtiDAO.todayChallegeUserNum(userList);
		}
		return cnt;
	}

	// 퍼센트 구하기
	public double percentOfChallenge(int hbti_id) {
		double A = (double) numOfUserWhoDidChallengeInGroup(hbti_id);
		double B = (double) numOfGroupMem(hbti_id);
		double percentage = 0;
		if (A == 0 || B == 0) {
			percentage = 0;
		} else
			percentage = A / B * 100.0;

		return percentage;
	}

}