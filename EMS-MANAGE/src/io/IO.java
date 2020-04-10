package io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class IO {
	public static void Fileout(String path, String str, String key) throws Exception {
		SecureRandom rand=new SecureRandom();
		byte salt[]=new byte[20];
		rand.nextBytes(salt);
		SecretKeyFactory fact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec=new PBEKeySpec(key.toCharArray(),salt,70000,256);
		SecretKey secretKey = fact.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(),"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
	    byte[] encryptedTextBytes = cipher.doFinal(str.getBytes("UTF-8"));
	    byte[] buffer = new byte[salt.length + ivBytes.length + encryptedTextBytes.length];
	    System.arraycopy(salt, 0, buffer, 0, salt.length);
	    System.arraycopy(ivBytes, 0, buffer, salt.length, ivBytes.length);
	    System.arraycopy(encryptedTextBytes, 0, buffer, salt.length + ivBytes.length, encryptedTextBytes.length);
		FileWriter w= new FileWriter(path);
		w.write(Base64.getEncoder().encodeToString(buffer));
		w.close();
	}
	public static String Filein(String path, String key) throws Exception {
		String msg=in(path);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));
	    byte[] saltBytes = new byte[20];
	    buffer.get(saltBytes, 0, saltBytes.length);
	    byte[] ivBytes = new byte[cipher.getBlockSize()];
	    buffer.get(ivBytes, 0, ivBytes.length);
	    byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
	    buffer.get(encryoptedTextBytes);
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
	    SecretKey secretKey = factory.generateSecret(spec);
	    SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
	    byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);
	    return new String(decryptedTextBytes,"utf-8");
	}
	public static String in(String path) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
		int buf;StringBuffer snwjr=new StringBuffer();
		while((buf=in.read())!=-1) {
			snwjr.append((char)buf);
		}
		in.close();
		return snwjr.toString();
	}
	public static void out(String path, String data) throws Exception{
		BufferedWriter in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
		in.write(data);
		in.close();
	}
}
