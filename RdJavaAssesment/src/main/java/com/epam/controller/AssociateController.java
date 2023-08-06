package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.AssociateDto;
import com.epam.exception.BatchException;
import com.epam.service.AssociateService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rd/associates")
@Slf4j
public class AssociateController {
	@Autowired
	private AssociateService associateService;

	@GetMapping
	public ResponseEntity<List<AssociateDto>> getAllAssociates() {
		log.info("AssociateApi : Get All Associates Method..");
		return new ResponseEntity<>(associateService.getAllAssociates(), HttpStatus.OK);
	}

	@GetMapping("/{gender}")
	public ResponseEntity<List<AssociateDto>> getAssociate(@PathVariable("gender") String gender) throws BatchException {
		log.info("AssociateApi : Get Associate Method..");
		return new ResponseEntity<>(associateService.getAssociateByGender(gender), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<AssociateDto> addAssociate(@RequestBody @Valid AssociateDto associateDto) throws BatchException {
		log.info("AssociateApi : Add Associate Method..");
		return new ResponseEntity<>(associateService.addAssociate(associateDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/{associateId}")
	public ResponseEntity<Void> deleteAssociate(@PathVariable("associateId") int associateId) {
		log.info("AssociateApi : Delete Associate Method..");
		associateService.deleteAssociate(associateId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

	@PutMapping("/{associateId}")
	public ResponseEntity<AssociateDto> updateAssociate(@PathVariable("associateId") int associateId, @RequestBody @Valid AssociateDto associateDto)
			throws BatchException {
		log.info("AssociateApi : Update Associate Method..");
		return new ResponseEntity<>(associateService.updateAssociate(associateId, associateDto), HttpStatus.OK);
	}
}
