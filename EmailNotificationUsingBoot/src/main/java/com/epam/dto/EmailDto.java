package com.epam.dto;

import lombok.Data;

@Data
public class EmailDto {
	private String toEmail;
	private String body;
	private String subject;
}
