package egen.io.apimodule.helper;

import org.springframework.stereotype.Component;

@Component
public class AuthResult {
String result;
String userId;
String token;
String role;
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}

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
