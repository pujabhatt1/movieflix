package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Role;

public interface RoleRepository {
	public List<Role> findAll();

	public Role findOne(String id);
	public Role create(Role role);

	public Role update(Role role);

	public void delete(Role role);
}
