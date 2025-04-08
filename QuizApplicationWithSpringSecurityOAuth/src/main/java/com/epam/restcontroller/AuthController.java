package com.epam.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.service.TokenService;

@RestController
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private TokenService tokenService;

	@PostMapping("/token")
	public String token(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Token granted: {}", token);
		return token;
	}
}