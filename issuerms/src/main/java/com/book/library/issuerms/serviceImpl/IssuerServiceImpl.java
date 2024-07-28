package com.book.library.issuerms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.book.library.issuerms.exception.BookNotAvailableException;
import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuerDetails;
import com.book.library.issuerms.repository.IssuerRepository;
import com.book.library.issuerms.service.IssuerService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import reactor.core.publisher.Mono;


@Service
public class IssuerServiceImpl implements IssuerService {

	
	@Autowired
	private IssuerRepository issuerRepo;
	

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//public List<In>
	
	@Override
	public List<IssuerDetails> getAllBooksByCustID(String custID) {
		
		List<IssuerDetails> issuerDetails = issuerRepo.findAllByCustID(custID);
		
		
		return issuerDetails;
	}
	
	
	@Override
	public ResponseEntity<String> issueNewBook(IssuerDetails issuer, String isbn) {
		 
		try {
	
			BookDetails book = webClient.get()
					.uri("/book/get/id/"+isbn)
					.retrieve()
					.bodyToMono(BookDetails.class).block();
			
			  if (book == null) {
	                return new ResponseEntity<>("Book not found: " + isbn, HttpStatus.NOT_FOUND);
	            }

			
			if(book.getTotalCopies()>book.getIssuedCopies()) {
				book.setIssuedCopies(book.getIssuedCopies()+1);
				issuer.setBookDetails(book);
				issuerRepo.save(issuer);
				webClient
					.put()
					 .uri("/book/update/details")
	                 .body(Mono.just(book), BookDetails.class)
	                 .retrieve()
	                 .toBodilessEntity()
	                 .block();

			}
			else {
				return new ResponseEntity<>("Book is not available: " + isbn, HttpStatus.BAD_REQUEST);
			 }
		}
		catch (Exception e) {
			System.out.println("Exception"+e);
			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Book issued SuccessFully",HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> returnIssuedBook(IssuerDetails issuerDetails, String isbn) {
		
		try {
			
			BookDetails book = webClient.get()
					.uri("/book/get/id/"+isbn)
					.retrieve()
					.bodyToMono(BookDetails.class).block();
			
			  if (book == null) {
	                return new ResponseEntity<>("Book not found: " + isbn, HttpStatus.NOT_FOUND);
	            }

			
				book.setIssuedCopies(book.getIssuedCopies()-1);
				issuerDetails.setBookDetails(book);
				issuerDetails.setStatus("Closed");
				issuerRepo.save(issuerDetails);
				webClient
					.put()
					 .uri("/book/update/details")
	                 .body(Mono.just(book), BookDetails.class)
	                 .retrieve()
	                 .toBodilessEntity()
	                 .block();

	
	
		
		}
		catch(Exception e){
			System.out.println("Exception"+e);
			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Returned issued Book SuccessFully",HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<String> deleteEntry(int entryId) {
			
		IssuerDetails issuer = issuerRepo.findById(entryId).get();
		issuerRepo.delete(issuer);
		return new ResponseEntity<>("delete Issuer details Entry",HttpStatus.OK);
	}

	
	
}
