package in.nk.tech.encrypt.service;

import java.util.List;

import in.nk.tech.encrypt.entity.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(Integer id);
}
