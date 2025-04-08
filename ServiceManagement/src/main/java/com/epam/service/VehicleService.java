package com.epam.service;

import java.time.LocalDate;
import java.util.List;

import com.epam.dto.ServiceStationDto;

public interface VehicleService {
	public LocalDate registerVehicle(ServiceStationDto serviceStationDto);
	public List<ServiceStationDto> getVehicleServiceDetails(int registrationNo);
}
