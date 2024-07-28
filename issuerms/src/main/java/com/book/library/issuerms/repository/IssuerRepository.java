package com.book.library.issuerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.library.issuerms.model.BookDetails;
import com.book.library.issuerms.model.IssuerDetails;

@Repository
public interface IssuerRepository extends JpaRepository<IssuerDetails, Integer>{

	List<IssuerDetails> findAllByCustID(String custID);
}