package com.amazingbooks.bookms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.amazingbooks.bookms.model.BookDetails;

import jakarta.validation.Valid;

public interface BookService {

	public ResponseEntity<List<BookDetails>> getAllBooks();

	public ResponseEntity<BookDetails> getBookById(int id);

	public ResponseEntity<String> updateBookDetails(@Valid BookDetails book);

	public ResponseEntity<String> deleteBook(int id);

	public ResponseEntity<String> addNewBook(@Valid BookDetails book);

	public BookDetails getBookByNameandAuthor(String name, String author);

}
