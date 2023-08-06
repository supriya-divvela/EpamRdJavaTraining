package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.exception.AssociateException;
import com.epam.model.Associate;
import com.epam.model.Batch;
import com.epam.repository.AssociateRepository;
import com.epam.service.AssociateServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestAssociateService {
	@Mock
	private AssociateRepository associateRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private AssociateServiceImpl associateService;
	AssociateDto associateDto;
	BatchDto batchDto;
	Batch batch;
	Associate associate;
	List<AssociateDto> associateDtos = new ArrayList<>();
	List<Associate> associates = new ArrayList<>();

	@BeforeEach
	void associateDetails() {
		associateDto = new AssociateDto();
		associateDto.setId(1);
		associateDto.setName("Supriya");
		associateDto.setEmail("supriya_divvela@epam.com");
		associate = new Associate();
		associate.setId(1);
		associate.setName("Supriya");
		associate.setEmail("supriya_divvela@epam.com");
		associateDtos.add(associateDto);
		associates.add(associate);
	}
//	@Override
//	public AssociateDto addAssociate(AssociateDto associateDto) {
//		log.info("AssociateService : Add Associate Method...");
//		return modelMapper.map(associateRepository.save(modelMapper.map(associateDto, Associate.class)),
//				AssociateDto.class);
//
//	}
	@Test
	void testAddAssociate() throws AssociateException {
		when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		when(associateRepository.save(Mockito.any(Associate.class))).thenReturn(associate);
		associateService.addAssociate(associateDto);
		verify(associateRepository).save(associate);
	}

	@Test
	void testDeleteAssociate() {
		doNothing().when(associateRepository).deleteById(1);
		associateService.deleteAssociate(1);
		verify(associateRepository,times(1)).deleteById(1);
	}

	@Test
	void testGetAllAssociates() {
		when(associateRepository.findAll()).thenReturn(associates);
		when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		assertEquals(1, associateService.getAllAssociates().size());

	}

	@Test
	void testGetAssociatesByGender() throws AssociateException {
		when(associateRepository.findAllByGender("m")).thenReturn(associates);
		when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		assertEquals(associateDtos, associateService.getAssociateByGender("m"));
	}

	@Test
	void testGetAssociatesWithEmptyList() throws AssociateException {
		associateDtos=new ArrayList<>();
		associates=new ArrayList<>();
		when(associateRepository.findAllByGender("o")).thenReturn(associates);
		assertEquals(associateDtos.size(),associateService.getAssociateByGender("o").size());
	}

	@Test
	void testUpdateAssociate() throws AssociateException {
		when(associateRepository.findById(0)).thenReturn(Optional.of(associate));
		when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		when(associateRepository.save(Mockito.any(Associate.class))).thenReturn(associate);
		associateService.updateAssociate(0, associateDto);
		verify(associateRepository).save(associate);
	}
	@Test
	void testUpdateAssociateWithException() throws AssociateException {
		when(associateRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(AssociateException.class,()->associateService.updateAssociate(1,associateDto));
	}
}