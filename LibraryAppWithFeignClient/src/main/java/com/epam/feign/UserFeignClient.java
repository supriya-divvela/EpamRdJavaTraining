package com.epam.feign;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.UserDto;

@FeignClient(name = "user-service/users",fallback = UserFeignClientImpl.class)
@LoadBalancerClient(name="user-service/users",configuration = UserFeignClientImpl.class)
public interface UserFeignClient {
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers();

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable("username") String username);

	@PostMapping
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto);

	@PutMapping("/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto);

}
