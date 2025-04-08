package com.epam.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.exception.LibraryException;
import com.epam.feign.BookFeignClient;
import com.epam.feign.UserFeignClient;
import com.epam.model.LibraryResponse;
import com.epam.service.LibraryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/library")
@Slf4j
public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private BookFeignClient bookFeignClient;
	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		log.info("LibraryController:getAllBooks");
		return bookFeignClient.getAllBooks();
	}

	@GetMapping("/books/{bookId}")
	public ResponseEntity<BookDto> getBook(@PathVariable("bookId") int bookId) {
		log.info("LibraryController:GetBook");
		return bookFeignClient.getBook(bookId);
	}

	@PostMapping("/books")
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
		log.info("LibraryController:AddBook");
		return bookFeignClient.addBook(bookDto);
	}

	@PutMapping("/books/{bookId}")
	public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") int bookId, @RequestBody BookDto bookDto) {
		log.info("LibraryController:UpdateBook");
		return bookFeignClient.updateBook(bookDto, bookId);
	}

	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteAllByBookIds(@PathVariable("bookId") int bookId) {
		log.info("LibraryController:DeleteBook");
		libraryService.deleteAllByBookIds(bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		log.info("LibraryController:getAllUsers");
		return userFeignClient.getAllUsers();
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<LibraryResponse> getUser(@PathVariable("username") String username) {
		log.info("LibraryController:GetUser");
		return new ResponseEntity<>(libraryService.getUsersWithIssuedBooks(username), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		log.info("LibraryController:Adduser");
		return userFeignClient.addUser(userDto);
	}

	@PutMapping("/users/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
		log.info("LibraryController:UpdateUser");
		return userFeignClient.updateUser(username, userDto);
	}

	@DeleteMapping("users/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		log.info("LibraryController:DeleteUser");
		libraryService.deleteUserWithAllBooks(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<LibraryDto> issueBook(@PathVariable("username") String username,
			@PathVariable("bookId") int bookId) throws LibraryException {
		log.info("LibraryController:IssueBook");
		return new ResponseEntity<>(libraryService.issueBook(username, bookId), HttpStatus.OK);
	}

	@DeleteMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<Void> receiveBook(@PathVariable("username") String username,
			@PathVariable("bookId") int bookId) throws LibraryException {
		log.info("LibraryController:ReceiveBook");
		libraryService.receiveBook(username, bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
