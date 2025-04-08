package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

	void deleteByUsernameAndBookId(String username, int bookId);

	List<Library> findAllByUsername(String username);

	boolean existsByBookIdAndUsername(int bookId, String username);

	boolean existsByBookId(int bookId);

	boolean existsByUsername(String username);

	void deleteAllByUsername(String username);

	void deleteAllByBookId(int bookId);

	Long countByUsername(String username);

}
