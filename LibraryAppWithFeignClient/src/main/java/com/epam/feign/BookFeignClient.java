package com.epam.feign;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.BookDto;

import jakarta.validation.Valid;

@FeignClient( name = "book-service/books",fallback = BookFeignClientImpl.class)
@LoadBalancerClient(name="book-service/books",configuration = BookFeignClientImpl.class)
public interface BookFeignClient {

	@GetMapping
	public ResponseEntity<List<BookDto>> getAllBooks();

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto> getBook(@PathVariable("bookId") int bookId);

	@PostMapping
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto);

	@PutMapping("/{bookId}")
	public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto bookDto, @PathVariable("bookId") int bookId);

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId);

}
