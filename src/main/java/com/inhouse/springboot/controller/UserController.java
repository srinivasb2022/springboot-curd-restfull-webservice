package com.inhouse.springboot.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.springboot.entity.User;
import com.inhouse.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	// get all users
	@GetMapping
	public List<User> getAllUser()
	{
		return this.userRepository.findAll();
	}
	
	//find by user id
	@GetMapping("/{id}")
	public User findByUserId(@PathVariable(value = "id") long userId) throws UserPrincipalNotFoundException
	{
		return this.userRepository.findById(userId).
				orElseThrow(() -> new UserPrincipalNotFoundException("User Not found"+userId));
	}
	//create user
	@PostMapping
	public User creteUser(@RequestBody User user)
	{
		System.out.println("user---->"+user);
		return this.userRepository.save(user);
	}
	
	//update user
	@PutMapping("/{id}")
	public User uodateUser(@RequestBody User user , @PathVariable(value = "id") long userId) throws UserPrincipalNotFoundException {
		User existingUser =this.userRepository.findById(userId).
				orElseThrow(() -> new UserPrincipalNotFoundException("User Not found"+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setFirstName(user.getLastName());
		existingUser.setFirstName(user.getEmail());
		return this.userRepository.save(existingUser);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId) throws UserPrincipalNotFoundException {
		User existingUser =this.userRepository.findById(userId).
				orElseThrow(() -> new UserPrincipalNotFoundException("User Not found"+userId));
		
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
