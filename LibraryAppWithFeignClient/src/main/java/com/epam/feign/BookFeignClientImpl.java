package com.epam.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDto;

import jakarta.validation.Valid;
@Service
public class BookFeignClientImpl implements BookFeignClient{

	@Override
	public ResponseEntity<List<BookDto>> getAllBooks() {
		return new ResponseEntity<>(List.of(BookDto.builder().build()),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookDto> getBook(int bookId) {
		return new ResponseEntity<>(BookDto.builder().build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookDto> addBook(@Valid BookDto bookDto) {
		return  new ResponseEntity<>(BookDto.builder().build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookDto> updateBook(@Valid BookDto bookDto, int bookId) {
		 return new ResponseEntity<>(BookDto.builder().build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteBook(int bookId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
