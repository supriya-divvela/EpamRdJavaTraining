package com.epam.service;

import java.util.List;

import com.epam.dto.BookDto;
import com.epam.exception.BookException;

public interface BookService {
	BookDto addBook(BookDto bookDto);

//	void deleteBook(String bookNo);
//
//	List<BookDto> getAllBooks();
//
//	BookDto getBook(String bookNo) throws BookException;
//
//	BookDto updateBook(String bookId,BookDto bookDto) throws BookException;
}
