package com.example.demoredis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoredis.domain.User;
import com.example.demoredis.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8085/api/get-userByid/{id}
	 */
	@GetMapping("/get-userByid/{id}")
	@Cacheable(value = "users", key = "#id", unless = "#result.id<5")
	public User getUser(@PathVariable long id) {
		logger.debug(" >> UserController : /user/{} call : ", id);
		return userService.getUser(id);

	}

	/**
	 * http://localhost:8085/api/create-user
	 */
	@PostMapping("/create-user")
	public User create(@RequestBody User user) {
		logger.debug(" >> UserController : /create-user : ", user.toString());
		return userService.create(user);
	}

	/**
	 * http://localhost:8085/api/getAllusers
	 */
	@GetMapping("/getAllusers")
	public List<User> getAll() {
		logger.debug(" >> UserController : /getAllusers : ");
		return userService.getAll();
	}

	/**
	 * http://localhost:8085/api/update
	 */
	@PutMapping("/update")
	@CachePut(value = "users", key = "#user.id")
	public User updateUser(@RequestBody User user) {
		logger.debug(" >> UserController : /update : ", user.toString());
		return userService.update(user);
	}

	/**
	 * http://localhost:8085/api/delete/{id}
	 */
	@DeleteMapping("/delete/{id}")
	@CacheEvict(value = "users", allEntries = false, key = "#id")
	public void deleteUser(@PathVariable Long id) {
		logger.debug(" >> UserController : /delete : ", id);
		userService.delete(id);
		logger.debug(" << UserController : /delete : ", id);

	}
}
