package com.book.library.issuerms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuerDetails;

public interface IssuerService {

	
	public List<IssuerDetails> getAllBooksByCustID(String custID);
	
	public ResponseEntity<String> issueNewBook(IssuerDetails issuer, String isbn);
	
	public ResponseEntity<String> returnIssuedBook(IssuerDetails issuerDetails, String isbn);
	
	public ResponseEntity<String> deleteEntry(int entryId);

}
