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
import com.epam.exception.BookException;
import com.epam.service.BookService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<BookDto>> getAllBooks() {
		log.info("BookApi:Get All Books Method..");
		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto> getBook(@PathVariable("bookId") int bookId) throws BookException {
		log.info("BookApi:Get Book Method..");
		return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) throws BookException {
		log.info("BookApi:Add Book Method..");
		return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
	}

	@DeleteMapping("{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		log.info("BookApi:delete Book Method..");
		bookService.deleteBook(bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") int bookId, @RequestBody @Valid BookDto bookDto)
			throws BookException {
		log.info("BookApi:update Book Method..");
		return new ResponseEntity<>(bookService.updateBook(bookId, bookDto), HttpStatus.OK);
	}
}
