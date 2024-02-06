package in.nk.tech.encrypt.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encrypt.rsa.RSADecryptor;

@RestController
@RequestMapping("/api")
public class RSADecryptRestController {

	@Autowired
	private RSADecryptor decryptor;

	/**
	 * @param message
	 * @return String of decrypted data
	 * @throws Exception
	 */
	@GetMapping("/decrypt")
	public ResponseEntity<String> decrypt(@RequestHeader("message") String message) throws Exception {
		String encryptedMessage = decryptor.decrypt(message);
		return ResponseEntity.ok(encryptedMessage);
	}
}
