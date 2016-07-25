package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.Role;


public interface RoleService {
	public List<Role> findAll();
	public Role findOne(String id);
	public Role create(Role role);
	public Role update(String id, Role role);
	public void delete(String id);
}
