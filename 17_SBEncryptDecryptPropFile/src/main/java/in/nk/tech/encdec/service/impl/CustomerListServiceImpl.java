package in.nk.tech.encdec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.encdec.entity.CustomerList;
import in.nk.tech.encdec.repo.CustomerListRepo;
import in.nk.tech.encdec.service.CustomerListService;

@Service
public class CustomerListServiceImpl implements CustomerListService {

	@Autowired
	private CustomerListRepo listRepo;

	@Override
	public List<CustomerList> getAllCustomers() {
		return listRepo.findAll();
	}

	@Override
	public CustomerList getCustomerBYId(Integer id) {

		return listRepo.findById(id).get();
	}

}
