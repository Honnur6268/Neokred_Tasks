package in.nk.tech.schedular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.nk.tech.schedular.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
