package com.quantion.ejercicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quantion.ejercicio.model.User;
import com.quantion.ejercicio.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	/**
	 * Method to retrieve all the users of the application
	 * 
	 * @return Users
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

	}

	/**
	 * Method to save a new user
	 * 
	 * @param user User to be created
	 * @return Created user
	 */
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		user = userService.save(user);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method to delete a user
	 * 
	 * @param id Id of the user to be deleted
	 * @return
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	/**
	 * Method to update the info of an user
	 * 
	 * @param id Id of the user to be updated
	 * @param user User
	 * @return The updated user
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {

		user = userService.updateUser(id, user);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
