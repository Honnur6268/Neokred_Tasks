package in.nk.tech.encrypt.rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//AES/CBC/NoPadding (128)
//AES/CBC/PKCS5Pading (128)
//AES/ECB/NoPADDING (128)
//AES/ECB/PKCS5Padding (128)
//RSA/ECB/PKCS1Padding (1024, 2048)
//RSA/ECB/OAEPWithSHA-1AndMGF1Padding 1024, 2048)
//RSA/ECB/OAEPWithSHA-256AndMGF1Padding 1024, 2048)

@Component
public class RSAEncryptor {

//	private PrivateKey privateKey;
	private PublicKey publicKey;

	@Value("${PUBLIK_KEY}")
	private String PUBLIK_KEY ;//= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDE/2wuTQ9BE3lGL5kbkA3Z2qCX8oQ4LrHIfWJysEVhOCnHTnoiVxKE/d5dXvd2yyeAFWW41vvmpQoOUbAVSGMtIruf6rJwH3kbEA5Jd50mySK/2ZMpEAO3FaqzwCpqR5+Wl4CR7xXZznxsRhUACP1DSIyfuze8J7Mp9keVDgSHnwIDAQAB";
//	private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMT/bC5ND0ETeUYvmRuQDdnaoJfyhDgusch9YnKwRWE4KcdOeiJXEoT93l1e93bLJ4AVZbjW++alCg5RsBVIYy0iu5/qsnAfeRsQDkl3nSbJIr/ZkykQA7cVqrPAKmpHn5aXgJHvFdnOfGxGFQAI/UNIjJ+7N7wnsyn2R5UOBIefAgMBAAECgYAKMnzzejWuu3of/r+NxH7GQIgZ+9aJ4F2yvRrNfDMuY+11tBA9mtHW/huQLCXiVlD5hlU5PjE7FsWKFc+bKmx67QBfXeCvxu+qcuoSu62YEtxPlN1eRZ2FPOA3MGeGRH999wI0xe+ZiHE5o9x0a5IbVhEfR6ycQkh4NG8f1ieI4QJBAMXO/yGbtdFc21fp7C47+i9yUWbkaucIUaUggJPo9gYUC4Mbvvtax7ZvwIeDvZSdHITqIOFi8GoFMgvQSzeDMecCQQD+81ybzpyCvCAhz5nbVIPkP2KOj/VyA/b+FsRge4KAcFd8VqdLLdgqYAF6jZ27gyh4vbYewIvz5b5vgyF5djWJAkBfCU+81vHo7+MWfvYRVJGkQBlqBO5zp6SKYG5GIhQp4XGSgT8lxnzTYyeuNBZek4mDNnWrs/xsA6kC0gWBW1AbAkB+gDYlQQE7hSjUXfPTdj3zFNhMMHXy2rDFJnrN/LqmDBf8o+piTR8C95wEORxYnB2Ku2cQHy3SGN7C6efEIrkxAkEAvSV9sm2SEyk/1N0lPG5gXUZdc5bd7eq2Rjn1zbyk6aP2en7x7M+OBYymeR+QJjXO+70+g0uXvfhCWHhot8zrFg==";

	public void init() {
		try {

			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);

			KeyPair pair = generator.generateKeyPair();
//			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initFromString() {
		try {
			X509EncodedKeySpec keySpec_publicKey = new X509EncodedKeySpec(decode(PUBLIK_KEY));
//			PKCS8EncodedKeySpec keySpec_privateKey = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY));

			KeyFactory factory = KeyFactory.getInstance("RSA");

			publicKey = factory.generatePublic(keySpec_publicKey);
//			privateKey = factory.generatePrivate(keySpec_privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void exportKeys() {
//		System.out.println("Public Key: " + encode(publicKey.getEncoded()));
//		System.out.println("Private Key: " + encode(privateKey.getEncoded()));
//	}

	public String encrypt(String message) throws Exception {
		initFromString();
		byte[] messageToBytes = message.getBytes();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(messageToBytes);
		return encode(encryptedBytes);
	}

	private String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
}
