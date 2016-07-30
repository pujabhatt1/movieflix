package egen.io.apimodule.tokenhandler;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import egen.io.apimodule.exception.NotAdminException;
@Component
public class TokenHandler {

	public String creatToken(String email, String role){
		final String issuer = "https://mydomain.com/";
		final String secret = "{{a secret used for signing}}";

		final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
		final long exp = iat + 360L; // expires claim. In this case the token expires in 360 seconds

		final JWTSigner signer = new JWTSigner(secret);
		final HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("iss", issuer);
		claims.put("exp", exp);
		claims.put("iat", iat);
		claims.put("email", email);
		claims.put("role", role);
		final String jwt = signer.sign(claims);
		return jwt;
	}
	public void verifyToken(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException{
		final String secret = "{{a secret used for signing}}";
		try {
			final JWTVerifier verifier = new JWTVerifier(secret);
		    final Map<String,Object> claims= verifier.verify(jwt);
		 		} catch (JWTVerifyException e) {
		    // Invalid Token
		}
	}
	public void verifyTokenAndAdmin(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException{
			
		final String secret = "{{a secret used for signing}}";
		try {
			System.out.println(jwt);
		    final JWTVerifier verifier = new JWTVerifier(secret);
		    final Map<String,Object> claims= verifier.verify(jwt);
		    Iterator it = claims.entrySet().iterator();
		     while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        if(pair.getKey().equals("role")){
		        	if(pair.getValue().equals("User")){
		        		throw new NotAdminException("You are not admin, Please login as Admin");
		        	}
		        }
		           }
		} catch (JWTVerifyException e) {
		    // Invalid Token
		}
	}
}
