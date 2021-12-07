package scheduler;

import java.sql.SQLException;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.service.GroupManager;

// 동기화 처리를 위해 어노테이션 추가 : 해당 스케줄러가 끝날때 까지 다음 요청 대기
@DisallowConcurrentExecution 
public class ChallengeScheduler implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 동작 시킬 내용 정의
		System.out.println("test : " + new Date(System.currentTimeMillis()));
		
		GroupManager groupManager = GroupManager.getInstance();
		try {
			int rlt = groupManager.assignChallenge();
			if (rlt == 0) {
				throw new JobExecutionException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new JobExecutionException();
		}
	}

}