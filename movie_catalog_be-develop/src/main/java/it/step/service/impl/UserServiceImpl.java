package it.step.service.impl;

import it.step.entity.User;
import it.step.repository.UserRepository;
import it.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getAll(Integer page) {
		return repo.findAll(PageRequest.of(--page, 10, Sort.by("id"))).toList();
	}
	
	@Override
	public Optional<User> getUserById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	@Override
	public Optional<User> deleteUserById(Integer id) {
		Optional<User> userRes = getUserById(id);
		repo.deleteById(id);
		return userRes;
	}
	
	@Override
	public User updateUser(User user) {
		return repo.saveAndFlush(user);
	}
	
	@Override
	public Integer getNumPagesAll() {
		Integer numElem = repo.findAll().size();
		return (int) Math.ceil((double)numElem/10);
	}
	
	@Override
	public User disableUser(Integer id) {
		if(Objects.nonNull(repo.findById(id).get())) {
			if(Objects.nonNull(repo.findById(id).get().getDisabledAt())) {
				repo.findById(id).get().setDisabledAt(null);
			} else {
				repo.findById(id).get().setDisabledAt(Date.valueOf(LocalDate.now()));
			}
		}
		
		return repo.findById(id).orElse(null);
	}

}
