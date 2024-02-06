package in.nk.tech.encrypt.rsa;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RSADecryptor {

	private PrivateKey privateKey;

//	private static final String PUBLIK_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDE/2wuTQ9BE3lGL5kbkA3Z2qCX8oQ4LrHIfWJysEVhOCnHTnoiVxKE/d5dXvd2yyeAFWW41vvmpQoOUbAVSGMtIruf6rJwH3kbEA5Jd50mySK/2ZMpEAO3FaqzwCpqR5+Wl4CR7xXZznxsRhUACP1DSIyfuze8J7Mp9keVDgSHnwIDAQAB";
	@Value("${PRIVATE_KEY}")
	private String PRIVATE_KEY ;//= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMT/bC5ND0ETeUYvmRuQDdnaoJfyhDgusch9YnKwRWE4KcdOeiJXEoT93l1e93bLJ4AVZbjW++alCg5RsBVIYy0iu5/qsnAfeRsQDkl3nSbJIr/ZkykQA7cVqrPAKmpHn5aXgJHvFdnOfGxGFQAI/UNIjJ+7N7wnsyn2R5UOBIefAgMBAAECgYAKMnzzejWuu3of/r+NxH7GQIgZ+9aJ4F2yvRrNfDMuY+11tBA9mtHW/huQLCXiVlD5hlU5PjE7FsWKFc+bKmx67QBfXeCvxu+qcuoSu62YEtxPlN1eRZ2FPOA3MGeGRH999wI0xe+ZiHE5o9x0a5IbVhEfR6ycQkh4NG8f1ieI4QJBAMXO/yGbtdFc21fp7C47+i9yUWbkaucIUaUggJPo9gYUC4Mbvvtax7ZvwIeDvZSdHITqIOFi8GoFMgvQSzeDMecCQQD+81ybzpyCvCAhz5nbVIPkP2KOj/VyA/b+FsRge4KAcFd8VqdLLdgqYAF6jZ27gyh4vbYewIvz5b5vgyF5djWJAkBfCU+81vHo7+MWfvYRVJGkQBlqBO5zp6SKYG5GIhQp4XGSgT8lxnzTYyeuNBZek4mDNnWrs/xsA6kC0gWBW1AbAkB+gDYlQQE7hSjUXfPTdj3zFNhMMHXy2rDFJnrN/LqmDBf8o+piTR8C95wEORxYnB2Ku2cQHy3SGN7C6efEIrkxAkEAvSV9sm2SEyk/1N0lPG5gXUZdc5bd7eq2Rjn1zbyk6aP2en7x7M+OBYymeR+QJjXO+70+g0uXvfhCWHhot8zrFg==";

	public void initFromString() {
		try {
//			X509EncodedKeySpec keySpec_publicKey = new X509EncodedKeySpec(decode(PUBLIK_KEY));
			PKCS8EncodedKeySpec keySpec_privateKey = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY));

			KeyFactory factory = KeyFactory.getInstance("RSA");

//			publicKey = factory.generatePublic(keySpec_publicKey);
			privateKey = factory.generatePrivate(keySpec_privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String decrypt(String encryptedMessage) throws Exception {
		initFromString();
		byte[] encryptedBytes = decode(encryptedMessage);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage, "UTF8");
	}

	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
}
