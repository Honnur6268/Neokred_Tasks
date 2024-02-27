package in.nk.tech.validation.service;

import java.util.List;

import in.nk.tech.validation.dto.EmployeeRequest;
import in.nk.tech.validation.entity.Employee;
import in.nk.tech.validation.exception.UserNotFoundException;

public interface EmployeeService {
	Employee saveEmployee(EmployeeRequest request);

	List<Employee> fetchAllEmployees();

	Employee fetchEmployeeById(int empId) throws UserNotFoundException ;
}
