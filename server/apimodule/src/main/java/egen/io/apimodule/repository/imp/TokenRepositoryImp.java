package egen.io.apimodule.repository.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.Token;
import egen.io.apimodule.repository.TokenRepository;


@Repository
public class TokenRepositoryImp implements TokenRepository {
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Token> findAll() {
		javax.persistence.TypedQuery<Token> query=em.createNamedQuery("Token.findAll",Token.class);
		return query.getResultList();
	}

	@Override
	public Token findOne(String id) {
		return em.find(Token.class, id);
	}

	@Override
	public Token findByToken(String token) {
		javax.persistence.TypedQuery<Token> query=em.createNamedQuery("Token.findByToken",Token.class);
		query.setParameter("pToken", token);
		List<Token> Tokens=query.getResultList();
		if(Tokens!=null && Tokens.size()==1){
			System.out.println(Tokens.get(0));
			return Tokens.get(0);
		}
		return null;
	}

	@Override
	public Token create(Token token) {
		em.persist(token);
		return token;
	}

	@Override
	public Token update(Token token) {
		return em.merge(token);
	}

	@Override
	public void delete(Token token) {
		em.remove(token);
	}

	

}
