package egen.io.apimodule.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.Role;
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
		System.out.println("role id"+role.getRoleId());
		System.out.println("merging");
		return em.merge(role);
	}

	@Override
	public void delete(Role role) {
		em.remove(role);
	}
	
}
