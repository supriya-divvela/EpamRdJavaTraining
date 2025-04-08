package com.epam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.ServiceStationDto;
import com.epam.exception.ServiceStationException;
import com.epam.service.VehicleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/service-station")
@RestController
public class ServiceStationController {
	
	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/{registrationNo}")
	public ResponseEntity<List<ServiceStationDto>> getAllVehicleServiceDetails(
			@PathVariable("registrationNo") int registrationNo) {
		log.info("ServiceStationController : Get All Vehicle Service Details Method..");
		return new ResponseEntity<>(vehicleService.getVehicleServiceDetails(registrationNo), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LocalDate> addAssociate(@RequestBody @Valid ServiceStationDto serviceStationDto)
			throws ServiceStationException {
		log.info("AssociateApi : Add Associate Method..");
		return new ResponseEntity<>(vehicleService.registerVehicle(serviceStationDto), HttpStatus.CREATED);
	}

}
