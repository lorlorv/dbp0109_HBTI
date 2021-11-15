package model;

public class HBTI {
	private String quesition;
	private String answerA;
	
	public HBTI(String quesition, String answerA) {
		super();
		this.quesition = quesition;
		this.answerA = answerA;
	}
	public HBTI() {
		// TODO Auto-generated constructor stub
	}
	public String getQuesition() {
		return quesition;
	}
	public void setQuesition(String quesition) {
		this.quesition = quesition;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	
	

}
