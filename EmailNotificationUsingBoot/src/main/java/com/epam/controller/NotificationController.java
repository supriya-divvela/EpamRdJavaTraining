package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.EmailDto;
import com.epam.service.NotificationService;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/email")
@RestController
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<EmailDto> sendSimpleMailMessage(@RequestBody EmailDto emailDto) {
		log.info("NotificationController : Send Email Method..");
		return new ResponseEntity<>(notificationService.sendSimpleMailMessage(emailDto), HttpStatus.OK);
	}

	@PostMapping("/attachment")
	public ResponseEntity<EmailDto> sendMimeMessageWithAttachment(@RequestBody EmailDto emailDto)
			throws MessagingException {
		log.info("NotificationController : Send Email With Attachment Method..");
		return new ResponseEntity<>(notificationService.sendMimeMessageWithAttachment(emailDto), HttpStatus.OK);
	}

	@PostMapping("/embeddedimage")
	public ResponseEntity<EmailDto> sendMimeMessageWithEmbeddedImage(@RequestBody EmailDto emailDto)
			throws MessagingException {
		log.info("NotificationController : Send Mime Message With Embedded Image..");
		return new ResponseEntity<>(notificationService.sendMimeMessageWithEmbeddedFiles(emailDto), HttpStatus.OK);
	}

	@PostMapping("/htmltemplate")
	public ResponseEntity<EmailDto> sendHtmlTemplate(@RequestBody EmailDto emailDto) throws MessagingException {
		log.info("NotificationController : Send HTML Template..");
		return new ResponseEntity<>(notificationService.sendHtmlEmailWithEmbeddedFiles(emailDto), HttpStatus.OK);
	}
}
