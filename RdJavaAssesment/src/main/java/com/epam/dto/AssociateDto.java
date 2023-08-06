package com.epam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AssociateDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Integer id;
	@NotBlank(message="Please provide valid name..")
	private String name;
	@Email(message = "please provide valid email..")
	private String email;
	@Pattern(regexp = "[MFmf]", message = "Please provide gender either male('M') or female('F')")
	private String gender;
	private String college;
	@Pattern(regexp = "(?i)^(active|inactive)$", message = "Status must be 'active' or 'inactive'")
	private String status;
    private int batchId;
}
