package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Token;



public interface TokenRepository {
	public List<Token> findAll();

	public Token findOne(String id);

	public Token findByToken(String token);

	public Token create(Token token);

	public Token update(Token token);

	public void delete(Token token);
}
