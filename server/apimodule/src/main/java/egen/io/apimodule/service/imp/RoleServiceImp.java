package egen.io.apimodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.Role;
import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.RoleNotFoundException;
import egen.io.apimodule.helper.Md5Hashing;
import egen.io.apimodule.repository.RoleRepository;
import egen.io.apimodule.repository.RoleRepository;
import egen.io.apimodule.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	RoleRepository repository;
	@Autowired

	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}
	@Override
	public Role findOne(String id) {
		Role existing = repository.findOne(id);
		if (existing == null) {
			throw new RoleNotFoundException("Role with id:" + id + " not found");
		}
	
		return existing;
	}

	@Override
	@Transactional
	public Role create(Role role) {
			return repository.create(role);
	}

	@Override
	@Transactional
	public Role update(String id, Role role) {
		Role existing = repository.findOne(id);
		if (existing == null) {
			throw new RoleNotFoundException("Role with id:" + id + " not found");
		}
		role.setRoleId(existing.getRoleId());
		return repository.update(role);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Role existing = repository.findOne(id);
		if (existing == null) {
			throw new RoleNotFoundException("Role with id:" + id + " not found");
		}
		repository.delete(existing);
	}
	
	
}
