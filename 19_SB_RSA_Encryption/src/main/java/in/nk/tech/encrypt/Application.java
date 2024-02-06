package in.nk.tech.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		RSAEncryptor rsa = new RSAEncryptor();
//		RSADecryptor rsaDec = new RSADecryptor();
////		rsa.init();
////		rsa.initFromString();
////		rsaDec.initFromString();
//		try {
//			String encryptedMessage = rsa.encrypt("Bhavana V");
//			String decryptedMessage = rsaDec.decrypt(encryptedMessage);
//			System.out.println("Length: " + encryptedMessage.length());
//			System.out.println("Encrypted: " + encryptedMessage);
//			System.out.println("Decrypted: " + decryptedMessage);
//
////			rsa.exportKeys();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
