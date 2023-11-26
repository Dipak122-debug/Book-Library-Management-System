package com.amazingbooks.bookms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazingbooks.bookms.model.BookDetails;
import com.amazingbooks.bookms.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value="/book/get/all")
	public ResponseEntity<List<BookDetails>> getAllBook(){
		return bookService.getAllBooks();
	}
	
	@GetMapping(value="/book/get/id/{id}")
	public ResponseEntity<BookDetails> getBookById(@PathVariable("id") int id){
		return bookService.getBookById(id);
	}
	
	@PutMapping(value="/book/update/details")
	public ResponseEntity<String> updateBookDetails(@Valid @RequestBody BookDetails bookDetails){
		return bookService.updateBookDetails(bookDetails);
	}
	
	@DeleteMapping(value="/book/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
		return bookService.deleteBook(id);
	}
	
	@PostMapping(value="/book/add/new")
	public ResponseEntity<String> addNewBook(@Valid @RequestBody BookDetails bookDetails){
		return bookService.addNewBook(bookDetails);
	}
	
	@GetMapping(value = "/book/get/{name}/and/{author}", produces = "application/json")
	public BookDetails getBookByNameandAuthor(@PathVariable("name") String name, @PathVariable("author") String author){
		return bookService.getBookByNameandAuthor(name, author);
	}
	
}
