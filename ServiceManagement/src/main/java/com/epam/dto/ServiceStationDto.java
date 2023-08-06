package com.epam.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ServiceStationDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	private int registrationNo;
	private int engineNo;
	@Schema(accessMode = AccessMode.READ_ONLY)
	private LocalDate deliveryDate;
}
