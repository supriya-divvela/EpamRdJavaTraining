package com.epam.service;

import java.io.File;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.epam.dto.EmailDto;
import com.epam.model.Email;
import com.epam.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String emailFrom;

	@Override
	@Async
	public EmailDto sendSimpleMailMessage(EmailDto emailDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(emailDto.getToEmail());
		message.setText(emailDto.getBody());
		message.setSubject(emailDto.getSubject());
		mailSender.send(message);
		return modelMapper.map(emailRepository.save(modelMapper.map(emailDto, Email.class)), EmailDto.class);
	}

	@Override
	@Async
	public EmailDto sendMimeMessageWithAttachment(EmailDto emailDto) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(emailFrom);
		mimeMessageHelper.setTo(emailDto.getToEmail());
		mimeMessageHelper.setText(emailDto.getBody());
		mimeMessageHelper.setSubject(emailDto.getSubject());
		FileSystemResource fileSystemResource = new FileSystemResource(
				new File("C://Users/Supriya_Divvela/Downloads/MYRESUME.pdf"));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		mailSender.send(mimeMessage);
		return modelMapper.map(emailRepository.save(modelMapper.map(emailDto, Email.class)), EmailDto.class);
	}

	@Override
	@Async
	public EmailDto sendMimeMessageWithEmbeddedFiles(EmailDto emailDto) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(emailFrom);
		mimeMessageHelper.setTo(emailDto.getToEmail());
		mimeMessageHelper.setText(emailDto.getBody());
		mimeMessageHelper.setSubject(emailDto.getSubject());
		FileSystemResource resume = new FileSystemResource(
				new File("C://Users/Supriya_Divvela/Downloads/MYRESUME.pdf"));
		FileSystemResource photo = new FileSystemResource(
				new File("C://Users/Supriya_Divvela/Downloads/MicrosoftTeams-image.png"));
		mimeMessageHelper.addInline("<" + resume.getFilename() + ">", resume);
		mimeMessageHelper.addInline("<" + photo.getFilename() + ">", photo);
		mailSender.send(mimeMessage);
		return modelMapper.map(emailRepository.save(modelMapper.map(emailDto, Email.class)), EmailDto.class);

	}

	@Override
	public EmailDto sendHtmlEmailWithEmbeddedFiles(EmailDto emailDto) throws MessagingException {

		Context context = new Context();
		context.setVariable("name", "Supriya");
		String text = templateEngine.process("emailtemplate", context);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(emailFrom);
		mimeMessageHelper.setTo(emailDto.getToEmail());
//		MimeMultipart mimeMultipart=new MimeMultipart("related");
//		BodyPart messagebody=new MimeBodyPart();
//		messagebody.setContent(text,"text/html");
//		mimeMultipart.addBodyPart(messagebody);
		mimeMessageHelper.setText(text, true);
		mimeMessageHelper.setSubject(emailDto.getSubject());
		FileSystemResource fileSystemResource = new FileSystemResource(
				new File("C://Users/Supriya_Divvela/Downloads/MYRESUME.pdf"));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		FileSystemResource epamlogo = new FileSystemResource(
				new File("C://Users/Supriya_Divvela/Downloads/epamlogo.jpeg"));
		mimeMessageHelper.addInline("epamlogo", epamlogo);
		mailSender.send(mimeMessage);
		return modelMapper.map(emailRepository.save(modelMapper.map(emailDto, Email.class)), EmailDto.class);

	}

}
