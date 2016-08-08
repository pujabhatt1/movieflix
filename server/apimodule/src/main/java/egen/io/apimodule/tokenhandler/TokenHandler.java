package egen.io.apimodule.tokenhandler;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.NotAdminException;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.service.MovieService;
import egen.io.apimodule.service.UserService;
import egen.io.apimodule.service.imp.UserServiceImp;

@Service
public class TokenHandler {
	@Autowired
	private UserService userService;

	@Autowired
	MovieService service;

	public TokenHandler() {

	}

	public TokenHandler(UserService userService) {
		System.out.println("Inside TextEditor constructor.");
		this.userService = userService;
	}

	public String creatToken(String email, String role) {
		final String issuer = "https://mydomain.com/";
		final String secret = "{{My secret word}}";

		final long iat = System.currentTimeMillis() / 1000l; // issued at claim
		final long exp = iat + 360L; // expires claim. In this case the token
										// expires in 360 seconds

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

	public Map<String, Object> verifyToken(String jwt)
			throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalStateException, SignatureException, IOException {
		final String secret = "{{My secret word}}";

		try {

			final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String, Object> claims = verifier.verify(jwt);
			return claims;
		} catch (JWTVerifyException e) {
			// Invalid Token
		}
		return null;
	}

	public void verifyTokenAndAdmin(String jwt) throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalStateException,
			SignatureException, IOException {

		final String secret = "{{My secret word}}";
		try {
			System.out.println(jwt);
			final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String, Object> claims = verifier.verify(jwt);
			if (claims.size() == 0) {
				throw new UserNotFoundException(
						"User not found!!please login with other valid email and password");
			}
			Iterator it = claims.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());

				if (pair.getKey().equals("role")) {
					if (pair.getValue().equals("User")) {
						throw new NotAdminException(
								"You are not admin, Please login as Admin");
					}
				}
			}
		} catch (JWTVerifyException e) {
			// Invalid Token
		}
	}

	public void invalidateToken(String jwt) throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalStateException,
			SignatureException, IOException {
		final String secret = "{{invalid token}}";
		final String issuer = "https://mydomain.com/";

		final long iat = System.currentTimeMillis() / 1000l; // issued at claim
		final long exp = iat + 1L; // expires claim. In this case the token
									// expires in 360 seconds
		final JWTSigner signer = new JWTSigner(secret);
	}
}
