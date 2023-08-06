package com.epam.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.dto.UserDto;

@FeignClient(url = "http://localhost:2000/users", name = "users")
public interface UserFeignClient {
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDto> getAllUsers();
	
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDto> getUser(String username);

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto);

	@PutMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto);
	
}
