package in.nk.tech.jobrunr.restcontroller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.jobrunr.service.SampleJobService;

@RestController
@RequestMapping("/job")
public class SampleJobRestController {
	
	@Autowired
	private JobScheduler jobScheduler;
	
	@Autowired
	private SampleJobService sampleJobService;

	@GetMapping("/enqueu/{input}")
	public ResponseEntity<String> enqueueJOb(@PathVariable String input){
		JobId enqueue = jobScheduler.enqueue(() -> sampleJobService.doJob(input));
		
		JobId schedule = jobScheduler.schedule(Instant.now().plus(5, ChronoUnit.MINUTES), () -> sampleJobService.doJob(input));
		
		String scheduleRecurrently = jobScheduler.scheduleRecurrently(Cron.minutely(), () -> sampleJobService.doJob(input));
		
		BackgroundJob.enqueue(() -> sampleJobService.doBackGroundJob(input));
	
		return ResponseEntity.ok("Job scheduled recurrently With id: "+scheduleRecurrently);
		
	}
}
