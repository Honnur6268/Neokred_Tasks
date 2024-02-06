package in.nk.tech.encrypt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nk.tech.encrypt.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
