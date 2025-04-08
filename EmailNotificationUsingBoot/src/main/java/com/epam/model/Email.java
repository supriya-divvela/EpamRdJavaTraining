package com.epam.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String toEmail;
	private String body;
	private String subject;
}
