package com.epam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Email
	private String username;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=*!])(?=\\\\S+$).{8,}$", message = "Password should follow strong password characters..")
	private String password;
	@Pattern(regexp = "^(?i)(user|admin)$", message = "usertype should be either admin or user..")
	private String role;
}
