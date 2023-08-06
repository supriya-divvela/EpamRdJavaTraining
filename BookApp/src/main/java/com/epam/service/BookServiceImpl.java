package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDto;
import com.epam.exception.BookException;
import com.epam.model.Book;
import com.epam.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookDto addBook(BookDto bookDto) {
		log.info("BookService:AddBookMethod...");
		return modelMapper.map(bookRepository.save(Book.builder().name(bookDto.getName())
				.publisher(bookDto.getPublisher()).author(bookDto.getAuthor()).build()), BookDto.class);
	}

	@Transactional
	@Override
	public void deleteBook(int bookNo) {
		log.info("BookService:DeleteBookMethod...");
		bookRepository.deleteById(bookNo);
	}

	@Override
	public List<BookDto> getAllBooks() {
		log.info("BookService:GetAllBooksMethod...");
		return bookRepository.findAll().stream().map(book -> modelMapper.map(book, BookDto.class)).toList();
	}

	@Override
	public BookDto getBook(int bookNo) throws BookException {
		log.info("BookService:GetBookMethod...");
		return bookRepository.findById(bookNo).map(book -> modelMapper.map(book, BookDto.class))
				.orElseThrow(() -> new BookException("Book Not Found With This Book Number"));
	}

	@Override
	public BookDto updateBook(int bookId, BookDto bookDto) throws BookException {
		log.info("BookService:UpdateBookMethod...");
		return modelMapper.map(bookRepository.findById(bookId)
				.map(book -> bookRepository.save(Book.builder().name(bookDto.getName())
						.publisher(bookDto.getPublisher()).author(bookDto.getAuthor()).build()))
				.orElseThrow(() -> new BookException("Book not found....")), BookDto.class);
	}
}
