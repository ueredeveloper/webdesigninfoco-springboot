package com.ueredeveloper.webdesigninfoco.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ueredeveloper.webdesigninfoco.com.models.User;
import com.ueredeveloper.webdesigninfoco.com.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository repository;

	UserController(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@PostMapping(path = { "/login" })
	public ResponseEntity<User> findUserByNameAndPassword(@RequestBody User user) {

		User u = (User) repository.findByNameAndPassword(user.getName(), user.getPassword());

		if (u != null) {
			return ResponseEntity.ok().body(u);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<User> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<User> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public User create(@RequestBody User user) {
		return repository.save(user);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable int id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User user) {
		return repository.findById(id).map(record -> {
			record.setName(user.getName());
			record.setPassword(user.getPassword());
			record.setCreatedAt(user.getCreatedAt());
			record.setUpdatedAt(user.getUpdatedAt());

			User updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
