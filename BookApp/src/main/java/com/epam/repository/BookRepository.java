package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
