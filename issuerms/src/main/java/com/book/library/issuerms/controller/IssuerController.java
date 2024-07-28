package com.book.library.issuerms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuerDetails;
import com.book.library.issuerms.service.IssuerService;
import com.book.library.issuerms.utility.BookFeignClient;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class IssuerController {
	
	@Autowired
	private BookFeignClient bookFeignClient;
	
	@Autowired
	private IssuerService issuerService;
	
	
	@GetMapping(value="issuer/entries")
	public ResponseEntity<List<IssuerDetails>> retrieveIssuerEntriesByCustID(@RequestParam String custID){
		
		 List<IssuerDetails> issuerEntries = issuerService.getAllBooksByCustID(custID);
		 
		 return new ResponseEntity<List<IssuerDetails>>(issuerEntries,HttpStatus.OK);
	}
	
	@GetMapping(value="issuer/bookdetails")
	public ResponseEntity<List<BookDetails>> retrieveBookDetailsByCustID(@RequestParam String custID){
		 List<IssuerDetails> issuerEntries = issuerService.getAllBooksByCustID(custID);
		 List<BookDetails> bookList = new ArrayList<>();
			
			for( IssuerDetails entry : issuerEntries) {
				 bookList.add(entry.getBookDetails());
			}

		return new ResponseEntity<List<BookDetails>>(bookList, HttpStatus.OK);
	}
	
	@PostMapping(value="issuer/addNewBook/{isbn}")
	public ResponseEntity<String> issueNewBook(@RequestBody IssuerDetails issuer,@PathVariable String isbn){
		
		return issuerService.issueNewBook(issuer, isbn);
	}
	
	@PostMapping(value="issuer/returnBook/{isbn}")
	public ResponseEntity<String> returnIssuedBook(@RequestBody IssuerDetails issuerDetails,@PathVariable String isbn){
		
		return issuerService.returnIssuedBook(issuerDetails,isbn);
	}
	
	@DeleteMapping(value="issuer/deleteIssuer/{entryId}")
	public ResponseEntity<String> deleteEntry (@PathVariable int entryId){
		
		return issuerService.deleteEntry(entryId);
		
		
	}
}
