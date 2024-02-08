package in.nk.tech.schedular.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Employee")
public class Employee {

	@Id
	private String id;
	private String employeeName;
	private String employeeAddress;
	private String employeeContactNumber;
	private String employeeEmail;
}
