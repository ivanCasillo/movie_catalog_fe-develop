package it.step.controller;

import io.swagger.annotations.Api;
import it.step.entity.User;
import it.step.entity.UserListResponse;
import it.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Api(tags = "User controller")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping(value = "/{page}")
	public ResponseEntity<UserListResponse> getAll(@PathVariable("page") Integer page){
		
		ResponseEntity<UserListResponse> response = null;
		UserListResponse res = new UserListResponse();
		try {
			res.setUserList(service.getAll(page));
			res.setMaxPageNumber(service.getNumPagesAll());
			response = new ResponseEntity<UserListResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<UserListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping(value = "/details/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
		
		ResponseEntity<User> response = null;
		try {
			Optional<User> user = service.getUserById(id);
			if(user.isPresent())
				response = new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		
		ResponseEntity<User> response = null;
		try {
			User userRes = service.saveUser(user);
			response = new ResponseEntity<User>(userRes, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@PutMapping()
	public ResponseEntity<User> updateUserById(@RequestBody User user){
		
		ResponseEntity<User> response = null;
		try {
			User userRes = service.updateUser(user);
			response = new ResponseEntity<User>(userRes, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") Integer id){
		ResponseEntity<User> response = null;
		try {
			Optional<User> user = service.deleteUserById(id);
			if(user.isPresent())
				response = new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@PatchMapping(value = "disable")
	public ResponseEntity<User> disableUser(@RequestBody User user){

		ResponseEntity<User> response = null;
		try {
			User userRes = service.disableUser(user.getId());
			if(Objects.nonNull(userRes)) {
				response = new ResponseEntity<User>(userRes, HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
