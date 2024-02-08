package in.nk.tech.schedular.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.schedular.model.Employee;
import in.nk.tech.schedular.service.EmployeeService;

@RestController
@RequestMapping("/ems")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		if (null != emp) {
			return ResponseEntity.ok(emp);
		}
		return ResponseEntity.badRequest().body(emp);
	}
}
