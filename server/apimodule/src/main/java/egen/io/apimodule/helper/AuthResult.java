package egen.io.apimodule.helper;

import org.springframework.stereotype.Component;

@Component
public class AuthResult {
String result;
String token;
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
}
