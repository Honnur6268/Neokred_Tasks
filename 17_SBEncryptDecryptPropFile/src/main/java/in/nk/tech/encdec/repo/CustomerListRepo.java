package in.nk.tech.encdec.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nk.tech.encdec.entity.CustomerList;

public interface CustomerListRepo extends JpaRepository<CustomerList, Integer> {

}
