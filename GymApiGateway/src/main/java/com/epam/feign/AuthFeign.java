package com.epam.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient( name = "auth-service/auth",fallback = AuthFeignImpl.class)
public interface AuthFeign {
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token);

}
