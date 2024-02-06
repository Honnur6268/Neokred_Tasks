package in.nk.tech.encdec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encdec.entity.CustomerList;
import in.nk.tech.encdec.service.CustomerListService;

@RestController
@RequestMapping("/customers")
public class CustomerListController {

	@Autowired
	private CustomerListService listService;

	@GetMapping
	public ResponseEntity<List<CustomerList>> getCustomers() {
		List<CustomerList> customers = listService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerList> getCustomerById(@PathVariable Integer id) {

		return ResponseEntity.ok(listService.getCustomerBYId(id));
	}
}
