package in.nk.tech.encrypt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encrypt.entity.Customer;
import in.nk.tech.encrypt.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * @param customer or any object
	 * @return saved object with status code 200, else 400
	 * @author Honnur Ali
	 */
	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {

		Customer savedCustomer = customerService.saveCustomer(customer);
		if (null != savedCustomer) {
			return ResponseEntity.ok(savedCustomer);
		}
		return ResponseEntity.badRequest().body(savedCustomer);

	}

	/**
	 * @return List of saved Objects with status code 200.
	 * @author Honnur Ali
	 */
	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	/**
	 * @param id
	 * @return saved object with status code 200, else 404
	 * @author Honnur Ali
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.getCustomerById(id);
		if (null != customer) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.notFound().build();
	}

}
