package in.nk.tech.jobrunr.service;

import java.util.Date;

import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {
	
	private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(SampleJobService.class));

	public void doJob(String input) throws Exception {
//		System.out.println("Hello " + input + ", Welcome to Job");
		Thread.sleep(10000);
		LOGGER.info("Job Scheduled By - {} on {}", input, new Date());
	}
	
	public void doBackGroundJob(String input) throws Exception {
//		System.out.println("Hello " + input + ", Welcome to Job");
		Thread.sleep(10000);
		LOGGER.info("Performing background job shecduled by - {} on {}", input, new Date());
	}
}
