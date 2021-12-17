package scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public void startScheduler() {
		//Quartz 라이브러리
				SchedulerFactory schedulerFactory = new StdSchedulerFactory();
				try {
		            Scheduler scheduler = schedulerFactory.getScheduler();

		            //job 등록
		            JobDetail job = newJob(ChallengeScheduler.class)
		            		.withIdentity("jobName", Scheduler.DEFAULT_GROUP)
		            		.build();

		            // 자정 -> 0 0 0 * * ?
		            Trigger trigger = newTrigger()
		            		.withIdentity("trggerName", Scheduler.DEFAULT_GROUP)
		                    .withSchedule(cronSchedule("0 0 0 * * ?"))
		                    .build();

		            scheduler.scheduleJob(job, trigger);
		            scheduler.start();
		            
		        } catch (Exception e) {
		        	 logger.debug(e.toString());
		        }
	}
	

}
