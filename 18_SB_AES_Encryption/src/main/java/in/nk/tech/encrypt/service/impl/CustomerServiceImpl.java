package in.nk.tech.encrypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.encrypt.entity.Customer;
import in.nk.tech.encrypt.repo.CustomerRepo;
import in.nk.tech.encrypt.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer saveCustomer(Customer customer) {

		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {

		return customerRepo.findById(id).get();
	}

}
