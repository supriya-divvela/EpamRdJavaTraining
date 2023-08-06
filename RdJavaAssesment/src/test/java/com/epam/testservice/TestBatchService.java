package com.epam.testservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.epam.exception.BatchException;
import com.epam.model.Associate;
import com.epam.model.Batch;
import com.epam.repository.BatchRepository;
import com.epam.service.BatchServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestBatchService {
	@Mock
	private BatchRepository batchRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private BatchServiceImpl batchService;
	AssociateDto associateDto;
	BatchDto batchDto;
	Batch batch;
	Associate associate;
	List<AssociateDto> associateDtos = new ArrayList<>();
	List<Associate> associates = new ArrayList<>();

	@BeforeEach
	void batchDetails() {
		associateDto = new AssociateDto();
		associateDto.setId(1);
		associateDto.setName("Supriya");
		associateDto.setEmail("supriya_divvela@epam.com");
		associateDto.setBatchId(1);
		associateDto.setGender("f");
		associateDto.setStatus("active");
		associateDto.setCollege("RVR and JC");
		associate = new Associate();
		associate.setId(1);
		associate.setName("Supriya");
		associate.setEmail("supriya_divvela@epam.com");
		associate.setGender("f");
		associate.setStatus("active");
		associate.setCollege("RVR and JC");
		associateDtos.add(associateDto);
		batchDto = new BatchDto();
		batchDto.setId(1);
		batchDto.setName("Java");
		batchDto.setEndDate(LocalDate.now().plusMonths(6));
		batchDto.setStartDate(LocalDate.now());
		batch = new Batch();
		batch.setId(1);
		batch.setName("Java");
		batch.setEndDate(LocalDate.now().plusMonths(6));
		batch.setStartDate(LocalDate.now());
	}

	@Test
	void testAddBatch() throws BatchException {
		when(modelMapper.map(batchDto, Batch.class)).thenReturn(batch);
		when(modelMapper.map(batch, BatchDto.class)).thenReturn(batchDto);
		when(batchRepository.save(Mockito.any(Batch.class))).thenReturn(batch);
		batchService.addBatch(batchDto);
		verify(batchRepository).save(batch);
	}
	
	@Test
	void testAddBatchWithException() throws BatchException {
		batchDto.setEndDate(LocalDate.now());
		assertThrows(BatchException.class, ()->batchService.addBatch(batchDto));
	}

	@Test
	void testDeleteBatch() {
		doNothing().when(batchRepository).deleteById(1);
		batchService.deleteBatch(1);
		verify(batchRepository).deleteById(1);
	}

	@Test
	void testGetAllBatches() {
		List<BatchDto> batchDtos = new ArrayList<>();
		batchDtos.add(batchDto);
		List<Batch> batches = new ArrayList<>();
		batches.add(batch);
		when(batchRepository.findAll()).thenReturn(batches);
		when(modelMapper.map(batch, BatchDto.class)).thenReturn(batchDto);
		assertEquals(1, batchService.getAllBatches().size());

	}

	@Test
	void testGetBatch() throws BatchException {
		when(batchRepository.findById(1)).thenReturn(Optional.of(batch));
		when(modelMapper.map(batch, BatchDto.class)).thenReturn(batchDto);
		assertEquals(batchDto, batchService.getBatch(1));
	}

	@Test
	void testGetBatchWithException() throws BatchException {
		when(batchRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BatchException.class,()->batchService.getBatch(1));
	}

	@Test
	void testUpdateBatch() throws BatchException {
		when(batchRepository.findById(0)).thenReturn(Optional.of(batch));
		when(modelMapper.map(batchDto, Batch.class)).thenReturn(batch);
		when(batchRepository.save(Mockito.any(Batch.class))).thenReturn(batch);
		batchService.updateBatch(0, batchDto);
		verify(batchRepository).save(batch);
	}

	@Test
	void testUpdateBatchWithBatchException() throws BatchException {
		when(batchRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BatchException.class,()->batchService.updateBatch(1,batchDto));
	}

}
