package it.uniroma3.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Helper class for encoding gravatar emails
 *
 * @author Gaetano
 *
 */

public class MD5Helper {

	private MessageDigest md;

	public MD5Helper() {
		try {
			this.md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}

	public String md5(String txt){
		md.update(txt.getBytes());
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		String encoded = sb.toString();

		//System.out.println(generatedPassword);

		return encoded;
	}


}
