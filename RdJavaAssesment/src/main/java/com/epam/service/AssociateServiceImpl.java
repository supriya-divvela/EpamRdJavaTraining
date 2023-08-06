package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.AssociateDto;
import com.epam.exception.AssociateException;
import com.epam.model.Associate;
import com.epam.repository.AssociateRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssociateServiceImpl implements AssociateService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AssociateRepository associateRepository;

	@Override
	public AssociateDto addAssociate(AssociateDto associateDto) {
		log.info("AssociateService : Add Associate Method...");
		return modelMapper.map(associateRepository.save(modelMapper.map(associateDto, Associate.class)),
				AssociateDto.class);

	}

	@Transactional
	@Override
	public void deleteAssociate(int associateId) {
		log.info("AssociateService : Delete Associate Method...");
		associateRepository.deleteById(associateId);

	}

	@Override
	public List<AssociateDto> getAllAssociates() {
		log.info("AssociateService : Get All Associates Method...");
		return associateRepository.findAll().stream().map(associate -> modelMapper.map(associate, AssociateDto.class))
				.toList();
	}

	@Override
	public List<AssociateDto> getAssociateByGender(String gender) throws AssociateException {
		log.info("AssociateService : Get Associate Method...");
		return associateRepository.findAllByGender(gender).stream()
				.map(associate -> modelMapper.map(associate, AssociateDto.class)).toList();

	}

	@Override
	public AssociateDto updateAssociate(int associateId, AssociateDto associateDto) throws AssociateException {
		log.info("AssociateService : Update Associate Method...");
		return modelMapper.map(associateRepository.findById(associateId).map(associate -> {
			associateDto.setId(associateId);
			associateRepository.save(modelMapper.map(associateDto, Associate.class));
			return associate;
		}).orElseThrow(() -> new AssociateException("Associate not found....")), AssociateDto.class);
	}
}
