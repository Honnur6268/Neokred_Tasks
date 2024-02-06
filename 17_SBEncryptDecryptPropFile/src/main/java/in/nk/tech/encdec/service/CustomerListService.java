package in.nk.tech.encdec.service;

import java.util.List;

import in.nk.tech.encdec.entity.CustomerList;

public interface CustomerListService {

	public List<CustomerList> getAllCustomers();
	
	public CustomerList getCustomerBYId(Integer id);
}
