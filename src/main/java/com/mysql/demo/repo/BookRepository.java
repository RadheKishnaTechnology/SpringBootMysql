package com.mysql.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysql.demo.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book> findBybookName(String bookName);

}
