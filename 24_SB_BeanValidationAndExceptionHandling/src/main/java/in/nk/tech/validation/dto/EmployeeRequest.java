package in.nk.tech.validation.dto;

import in.nk.tech.validation.validations.UniqueEmail;
import in.nk.tech.validation.validations.ValidateAge;
import in.nk.tech.validation.validations.ValidateEmployeeType;
import in.nk.tech.validation.validations.ValidateLength;
import in.nk.tech.validation.validations.ValidateMobile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeRequest {

//	@NotNull(message = "Name Must Not be null")
	@NotEmpty(message = "Name Must Not be null or empty")
//	@Length(min = 4, message = "Name Must contain at least 4 character")
	// Custom annotation Validation for Employee Name: Length based on min and max
	@ValidateLength(min = 5, max = 100, message = "Length should be greter than or eqaul to 5 and less than 100")
	private String name;

	@Email(message = "Invalid Email Address")
	// Custom annotation Validation for Employee Email: Checks for email uniqueness
	@UniqueEmail(message = "Email is already present. Try other email id!!")
	private String email;

//	@Pattern(regexp = "^\\d{10}$", message = "Inavlid Mobile Number")
	// Custom annotation Validation for Employee mobile: should be 10 digit numeric value
	@ValidateMobile
	private String mobile;

	private String gender;

//	@Min(value = 18, message = "Age must be greater than or equal to 18")
//	@Max(value = 65, message = "Age must be greater than or equal to 18 and less than 65")
	// Custom annotation Validation for Employee age: Checks for Age >= 18 and Age <= 65
	@ValidateAge(min = 18, max = 65, message = "Age must be greater than or equal to 18 and less than or equal to 65")
	private int age;

	@NotBlank(message = "Enter valid Nationality")
	private String nationality;

	// Custom annotation Validation for Employee Nature: Either Permanent or Vendor
	@ValidateEmployeeType
	private String employeeNature;
}
