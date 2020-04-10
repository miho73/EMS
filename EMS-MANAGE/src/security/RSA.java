package security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
	public KeyPair getKey() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair pair = keyPairGenerator.genKeyPair();
		return pair;
	}
	public static byte[] encrypt(String plainText,Key publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
	}
	public static String decrypt(byte[] encryText,Key privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] arrData = cipher.doFinal(encryText);
        return new String(arrData);
	}
	public static String keyToStr(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	public static Key StrToKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kfrsa = KeyFactory.getInstance("RSA");
		return kfrsa.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(key)));
	}
}
