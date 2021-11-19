package model.service.exception;

/*
 * 그룹 가입을 신청했을 때, 인원이 만원이라면
 * 가입하지 못한다는 예외 발생
 */
public class OverTheLimitException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OverTheLimitException() {
		super();
	}

	public OverTheLimitException(String arg0) {
		super(arg0);
	}
}
