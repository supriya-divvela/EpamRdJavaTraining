package com.epam.service;

import org.springframework.stereotype.Service;

import com.epam.dto.LibraryDto;
import com.epam.exception.LibraryException;
import com.epam.model.LibraryResponse;
@Service
public interface LibraryService {
	LibraryDto issueBook(String username, int bookId) throws LibraryException;

	void receiveBook(String username, int bookId) throws LibraryException;

	LibraryResponse getUsersWithIssuedBooks(String username);

	void deleteUserWithAllBooks(String username);

	void deleteAllByBookIds(int bookId);
}
