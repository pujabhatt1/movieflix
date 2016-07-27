package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.User;

public interface UserService {
	public List<User> findAll();
	public User findOne(String id);
	public boolean authenticate(User user);
	public User create(User user);
	public User update(String id, User user);
	public void delete(String id);
}
