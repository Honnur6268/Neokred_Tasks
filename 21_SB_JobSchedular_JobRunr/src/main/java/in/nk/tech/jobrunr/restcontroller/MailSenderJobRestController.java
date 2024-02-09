package in.nk.tech.jobrunr.restcontroller;

import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.jobrunr.service.EmployeeService;

@RestController
@RequestMapping("/mail")
public class MailSenderJobRestController {

	@Autowired
	private JobScheduler jobScheduler;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<String> fetchDataAndSendMail() {
		jobScheduler.scheduleRecurrently(Cron.everyHalfHour(), () -> employeeService.fetchEmployees(JobContext.Null));

		return null;
	}
}
