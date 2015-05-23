package it.uniroma3.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Helper class for password encoding and management
 *
 * @author Gaetano
 *
 */

public class PasswordHelper {
	
	private MessageDigest md;
	
	public PasswordHelper() {
		try {
		 this.md = MessageDigest.getInstance("SHA-256");
		 
		}
		catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
	}
	
	public String securePassword(String password){
		//Add password bytes to digest
        md.update(password.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        String generatedPassword = sb.toString();
        
        //System.out.println(generatedPassword);
        
        return generatedPassword;
	}
	
	
}
