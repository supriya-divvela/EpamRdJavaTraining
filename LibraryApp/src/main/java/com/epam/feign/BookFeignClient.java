package com.epam.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.dto.BookDto;

import jakarta.validation.Valid;

@FeignClient(url = "http://localhost:1000/books", name = "books")
public interface BookFeignClient {

	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<BookDto>> getAllBooks();

	@GetMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BookDto> getBook(@PathVariable("bookId") int bookId);

	@PostMapping
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto);

	@PutMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto bookDto, @PathVariable("bookId") int bookId);

	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId);

}
