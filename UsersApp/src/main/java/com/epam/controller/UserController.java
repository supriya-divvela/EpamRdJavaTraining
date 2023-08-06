package com.epam.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDto>> getaAllUsers() {
		log.info("UserApi:Get All Users..");
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) throws UserException {
		log.info("UserApi:Get User..");
		return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) throws UserException {
		log.info("UserApi:Add User..");
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		log.info("UserApi:delete User..");
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username,
			@RequestBody @Valid UserDto userDto) throws UserException {
		log.info("UserApi:update User..");
		return new ResponseEntity<>(userService.updateUser(username, userDto), HttpStatus.OK);
	}

}
