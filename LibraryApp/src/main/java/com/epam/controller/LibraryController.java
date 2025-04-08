package com.epam.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.exception.LibraryException;
import com.epam.model.LibraryResponse;
import com.epam.service.LibraryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/library")
@Slf4j
public class LibraryController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LibraryService libraryService;
	private String booksUrl = "http://localhost:1000/books";
	private String usersUrl = "http://localhost:2000/users";

	@GetMapping("/books")
	@ResponseStatus(HttpStatus.OK)
	public List<BookDto> getAllBooks() {
		log.info("LibraryController:getAllBooks");
		return Arrays.asList(restTemplate.getForObject(booksUrl, BookDto[].class));
	}

	@GetMapping("/books/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BookDto> getBook(@PathVariable("bookId") int bookId) {
		log.info("LibraryController:GetBook");
		return restTemplate.getForEntity(booksUrl +"/"+ bookId, BookDto.class);
	}

	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
		log.info("LibraryController:AddBook");
		return restTemplate.postForEntity(booksUrl, bookDto, BookDto.class);
	}

	@PutMapping("/books/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") int bookId, @RequestBody BookDto bookDto) {
		log.info("LibraryController:UpdateBook");
		restTemplate.put(booksUrl + bookId, bookDto);
		return restTemplate.getForEntity(booksUrl+"/" + bookId, BookDto.class);
	}

	@DeleteMapping("/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		log.info("LibraryController:DeleteBook");
		restTemplate.delete(booksUrl+"/" + bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<UserDto> getAllUsers() {
		log.info("LibraryController:getAllUsers");
		return Arrays.asList(restTemplate.getForObject(usersUrl, UserDto[].class));
	}

	@GetMapping("/users/{username}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LibraryResponse> getUser(@PathVariable("username") String username) {
		log.info("LibraryController:GetUser");
		return new ResponseEntity<>(libraryService.getUsersWithIssuedBooks(username), HttpStatus.OK);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		log.info("LibraryController:Adduser");
		return restTemplate.postForEntity(usersUrl, userDto, UserDto.class);
	}

	@PutMapping("/users/{username}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
		log.info("LibraryController:UpdateUser");
		restTemplate.put(usersUrl+"/" + username, userDto, UserDto.class);
		return restTemplate.getForEntity(usersUrl+"/" + username, UserDto.class);
	}

	@DeleteMapping("users/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		log.info("LibraryController:DeleteUser");
		libraryService.deleteUserWithAllBooks(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/users/{username}/books/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LibraryDto> issueBook(@PathVariable("username") String username,
			@PathVariable("bookId") int bookId) throws LibraryException {
		log.info("LibraryController:IssueBook");
		return new ResponseEntity<>(libraryService.issueBook(username, bookId), HttpStatus.OK);
	}

	@DeleteMapping("/users/{username}/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> receiveBook(@PathVariable("username") String username,
			@PathVariable("bookId") int bookId) throws LibraryException {
		log.info("LibraryController:ReceiveBook");
		libraryService.receiveBook(username, bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
