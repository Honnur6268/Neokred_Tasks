package in.nk.tech.validation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nk.tech.validation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByEmployeeId(int empId);

	List<Employee> findByEmail(String email);

}
