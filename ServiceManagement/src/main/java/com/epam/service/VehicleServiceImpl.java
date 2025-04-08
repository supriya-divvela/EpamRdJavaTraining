package com.epam.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.ServiceStationDto;
import com.epam.model.ServiceStation;
import com.epam.repository.ServiceStationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ServiceStationRepository serviceStationRepository;

	@Override
	public LocalDate registerVehicle(ServiceStationDto serviceStationDto) {
		log.info("VehicleServiceImpl : Register Vehicle Method...");
		serviceStationDto.setDeliveryDate(serviceStationDto.getDeliveryDate().plusDays(5));
		return serviceStationRepository.save(modelMapper.map(serviceStationDto, ServiceStation.class))
				.getDeliveryDate();

	}

	@Override
	public List<ServiceStationDto> getVehicleServiceDetails(int registrationNo) {
		log.info("VehicleServiceImpl : Get Vehicle Details...");
		System.out.println(serviceStationRepository.findAllByRegistrationNo(registrationNo));
		return serviceStationRepository.findAllByRegistrationNo(registrationNo).stream()
				.map(vehicle -> modelMapper.map(vehicle, ServiceStationDto.class)).toList();
	}

}
