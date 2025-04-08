package com.epam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class BookDto {
	@NotBlank(message = "Please enter valid name...")
	String name;
	@NotBlank(message = "Please enter valid publisher...")
	String publisher;
	@NotBlank(message = "Please enter valid author...")
	String author;
	@Positive
	int quantity;
}
