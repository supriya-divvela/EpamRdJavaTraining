package com.epam.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	@NotBlank(message = "Please provide valid batch name..")
	private String name;
	@NotBlank(message = "Please provide valid practise name..")
	private String practise;
	@NotNull(message = "Please provide valid date in format(YYYY-MM-DD)")
	private LocalDate startDate;
	@NotNull(message = "Please provide valid date in format(YYYY-MM-DD)")
	private LocalDate endDate;
}
