package it.step.service;

import it.step.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	
	public List<User> getAll(Integer page);
	
	public Optional<User> getUserById(Integer id);
	
	public User saveUser(User user);
	
	public Optional<User> deleteUserById(Integer id);
	
	public User updateUser(User user);
	
	public Integer getNumPagesAll();
	
	public User disableUser(Integer id);
}
