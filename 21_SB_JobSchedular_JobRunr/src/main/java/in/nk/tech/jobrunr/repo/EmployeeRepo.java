package in.nk.tech.jobrunr.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.nk.tech.jobrunr.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, String>{

}
