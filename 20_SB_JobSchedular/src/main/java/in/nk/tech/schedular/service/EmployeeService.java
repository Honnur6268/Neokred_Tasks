package in.nk.tech.schedular.service;

import java.util.List;

import in.nk.tech.schedular.model.Employee;

public interface EmployeeService {

	public List<Employee> fetchEmployees();

	public Employee saveEmployee(Employee employee);
}
