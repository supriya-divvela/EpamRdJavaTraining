package com.epam.service;

import com.epam.dto.EmailDto;

import jakarta.mail.MessagingException;

public interface NotificationService {
	EmailDto sendSimpleMailMessage(EmailDto emailDto);

	EmailDto sendMimeMessageWithAttachment(EmailDto emaildto) throws MessagingException;

	EmailDto sendMimeMessageWithEmbeddedFiles(EmailDto emailDto) throws MessagingException;

	EmailDto sendHtmlEmailWithEmbeddedFiles(EmailDto emailDto) throws MessagingException;

}
