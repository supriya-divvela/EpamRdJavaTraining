package com.epam.model;

import java.util.List;

import com.epam.dto.BookDto;
import com.epam.dto.UserDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Data
@Getter
@Setter
@ToString
@Builder
public class LibraryResponse {
	private UserDto userDto;
	private List<BookDto> listOfBooks;
}
