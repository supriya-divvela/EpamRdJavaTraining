package com.epam.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.service.LoginService;
import com.epam.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("userspage")
@Slf4j
public class UserApi {
	@Autowired
	private RegisterService registerService;
	@Autowired
	private LoginService loginService;

	@PostMapping("user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws UserException {
		log.info("UserApi:Add User");
		return new ResponseEntity<>(registerService.addUser(userDto), HttpStatus.CREATED);
	}

	@GetMapping("verify")
	public ResponseEntity<String> verifyUser(@RequestBody UserDto userDto) {
		log.info("UserApi:Verify User");
		if (loginService.getAuthentication(userDto)) {
			return new ResponseEntity<>("Authentication Succesfull..",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Authentication Failure...",HttpStatus.UNAUTHORIZED);
		}
	}

}
