package in.nk.tech.jobrunr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.jobrunr.model.Employee;
import in.nk.tech.jobrunr.repo.EmployeeRepo;
import in.nk.tech.jobrunr.service.EmployeeService;
import in.nk.tech.jobrunr.utils.EmailUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(EmployeeServiceImpl.class));
	@Autowired
	private EmployeeRepo employeeRepository;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	@Job(name = "fetchDataAndSendMail")
	public void fetchEmployees(JobContext jobContext) {
		List<Employee> employees = employeeRepository.findAll();
		for (Employee e : employees) {
			String toEmail = e.getEmployeeEmail();
			boolean sendEmail = emailUtils.sendEmail(toEmail);
			if (sendEmail) {
				LOGGER.info("Data Fetched and Sent Mail - {} on Date {}", e.getEmployeeEmail(), new Date());
				//Progress Bar
				JobDashboardProgressBar progressBar = jobContext.progressBar(100);
				for (int i = 1; i <= 4; i++) {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					progressBar.setValue(i * 25);
				}
			} else {
				LOGGER.info("Data Fetched and Unable to Sent Mail - {} on Date {}", e.getEmployeeEmail(), new Date());
			}
		}
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		return emp;
	}
}
