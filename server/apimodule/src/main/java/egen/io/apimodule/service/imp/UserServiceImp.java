package egen.io.apimodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.UserAlreadyExistsException;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.helper.Md5Hashing;
import egen.io.apimodule.repository.RoleRepository;
import egen.io.apimodule.repository.UserRepository;
import egen.io.apimodule.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository repository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private Md5Hashing md;
	
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findOne(String id) {
		User existing = repository.findOne(id);
		if (existing == null) {
			throw new UserNotFoundException("User with id:" + id + " not found");
		}
	
		return existing;
	}

	
	@Override
	public boolean authenticate(User user){
		User existing = repository.findByEmail(user.getEmail());
		if(existing ==null){
			//throw new UserNotFoundException("User with email:" + user.getEmail() + " not found");
	      return false;
		}
		else{
			String md5Password=md.getMd5Pass(user.getPassword());
			User dbUser = repository.findOne(existing.getId());
			if(md5Password.equals(dbUser.getPassword())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	@Transactional
	public User create(User user) {
		User existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			throw new UserAlreadyExistsException("Email is already in use: " + user.getEmail());
		}
		  user.setPassword(md.getMd5Pass(user.getPassword()));
		return repository.create(user);
	}

	@Override
	@Transactional
	public User update(String id, User user) {
		User existing = repository.findOne(id);
		if (existing == null) {
			throw new UserNotFoundException("User with id:" + id + " not found");
		}
		user.setId(existing.getId());
		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		User existing = repository.findOne(id);
		if (existing == null) {
			throw new UserNotFoundException("User with id:" + id + " not found");
		}
		repository.delete(existing);
	}
	
}
