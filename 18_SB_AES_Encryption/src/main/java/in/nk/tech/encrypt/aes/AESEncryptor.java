package in.nk.tech.encrypt.aes;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nk.tech.encrypt.common.utils.AESCommonUtils;
import in.nk.tech.encrypt.entity.Customer;

/**
 * Possible KEY_SIZE values are 128, 192 and 256 
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */
@Component
public class AESEncryptor extends AESCommonUtils {

	private SecretKey key;

	@Value("${secretKey}")
	private String propSecretKey;

	@Value("${IV}")
	private String propIV;

	public void init() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(KEY_SIZE);
		key = keyGenerator.generateKey();
	}

	/**
	 * @param secretKey
	 * @param IV
	 *                  <p>
	 *                  This method initiates the values for secret key and
	 *                  IV(Initialization Vector) based on provided key and IV
	 *                  values are to use in process of encryption and decryption
	 *                  </p>
	 * @author Honnur Ali
	 */
	public void initFromStrings(String secretKey, String IV) {
		key = new SecretKeySpec(decode(secretKey), "AES");
		this.IV = decode(IV);

	}

	public String encryptOld(String message) throws Exception {
		byte[] messageInBytes = message.getBytes();
		Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
		this.IV = encryptionCipher.getIV();
		byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
		return encode(encryptedBytes);
	}

	/**
	 * @param message
	 * @return String of encrypted data from supplied data
	 * @throws Exception
	 * @author Honnur Ali
	 */
	public String encrypt(String message) throws Exception {
		key = stringToKeyConverter(propSecretKey);
		String encode = encode(key.getEncoded());
		String encode2 = encode(propIV.getBytes());
		initFromStrings(encode, encode2);
		byte[] messageInBytes = message.getBytes();
		Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
		encryptionCipher.init(Cipher.ENCRYPT_MODE, key, spec);
		IV = encryptionCipher.getIV();
		byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);

		return encode(encryptedBytes);
	}

	/**
	 * @param customer or any object
	 * @return String of encrypted data from provided input data
	 * @throws Exception
	 * @author Honnur Ali
	 */
	public String encryptObject(Customer customer) throws Exception {
		key = stringToKeyConverter(propSecretKey);
		String encode = encode(key.getEncoded());
		String encode2 = encode(propIV.getBytes());
		initFromStrings(encode, encode2);

		ObjectMapper mapper = new ObjectMapper();
		String customerToString = mapper.writeValueAsString(customer);
		byte[] messageInBytes = customerToString.getBytes();
		Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
		encryptionCipher.init(Cipher.ENCRYPT_MODE, key, spec);
		IV = encryptionCipher.getIV();
		byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
		return encode(encryptedBytes);
	}
	
	/**
	 * @param List of customers object or any List object
	 * @return String of encrypted data from supplied data
	 * @throws Exception
	 * @author Honnur Ali
	 */
	public String encryptListOfObjects(List<Customer> customers) throws Exception {
		key = stringToKeyConverter(propSecretKey);
		initFromStrings(encode(key.getEncoded()), encode(propIV.getBytes()));

		ObjectMapper mapper = new ObjectMapper();
		String customerToString = mapper.writeValueAsString(customers);
		byte[] messageInBytes = customerToString.getBytes();
		Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
		encryptionCipher.init(Cipher.ENCRYPT_MODE, key, spec);
		IV = encryptionCipher.getIV();
		byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
		return encode(encryptedBytes);
	}

	public String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	/**
	 * @return secret key, that to be used by both parties to encrypt and decrypt
	 * @author Honnur Ali
	 */
	public String exportSecretKey() {
		return encode(key.getEncoded());
	}

	/**
	 * @return IV key, same key to be used by both parties to encrypt and decrypt
	 *         along with secret key
	 * @author Honnur Ali
	 */
	public String exportIV() {
		return encode(IV);
	}
}
