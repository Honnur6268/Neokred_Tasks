package in.nk.tech.aop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.aop.annotations.TrackExecutionTime;
import in.nk.tech.aop.entity.Employee;
import in.nk.tech.aop.service.EmployeeService;

@RestController
@RequestMapping("/ems")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = employeeService.saveEmployee(employee);

		return ResponseEntity.ok(saveEmployee);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{id}")
	@TrackExecutionTime
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee empById = employeeService.getEmpById(id);

		return ResponseEntity.ok(empById);
	}
}
