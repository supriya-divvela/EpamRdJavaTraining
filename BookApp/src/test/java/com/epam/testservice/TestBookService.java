package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.BookDto;
import com.epam.exception.BookException;
import com.epam.model.Book;
import com.epam.repository.BookRepository;
import com.epam.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestBookService {
	@Mock
	private BookRepository bookRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Test
	void testAddBook() throws BookException {
		Book book =Book.builder().id(0).author("ok").name("ok").publisher("ok").quantity(0).build();
		BookDto bookDto = BookDto.builder().author("ok").name("ok").publisher("ok").quantity(0).build();
		when(bookRepository.save(book)).thenReturn(book);
		bookService.addBook(bookDto);
		verify(bookRepository,times(1)).save(book);
	}

	@Test
	void testDeleteBook() {
		doNothing().when(bookRepository).deleteById(1);
		bookService.deleteBook(1);
		verify(bookRepository).deleteById(1);
	}

	@Test
	void testGetAllBooks() {
		Book book = new Book();
		book.setId(1);
		BookDto bookDto = new BookDto();
		List<Book> booksList = new ArrayList<>();
		booksList.add(book);
		when(bookRepository.findAll()).thenReturn(booksList);
		when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
		assertEquals(1, bookService.getAllBooks().size());

	}
	@Test
	void testGetBook() throws BookException {
		Book book=new Book();
		book.setId(1);
		BookDto bookDto=new BookDto();
		when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
		assertEquals(bookDto, bookService.getBook(1));
	}
	
	@Test
	void testGetBookWithException() throws BookException {
		when(bookRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BookException.class,()->bookService.getBook(1));
	}
	@Test
	void testUpdateBook() throws BookException {
		Book book=Book.builder().id(0).author("ok").name("ok").publisher("ok").quantity(0).build();
		BookDto bookDto= BookDto.builder().author("ok").name("ok").publisher("ok").quantity(0).build();
		when(bookRepository.findById(0)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(book);
		bookService.updateBook(0, bookDto);
		verify(bookRepository).save(book);
	}
	
	@Test
	void testUpdateBookWithException() throws BookException {
		when(bookRepository.findById(1)).thenReturn(Optional.empty());
		BookDto bookDto=new BookDto();
		assertThrows(BookException.class,()->bookService.updateBook(1,bookDto));
	}
}
