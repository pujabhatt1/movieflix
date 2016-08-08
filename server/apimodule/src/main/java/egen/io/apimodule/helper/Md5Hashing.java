package egen.io.apimodule.helper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Md5Hashing {
	 public String getMd5Pass(String password)
	    {
	    	MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	        md.update(password.getBytes());
    
            byte byteData[] = md.digest();

            //convert the byte to hex format 
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
              }
 
    return sb.toString();
}
}
