package com.epam.service;

import java.util.List;

import com.epam.dto.AssociateDto;
import com.epam.exception.AssociateException;

public interface AssociateService {
	AssociateDto addAssociate(AssociateDto associateDto);

	void deleteAssociate(int associateId);

	List<AssociateDto> getAllAssociates();

	List<AssociateDto> getAssociateByGender(String gender) throws AssociateException;

	AssociateDto updateAssociate(int associateId,AssociateDto associateDto) throws AssociateException;
}
