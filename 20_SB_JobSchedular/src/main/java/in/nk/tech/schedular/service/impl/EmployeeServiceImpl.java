package in.nk.tech.schedular.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.schedular.model.Employee;
import in.nk.tech.schedular.repository.EmployeeRepository;
import in.nk.tech.schedular.service.EmployeeService;

@Service
//@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> fetchEmployees() {
		List<Employee> employees = employeeRepository.findAll();
//		log.info("Fetched Employees Details - {} on Date {}", employees, new Date());
		return employees;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		return emp;
	}

}
