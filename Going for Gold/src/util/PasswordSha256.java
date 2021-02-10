package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* This class contains a static method used to hash the password with SHA256
* 
* @author Peccio Leandro
*
*/
public class PasswordSha256 {
	
     
    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("input : "+sb.toString());
        return sb.toString();
    }

}


