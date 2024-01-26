package in.nk.tech.aop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nk.tech.aop.annotations.TrackExecutionTime;
import in.nk.tech.aop.entity.Employee;
import in.nk.tech.aop.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

//	public String saveEmployee() {
//		System.out.println("Employee saved.....");
//		
//		if("employee".equals("employee")) {
//			throw new NullPointerException("Exception Occured");
//		}
//		
//		return "employee";
//	}

	@Transactional
	public Employee saveEmployee(Employee e) {
		e.getEmployeeAddress().length();
		Employee employee = employeeRepo.save(e);

		System.out.println(employee);

		return employee;
	}

	@TrackExecutionTime
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();

		return employees;
	}

	public Employee getEmpById(String id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		return null;

	}
}
