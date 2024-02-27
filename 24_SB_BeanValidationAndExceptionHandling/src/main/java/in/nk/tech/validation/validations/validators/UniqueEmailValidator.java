package in.nk.tech.validation.validations.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.nk.tech.validation.entity.Employee;
import in.nk.tech.validation.repository.EmployeeRepository;
import in.nk.tech.validation.validations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		List<Employee> employees =  employeeRepository.findByEmail(email);
		if(employees.size() == 0) return true;
		return false;
	}

}
