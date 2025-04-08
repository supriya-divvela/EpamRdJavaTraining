package com.epam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.RoleDto;
import com.epam.service.interfaces.RoleService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/gym/role")
@AllArgsConstructor

@RestController
public class RoleController {
	private RoleService roleService;

	@GetMapping("/{username}")
	public ResponseEntity<RoleDto> getRole(@PathVariable("username") String username) {
		log.info("RoleController : getRole ");
		return new ResponseEntity<>(new RoleDto(roleService.getRole(username)), HttpStatus.OK);
	}
}
