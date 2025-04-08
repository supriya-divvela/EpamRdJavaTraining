package com.epam.service;

import java.util.List;

import com.epam.dto.BookDto;
import com.epam.exception.BookException;

public interface BookService {
	BookDto addBook(BookDto bookDto);

	void deleteBook(int bookNo);

	List<BookDto> getAllBooks();

	BookDto getBook(int bookNo) throws BookException;

	BookDto updateBook(int bookId,BookDto bookDto) throws BookException;
}
