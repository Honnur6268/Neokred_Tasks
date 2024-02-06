package in.nk.tech.encrypt.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.Data;

@Data
public class AESCommonUtils {

	public SecretKey key;

	public final int KEY_SIZE = 128;

	public final int T_LEN = 128;

	public byte[] IV;
	
	public SecretKey stringToKeyConverter(String key) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = messageDigest.digest(key.getBytes());
		return new SecretKeySpec(hashBytes, "AES");
	}
}
