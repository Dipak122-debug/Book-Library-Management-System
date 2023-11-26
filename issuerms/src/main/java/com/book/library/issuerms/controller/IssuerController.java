package com.book.library.issuerms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuedBookDetails;
import com.book.library.issuerms.service.IssuerService;
import com.book.library.issuerms.utility.BookFeignClient;

@RestController
@RequestMapping("/api/v1")
public class IssuerController {
	
	@Autowired
	private BookFeignClient bookFeignClient;
	
	@Autowired
	private IssuerService issuerService;
	
	@GetMapping(value="/issuer/book/availability")
	public boolean isBookAvailable(@RequestBody BookDetails book) {
		BookDetails bookDetails = bookFeignClient.getBookByNameandAuthor(book.getTitle(), book.getAuthor());
		return bookDetails.getTotalCopies() - bookDetails.getIssuedCopies()>0?true:false;
	}
	
	@PostMapping(value="/issuer/book/issue")
	public ResponseEntity<String> issueBookToCustomer(@RequestBody IssuedBookDetails book) {
		return issuerService.issueBookToCustomer(book);
	}
}
