package com.mysql.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.demo.models.Book;
import com.mysql.demo.repo.BookRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController(value = "/book")
@Api("This controller handles CRUD operation for the Books")
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@PostMapping(value = "/saveBook")
	@ApiOperation(value = "This API will insert the book object into the database", 
	response = Object.class)
	public String insertBook(@RequestBody Book book) {
		repository.save(book);
		return "Book added successfully";
	}
	
	@PostMapping(value = "/saveAllBooks")
	@ApiOperation(value = "This API will insert all the given books into the database", 
	response = Object.class)
	public String insertAllBooks(@RequestBody List<Book> books) {
		repository.saveAll(books);
		return "Books saved successfully";
	}
	
	@GetMapping("/getBookById")
	@ApiOperation(value="Return the book by the book id" , response = Book.class)
	public Optional<Book> getBooks(@RequestParam Long id) {
		return repository.findById(id);
	}
	
	@GetMapping("/getAllBook")
	@ApiOperation(value="Return All the books" , response = Book.class)
	public List<Book> getBooks() {
		return repository.findAll();
	}
	
	@GetMapping("/getByBookName")
	@ApiOperation(value="Return the book by the book name" , response = Book.class)
	public List<Book> getBooks(@RequestParam String bookName) {
		return repository.findBybookName(bookName);
	}
	
	@PutMapping(value= "/updateBookById")
	@ApiOperation(value="Book updated success fully" , response = String.class)
	public String updateBookById(@RequestParam Long id, @RequestBody Book book) {
		Book bk = repository.findById(id).get();
		bk.setBookAuthor(book.getBookAuthor());
		bk.setBookName(book.getBookName());
		repository.save(bk);
		return "Book updated successfully";
	}
	
	@DeleteMapping(value="/deleteById")
	@ApiOperation(value="This API will delete book by the id" , response = String.class)
	public String deleteBookById(@RequestParam Long id) {
		repository.deleteById(id);
		
		return "Book removed successfully";
	}

}
