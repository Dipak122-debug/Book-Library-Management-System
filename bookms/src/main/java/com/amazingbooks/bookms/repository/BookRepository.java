package com.amazingbooks.bookms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amazingbooks.bookms.model.BookDetails;

@Repository
public interface BookRepository extends CrudRepository<BookDetails, Integer>{

	public Optional<BookDetails> findByTitleAndAuthor(String title, String author);
}
