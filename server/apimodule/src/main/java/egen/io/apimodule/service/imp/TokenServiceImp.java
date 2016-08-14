package egen.io.apimodule.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.Token;
import egen.io.apimodule.exception.TokenNotFoundException;
import egen.io.apimodule.repository.TokenRepository;
import egen.io.apimodule.service.TokenService;



@Service
public class TokenServiceImp implements TokenService {
	@Autowired
	TokenRepository repository;
	@Override
	public List<Token> findAll() {
		return repository.findAll();
	}

	@Override
	public Token findOne(String id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional
	public Token create(Token token) {
		return repository.create(token);
		
	}

	@Override
	@Transactional
	public Token update(String id, Token token) {
	  Token existing=repository.findOne(id);
	  if(existing==null){
		  throw new TokenNotFoundException("Token with this id"+id+ "does not exists");
			  
	  }
	  else{
		  return repository.update(token);
	  }
		
	}

	@Override
	@Transactional
	public void delete(String id) {
		  Token existing=repository.findOne(id);
		  if(existing==null){
			  throw new TokenNotFoundException("Token with this id"+id+ "does not exists");
				  
		  }
		  else{
		      repository.delete(existing);
		  }
		
	}

	@Override
	public Token findByToken(String token) {
		return repository.findByToken(token);
	}

	@Override
	public boolean isValidToken(String token) {
		Token existing= repository.findByToken(token);
		System.out.println("at here"+existing);
		if(existing==null){
			return false;
		}
		return true;
	}

}
