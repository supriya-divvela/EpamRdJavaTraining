package com.epam.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.epam.dto.TrainerDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraineeProfileResponse {
	
	
	private String firstname;
	private String lastname;
	private LocalDate dateOfBirth;
	private String address;
	@JsonProperty
	private boolean isActive;
	private List<TrainerDto> listOfTrainers;
}
