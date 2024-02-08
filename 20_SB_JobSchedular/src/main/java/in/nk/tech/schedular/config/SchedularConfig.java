package in.nk.tech.schedular.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.nk.tech.schedular.model.Employee;
import in.nk.tech.schedular.service.EmployeeService;
import in.nk.tech.schedular.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableScheduling
public class SchedularConfig {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmailUtils emailUtils;

	@Scheduled()

//	// @Async 
//	// @Scheduled(fixedDelay = 4000, initialDelay = 3000)
//	@Scheduled(fixedDelay = 4000)
//	// @Scheduled(fixedRateString = "PT03S", initialDelayString = "PT05S")
//	public void runTask() throws InterruptedException {
//		log.info("Executing Task 01 - {}", new Date());
//		Thread.sleep(1000);
//	}

	// @Async
	@Scheduled(fixedRate = 4000)
	public void runJob() throws InterruptedException {
		log.info("Executing Task 02 - {}", new Date());
		Thread.sleep(1000);
	}

	/*
	 * At every minute from 55 through 57 past hour 17 on every day-of-month from 6
	 * through 7 and on every day-of-week from Tuesday through Wednesday in every
	 * month from February through March
	 */
	@Scheduled(cron = "*/5 55-57 17 6-7 Feb-MAR Tue-wed")
	public void doTask() {
		log.info("Executing Task 03 - {}", new Date());
	}

//	@Scheduled(cron = "*/5 17-20 10 7 Feb *")
	@Scheduled(cron = "${cron.schedule}")
	public void fetchEmployees() {
		List<Employee> employees = employeeService.fetchEmployees();
		for (Employee e : employees) {
			String toEmail = e.getEmployeeEmail();
			boolean sendEmail = emailUtils.sendEmail(toEmail);
			if (sendEmail) {
				log.info("Date Fetched and Sent Mail - {} on Date {}", e.getEmployeeEmail(), new Date());
			} else {
				log.info("Date Fetched and Unable to Sent Mail - {} on Date {}", e.getEmployeeEmail(), new Date());
			}
		}

	}

}
