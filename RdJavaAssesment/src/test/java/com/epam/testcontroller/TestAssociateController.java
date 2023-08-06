package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.controller.AssociateController;
import com.epam.dto.AssociateDto;
import com.epam.service.AssociateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(AssociateController.class)
@RequestMapping("/rd/associates")
class TestAssociateController {
	@MockBean
	private AssociateService associateService;
	@Autowired
	private MockMvc mockMvc;
	AssociateDto associateDto;
	List<AssociateDto> associateDtos = new ArrayList<>();

	@BeforeEach
	void associateDetails() {
		associateDto = new AssociateDto();
		associateDto.setId(1);
		associateDto.setName("Supriya");
		associateDto.setEmail("supriya_divvela@epam.com");
		associateDto.setBatchId(1);
		associateDto.setGender("F");
		associateDto.setStatus("active");
		associateDto.setCollege("RVR and JC");
		associateDtos.add(associateDto);
	}

	@Test
	void testGetAllAssociates() throws Exception {
		when(associateService.getAllAssociates()).thenReturn(associateDtos);
		mockMvc.perform(get("/rd/associates").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetAssociatesWithGender() throws Exception {
		when(associateService.getAssociateByGender("m")).thenReturn(associateDtos);
		mockMvc.perform(get("/rd/associates/{gender}",1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testAddAssociateWithMethodArgumentNotValidException() throws JsonProcessingException, Exception {
		associateDto = new AssociateDto();
		when(associateService.addAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/rd/associates").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(associateDto)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testAddAssociate() throws JsonProcessingException, Exception {
		when(associateService.addAssociate(Mockito.any(AssociateDto.class))).thenReturn(associateDto);
		mockMvc.perform(post("/rd/associates").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isCreated());
	}

	@Test
	void testDeleteAssociate() throws JsonProcessingException, Exception {
		doNothing().when(associateService).deleteAssociate(1);
		mockMvc.perform(delete("/rd/associates/{associateId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateAssociate() throws JsonProcessingException, Exception {
		when(associateService.updateAssociate(1, associateDto)).thenReturn(associateDto);
		mockMvc.perform(put("/rd/associates/{associateId}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isOk());
	}
}
