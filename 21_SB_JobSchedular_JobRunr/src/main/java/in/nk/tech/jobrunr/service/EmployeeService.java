package in.nk.tech.jobrunr.service;

import org.jobrunr.jobs.context.JobContext;

import in.nk.tech.jobrunr.model.Employee;

public interface EmployeeService {

	public void fetchEmployees(JobContext context);

	public Employee saveEmployee(Employee employee);
}
