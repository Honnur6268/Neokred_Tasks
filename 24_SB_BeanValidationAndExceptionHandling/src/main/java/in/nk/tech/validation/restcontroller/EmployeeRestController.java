package in.nk.tech.validation.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.validation.dto.EmployeeRequest;
import in.nk.tech.validation.entity.Employee;
import in.nk.tech.validation.exception.UserNotFoundException;
import in.nk.tech.validation.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ems")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/on-board")
	public ResponseEntity<Employee> onBoardEmployee(@RequestBody @Valid EmployeeRequest request) {
		Employee employee = employeeService.saveEmployee(request);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/fetch-all")
	public ResponseEntity<List<Employee>> fetchAllEmployees() {
		List<Employee> employees = employeeService.fetchAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/fetch-by/{employeeId}")
	public ResponseEntity<Employee> fetchByEmployeeId(@PathVariable Integer employeeId)throws UserNotFoundException  {
		Employee employee = employeeService.fetchEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}

}
