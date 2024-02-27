package in.nk.tech.validation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.validation.dto.EmployeeRequest;
import in.nk.tech.validation.entity.Employee;
import in.nk.tech.validation.exception.UserNotFoundException;
import in.nk.tech.validation.repository.EmployeeRepository;
import in.nk.tech.validation.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee saveEmployee(EmployeeRequest request) {
		Employee employeeDetaila = Employee.build(0, request.getName(), request.getEmail(), request.getMobile(),
				request.getGender(), request.getAge(), request.getNationality(), request.getEmployeeNature());
		return repository.save(employeeDetaila);
	}

	@Override
	public List<Employee> fetchAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee fetchEmployeeById(int empId) throws UserNotFoundException {
		Employee employee = repository.findByEmployeeId(empId);
		if(null != employee) {
			return employee;
		}
		else {
			throw new UserNotFoundException("User not found wuth id: "+empId);
		}
		
	}

}
