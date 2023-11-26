package com.book.library.issuerms.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.library.issuerms.model.BookDetails;

@FeignClient(value = "BookService", url = "http://localhost:8090")
public interface BookFeignClient {

    @GetMapping(value = "/api/v1/book/get/{name}/and/{author}", produces = "application/json")
    BookDetails getBookByNameandAuthor(@PathVariable("name") String name, @PathVariable("author") String author);
    
    @GetMapping(value="/api/v1/book/get/id/{id}")
	public ResponseEntity<BookDetails> getBookById(@PathVariable("id") int id);
    
    @PutMapping(value="/api/v1/book/update/details")
	public ResponseEntity<String> updateBookDetails(@RequestBody BookDetails bookDetails);
}
