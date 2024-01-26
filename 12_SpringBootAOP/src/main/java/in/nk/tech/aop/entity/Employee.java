package in.nk.tech.aop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Employee")
public class Employee {

	@Id
	public String id;
	
	public String employeeName;

	@Field
	public String employeeAddress;

	public String employeeContactNumber;

}
