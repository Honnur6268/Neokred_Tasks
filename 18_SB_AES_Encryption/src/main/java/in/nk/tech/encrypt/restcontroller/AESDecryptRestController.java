package in.nk.tech.encrypt.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.encrypt.aes.AESDecryptor;

@RestController
@RequestMapping("/api")
public class AESDecryptRestController {

	@Autowired
	private AESDecryptor decryptor;

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

	/**
	 * @param data in String
	 * @return decrypted data in from encrypted data in String format.
	 * @throws Exception
	 * @author Honnur Ali //
	 */
	@GetMapping("/customer/decrypt")
	public ResponseEntity<String> getEncryptedCustomerAndDecrypt(@RequestHeader("data") String data) throws Exception {

		String decryptedData = decryptor.decrypt(data);
		if (null != decryptedData) {
			return ResponseEntity.ok("Data Successfully Decrypted : \n" + decryptedData);
		}
		return new ResponseEntity<String>("Error Decrypting data!!!", HttpStatus.NOT_FOUND);
	}
}
