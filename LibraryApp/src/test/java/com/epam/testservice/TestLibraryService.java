package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.exception.LibraryException;
import com.epam.feign.BookFeignClient;
import com.epam.feign.UserFeignClient;
import com.epam.model.Library;
import com.epam.repository.LibraryRepository;
import com.epam.service.LibraryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestLibraryService {
	@Mock
	private LibraryRepository libraryRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private LibraryServiceImpl libraryService;
	@Mock
	private BookFeignClient bookFeignClient;
	@Mock
	private UserFeignClient userFeignClient;

	@Test
	void testIssueBook() throws LibraryException {
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		Library library = new Library(0, "hii", 1);
		LibraryDto libraryDto = LibraryDto.builder().bookId(1).username("hii").bookId(1).build();
		when(modelMapper.map(library, LibraryDto.class)).thenReturn(libraryDto);
		when(libraryRepository.save(library)).thenReturn(library);
		libraryService.issueBook("hii", 1);
		verify(libraryRepository).save(library);
	}

	@Test
	void testIssueBookWithDuplicateBookTaken() throws LibraryException {
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		when(libraryRepository.existsByBookIdAndUsername(1, "hii")).thenReturn(true);
		assertThrows(LibraryException.class,()->libraryService.issueBook("hii",1));
	}

	@Test
	void testDeleteUserWithAllBooks() {
		doNothing().when(libraryRepository).deleteAllByUsername("hii");
		libraryService.deleteUserWithAllBooks("hii");
		verify(libraryRepository).deleteAllByUsername("hii");
	}

	@Test
	void testIssueBookWithMaximumBooksTaken() throws LibraryException {
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		when(libraryRepository.countByUsername("hii")).thenReturn(3l);
		assertThrows(LibraryException.class,()->libraryService.issueBook("hii",1));
	}

	@Test
	void testReceiveBook() throws LibraryException {
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		doNothing().when(libraryRepository).deleteByUsernameAndBookId("hii", 1);
		when(libraryRepository.existsByBookIdAndUsername(1, "hii")).thenReturn(true);
		libraryService.receiveBook("hii", 1);
		verify(libraryRepository).deleteByUsernameAndBookId("hii", 1);
	}

	@Test
	void testReceiveBookWithBookNotTaken() throws LibraryException {
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		when(libraryRepository.existsByBookIdAndUsername(1,"hii")).thenReturn(false);
		assertThrows(LibraryException.class,()->libraryService.receiveBook("hii",1));
	}

	@Test
	void testGetUsersWithIssuedBooks() {
		List<Library> userIdsWithBookIds =new ArrayList<>();
		userIdsWithBookIds.add(Library.builder().id(1).username("hii").bookId(1).build());
		BookDto bookDto = BookDto.builder().name("okok").publisher("okok").author("okok").quantity(4).build();
		when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto,HttpStatus.OK));
		UserDto userDto = new UserDto("hii", "hi@gmail.com", "ij");
		when(userFeignClient.getUser("hii")).thenReturn(new ResponseEntity<>(userDto,HttpStatus.OK));
		when(libraryRepository.findAllByUsername("hii")).thenReturn(userIdsWithBookIds);
		libraryService.getUsersWithIssuedBooks("hii");
		verify(userFeignClient).getUser("hii");
	}
	
	@Test
	void testDeleteAllByBookId() {
		doNothing().when(libraryRepository).deleteAllByBookId(1);
		libraryService.deleteAllByBookIds(1);
		verify(libraryRepository).deleteAllByBookId(1);
	}
}
