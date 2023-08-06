package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.BatchController;
import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.exception.BatchException;
import com.epam.service.BatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(BatchController.class)
class TestBatchController {
	@MockBean
	private BatchService batchService;
	@Autowired
	private MockMvc mockMvc;
	AssociateDto associateDto;
	BatchDto batchDto;

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
		List<AssociateDto> associateDtos = new ArrayList<>();
		associateDtos.add(associateDto);
		batchDto = new BatchDto();
		batchDto.setId(1);
		batchDto.setName("Java");
		batchDto.setPractise("Java Practise");
		batchDto.setEndDate(LocalDate.now().plusMonths(6));
		batchDto.setStartDate(LocalDate.now());
	}

	@Test
	void testGetAllBatches() throws Exception {
		List<BatchDto> batches = new ArrayList<>();
		when(batchService.getAllBatches()).thenReturn(batches);
		mockMvc.perform(get("/batch").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetBatch() throws Exception {
		when(batchService.getBatch(1)).thenReturn(batchDto);
		mockMvc.perform(get("/batch/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetBatchWithBatchException() throws Exception {
		when(batchService.getBatch(12)).thenThrow(BatchException.class);
		mockMvc.perform(get("/batch/{batchId}",12).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void testGetBatchWithMethodArgumentTypeMismatchException() throws JsonProcessingException, Exception {
		when(batchService.getBatch(1)).thenReturn(batchDto);
		mockMvc.perform(get("/batch/{batchId}", "ab").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testAddBatch() throws JsonProcessingException, Exception {
		when(batchService.addBatch(batchDto)).thenReturn(batchDto);
		mockMvc.perform(post("/batch").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(batchDto))).andExpect(status().isCreated());
	}

	@Test
	void testAddBatchWithRuntimeException() throws JsonProcessingException, Exception {
		when(batchService.addBatch(Mockito.any())).thenThrow(RuntimeException.class);
		mockMvc.perform(post("/batch").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(batchDto))).andExpect(status().isInternalServerError());
	}

	@Test
	void testDeleteBatch() throws JsonProcessingException, Exception {
		doNothing().when(batchService).deleteBatch(1);
		mockMvc.perform(delete("/batch/{batchId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateBatch() throws JsonProcessingException, Exception {
		when(batchService.updateBatch(1, batchDto)).thenReturn(batchDto);
		mockMvc.perform(put("/batch/{batchId}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(batchDto))).andExpect(status().isOk());
	}

	@Test
	void testAddBatchWithDataIntegrityViolationException() throws JsonProcessingException, Exception {
		when(batchService.addBatch(Mockito.any())).thenThrow(DataIntegrityViolationException.class);
		mockMvc.perform(post("/batch").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(batchDto))).andExpect(status().isInternalServerError());
	}
}
