package in.nk.tech.encrypt.aes;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.nk.tech.encrypt.common.utils.AESCommonUtils;

/**
 * Possible KEY_SIZE values are 128, 192 and 256 Possible T_LEN values are 128,
 * 120, 112, 104 and 96
 */
@Component
public class AESDecryptor extends AESCommonUtils {

	private SecretKey key;

	@Value("${secretKey}")
	private String propSecretKey;

	@Value("${IV}")
	private String propIV;

	/**
	 * @param secretKey
	 * @param IV
	 * <p>This method initiates the values for secret key and IV(Initialization Vector) 
	 * based on provided key and IV values are
	 * to use in process of encryption and decryption</p>
	 * @author Honnur Ali
	 */
	public void initFromStrings(String secretKey, String IV) {
		key = new SecretKeySpec(decode(secretKey), "AES");
		this.IV = decode(IV);
	}

	/**
	 * @param encryptedMessage
	 * @return decrypted String from encrypted message type
	 * @throws Exception
	 * @author Honnur Ali
	 */
	public String decrypt(String encryptedMessage) throws Exception {
		key = stringToKeyConverter(propSecretKey);
		String encode = encode(key.getEncoded());
		String encode2 = encode(propIV.getBytes());
		initFromStrings(encode, encode2);
		byte[] messageInBytes = decode(encryptedMessage);
		Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
		decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
		return new String(decryptedBytes);
	}

	public String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
}
