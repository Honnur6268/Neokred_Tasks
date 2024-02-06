package in.nk.tech.encrypt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encrypt.aes.AESEncryptor;
import in.nk.tech.encrypt.entity.Customer;
import in.nk.tech.encrypt.entity.EncryptedData;
import in.nk.tech.encrypt.repo.EncryptedDataRepo;
import in.nk.tech.encrypt.service.CustomerService;

@RestController
@RequestMapping("/api")
public class AESEncryptRestController {

	@Autowired
	private AESEncryptor encryptor;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EncryptedDataRepo dataRepo;


	/**
	 * @param message in String
	 * @return String of encrypted data with status code 200.
	 * @throws Exception
	 * @author Honnur Ali
	 */
	@GetMapping("/encrypt")
	public ResponseEntity<String> encrypt(@RequestHeader("message") String message) throws Exception {
		String encryptedMessage = encryptor.encrypt(message);
		return ResponseEntity.ok(encryptedMessage);
	}

	/**
	 * @param customer or Object
	 * @return String of encrypted data with status code 200.
	 * @throws Exception
	 * @author Honnur Ali
	 */
	@PostMapping("/customer")
	public ResponseEntity<String> customerEncrypt(@RequestBody Customer customer) throws Exception {
		String encryptedMessage = encryptor.encryptObject(customer);
		return ResponseEntity.ok(encryptedMessage);
	}

	/**
	 * @return encrypted data in String format with status code 200, 
	 * else error response with status code 406
	 * @throws Exception
	 * @author Honnur Ali
	 */
	@GetMapping("/customer/encrypt")
	public ResponseEntity<String> getAllCustomersAndEncrypt() throws Exception {
		List<Customer> customers = customerService.getAllCustomers();
		if (null != customers) {

			String encryptedData = encryptor.encryptListOfObjects(customers);
			EncryptedData data = new EncryptedData();
			data.setData(encryptedData);
			dataRepo.save(data);
			return ResponseEntity.ok("Data Successfully Encrypted : " + encryptedData);
		}
		return new ResponseEntity<String>("Error Encrypting data!!!", HttpStatus.NOT_ACCEPTABLE);
	}

	
	/**
	 * @param id
	 * @return encrypted data in String format with status code 200, 
	 * else error response with status code 406
	 * @throws Exception
	 * @author Honnur Ali
	 */
	@GetMapping("/customer/encrypt/{id}")
	public ResponseEntity<String> getCustomerAndEncrypt(@PathVariable Integer id) throws Exception {
		Customer customer = customerService.getCustomerById(id);
		if (null != customer) {
			String encryptedData = encryptor.encryptObject(customer);
			EncryptedData data = new EncryptedData();
			data.setData(encryptedData);
			dataRepo.save(data);
			return ResponseEntity.ok("Data Successfully Encrypted : " + encryptedData);
		}
		return new ResponseEntity<String>("Error Encrypting data!!!", HttpStatus.NOT_ACCEPTABLE);
	}

}
