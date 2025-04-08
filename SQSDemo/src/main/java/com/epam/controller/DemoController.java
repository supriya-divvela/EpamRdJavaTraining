package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.TrainingSummaryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@RestController
public class DemoController {

	private final SqsTemplate sqsTemplate;

	@Autowired
	public DemoController(SqsTemplate sqsTemplate) {
		super();
		this.sqsTemplate = sqsTemplate;
	}

	@PostMapping("/send")
	public TrainingSummaryDto sendMessage(@RequestBody TrainingSummaryDto trainingSummaryDto) {
		sqsTemplate.send(to -> {
			try {
				to.queue("demo-queue").payload(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(trainingSummaryDto));
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		});
		return trainingSummaryDto;
	}

}
