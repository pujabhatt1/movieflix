package egen.io.apimodule.repository.imp;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.Role;
import egen.io.apimodule.repository.RoleRepository;
@Repository
public class RoleRepositoryImp implements RoleRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Role> findAll() {
		TypedQuery<Role> query = em.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public Role findOne(String id) {
		return em.find(Role.class, id);
	}

	@Override
	
	public Role create(Role role) {
		em.persist(role);
		return role;
	}

	@Override
	
	public Role update(Role role) {
		return em.merge(role);
	}

	@Override
	
	public void delete(Role role) {
		em.remove(role);
	}
	
}
