package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.exception.LibraryException;
import com.epam.feign.BookFeignClient;
import com.epam.feign.UserFeignClient;
import com.epam.model.Library;
import com.epam.model.LibraryResponse;
import com.epam.repository.LibraryRepository;

import jakarta.transaction.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookFeignClient bookFeignClient;
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public LibraryDto issueBook(String username, int bookId) throws LibraryException {
		BookDto bookDto = bookFeignClient.getBook(bookId).getBody();
		UserDto userDto = userFeignClient.getUser(username).getBody();
		if (libraryRepository.existsByBookIdAndUsername(bookId, username)) {
			throw new LibraryException(username + " already take book taken with this " + bookId);
		}
		if (libraryRepository.countByUsername(userDto.getUsername()) == 3) {
			throw new LibraryException(
					username + " already took 3 books..please return atleast one book and take new one..");
		}
		return modelMapper.map(libraryRepository.save(Library.builder().username(username).bookId(bookId).build()),
				LibraryDto.class);

	}

	@Transactional
	@Override
	public void receiveBook(String username, int bookId) throws LibraryException {
		BookDto bookDto = bookFeignClient.getBook(bookId).getBody();
		UserDto userDto = userFeignClient.getUser(username).getBody();
		if (!libraryRepository.existsByBookIdAndUsername(bookId, username)) {
			throw new LibraryException(username + " didnot take any book taken with this " + bookId);
		}
		libraryRepository.deleteByUsernameAndBookId(username, bookId);
	}

	@Override
	public LibraryResponse getUsersWithIssuedBooks(String username) {
		return LibraryResponse.builder().listOfBooks(libraryRepository.findAllByUsername(username).stream()
				.map(library -> bookFeignClient.getBook(library.getBookId()).getBody()).toList())
				.userDto(userFeignClient.getUser(username).getBody()).build();
	}

	@Transactional
	@Override
	public void deleteUserWithAllBooks(String username) {
		libraryRepository.deleteAllByUsername(username);
	}

	@Override
	public void deleteAllByBookIds(int bookId) {
		libraryRepository.deleteAllByBookId(bookId);	
	}

}
