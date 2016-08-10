package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.Token;
public interface TokenService {
	public List<Token> findAll();
	public Token findOne(String id);
	public Token findByToken(String token);
	public boolean isValidToken(String token);
	public Token create(Token token);
	public Token update(String id, Token token);
	public void delete(String id);
}
