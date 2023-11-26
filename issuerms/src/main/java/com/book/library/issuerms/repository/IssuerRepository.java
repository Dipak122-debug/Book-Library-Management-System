package com.book.library.issuerms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.book.library.issuerms.model.IssuedBookDetails;

@Repository
public interface IssuerRepository extends CrudRepository<IssuedBookDetails, Integer>{

}