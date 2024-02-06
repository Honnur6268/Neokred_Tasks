package in.nk.tech.encrypt.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encrypt.rsa.RSAEncryptor;

@RestController
@RequestMapping("/api")
public class RSAEncryptRestController {

	@Autowired
	private RSAEncryptor encryptor;


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
}
