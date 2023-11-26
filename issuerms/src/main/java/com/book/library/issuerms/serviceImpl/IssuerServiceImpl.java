package com.book.library.issuerms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.library.issuerms.exception.BookNotAvailableException;
import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuedBookDetails;
import com.book.library.issuerms.repository.IssuerRepository;
import com.book.library.issuerms.service.IssuerService;
import com.book.library.issuerms.utility.BookFeignClient;

@Service
public class IssuerServiceImpl implements IssuerService {

	@Autowired
	private BookFeignClient bookFeignClient;
	
	@Autowired
	private IssuerRepository issuerRepo;
	
	@Override
	public int isBookAvailable(BookDetails book) {
		BookDetails bookDetails = bookFeignClient.getBookByNameandAuthor(book.getTitle(), book.getAuthor());
		return bookDetails.getTotalCopies() - bookDetails.getIssuedCopies();
	}

	@Override
	public ResponseEntity<String> issueBookToCustomer(IssuedBookDetails book) {
		BookDetails bookDetails = bookFeignClient.getBookById(book.getIsbn()).getBody();
		if(isBookAvailable(bookDetails)>=book.getNoOfCopies()) {
			issuerRepo.save(book);
			bookDetails.setIssuedCopies(bookDetails.getIssuedCopies()+book.getNoOfCopies());
			bookFeignClient.updateBookDetails(bookDetails);
			return new ResponseEntity<>("Book issued to customer successfully",HttpStatus.OK);
		}
		else {
			throw new BookNotAvailableException("Required Book Copies Not Available");
					
		}
	}

}
