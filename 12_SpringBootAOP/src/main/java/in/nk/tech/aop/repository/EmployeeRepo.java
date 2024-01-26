package in.nk.tech.aop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.nk.tech.aop.entity.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {

}
