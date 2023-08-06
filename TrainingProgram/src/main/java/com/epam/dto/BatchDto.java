package com.epam.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class BatchDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int batchId;
	@NotBlank(message = "Please provide valid batch name..")
	private String batchName;
	@Size(min=1,message = "Minimum one mentee should present..")
	List<MenteeDto> mentee;
}
