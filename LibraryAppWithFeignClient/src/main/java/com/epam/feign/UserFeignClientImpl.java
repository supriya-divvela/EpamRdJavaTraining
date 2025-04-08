package com.epam.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
@Service
public class UserFeignClientImpl implements UserFeignClient{

	@Override
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(List.of(UserDto.builder().build()),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> getUser(String username) {
		return new ResponseEntity<>(UserDto.builder().build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> addUser(UserDto userDto) {
		return new ResponseEntity<>(UserDto.builder().build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> updateUser(String username, UserDto userDto) {
		return new ResponseEntity<>(UserDto.builder().build(),HttpStatus.OK);
	}

}
