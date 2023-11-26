package com.book.library.issuerms.service;

import org.springframework.http.ResponseEntity;

import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuedBookDetails;

public interface IssuerService {

	public int isBookAvailable(BookDetails book);

	public ResponseEntity<String> issueBookToCustomer(IssuedBookDetails book);

}
