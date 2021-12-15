package model.service;

import java.sql.SQLException;

import model.dao.mybatis.UserDAO;

public class UserHBTIMatching {
	private UserDAO userDAO;
	
	public UserHBTIMatching() {}
	
	public UserHBTIMatching(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	
	public int matchingHBTIResult(String user_id, String[] testRst) throws SQLException {
		String hbtiType = new String();
		int hbti_id = 0;
		int A = 0;
		for (int i = 0; i < 3; i++) {
			if (testRst[i].equals("A")) {
				A++;
			}
		}
		if (A > 1)
			hbtiType += "E";
		else
			hbtiType += "I";

		A = 0;
		for (int i = 3; i < 6; i++) {
			if (testRst[i].equals("A")) {
				A++;
			}
		}
		if (A > 1)
			hbtiType += "S";
		else
			hbtiType += "N";

		A = 0;
		for (int i = 6; i < 9; i++) {
			if (testRst[i].equals("A")) {
				A++;
			}
		}
		if (A > 1)
			hbtiType += "T";
		else
			hbtiType += "F";

		A = 0;
		for (int i = 9; i < testRst.length; i++) {
			if (testRst[i].equals("A")) {
				A++;
			}
		}
		if (A > 1)
			hbtiType += "J";
		else
			hbtiType += "P";

		switch (hbtiType) {
	      case "ENFJ":
	         hbti_id = 1;
	         break;
	      case "ESTJ":
	         hbti_id = 2;
	         break;
	      case "ESFJ":
	         hbti_id = 3;
	         break;
	      case "ENTJ":
	         hbti_id = 4;
	         break;
	      case "ESTP":
	         hbti_id = 5;
	         break;
	      case "ESFP":
	         hbti_id = 6;
	         break;
	      case "ENFP":
	         hbti_id = 7;
	         break;
	      case "ISFJ":
	         hbti_id = 8;
	         break;
	      case "ENTP":
	         hbti_id = 9;
	         break;
	      case "INFJ":
	         hbti_id = 10;
	         break;
	      case "INTJ":
	         hbti_id = 11;
	         break;
	      case "ISTP":
	         hbti_id = 12;
	         break;
	      case "ISFP":
	         hbti_id = 13;
	         break;
	      case "INFP":
	         hbti_id = 14;
	         break;
	      case "INTP":
	         hbti_id = 15;
	         break;
	      case "ISTJ":
	         hbti_id = 16;
	         break;
	      }
		
		userDAO.updateHBTI(user_id, hbti_id); //hbti �ٲ�
		
		return hbti_id;
	}
}
