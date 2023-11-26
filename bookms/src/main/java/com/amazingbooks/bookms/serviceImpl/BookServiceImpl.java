package com.amazingbooks.bookms.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazingbooks.bookms.exception.BookNotFoundException;
import com.amazingbooks.bookms.model.BookDetails;
import com.amazingbooks.bookms.repository.BookRepository;
import com.amazingbooks.bookms.service.BookService;

import jakarta.validation.Valid;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	@Override
	public ResponseEntity<List<BookDetails>> getAllBooks() {
		List<BookDetails> bookList = (List<BookDetails>) bookRepo.findAll();
		System.out.println(bookList);
		return new ResponseEntity<>(bookList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookDetails> getBookById(int id) {
		BookDetails book = bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException("Book does not exists"));
		return new ResponseEntity<>(book,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateBookDetails(@Valid BookDetails book) {
		BookDetails bookDetails = bookRepo.findById(book.getIsbn()).orElseThrow(()-> new BookNotFoundException("Book does not exists"));
		bookRepo.save(book);
		return new ResponseEntity<>("Book details updated successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteBook(int id) {
		BookDetails bookDetails = bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException("Book does not exists"));
		bookRepo.delete(bookDetails);
		return new ResponseEntity<>("Book deleted successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addNewBook(@Valid BookDetails book) {
		book.setPublishedDate(new Date());
		bookRepo.save(book);
		return new ResponseEntity<>("Book added successfully",HttpStatus.OK);
	}

	@Override
	public BookDetails getBookByNameandAuthor(String name, String author) {
		// TODO Auto-generated method stub
		BookDetails bookDetails = bookRepo.findByTitleAndAuthor(name, author).orElseThrow(()-> new BookNotFoundException("Book does not exists"));
		return bookDetails;
	}

}
