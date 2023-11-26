package com.book.library.issuerms.exception;

import lombok.Data;

@Data
public class BookNotAvailableException extends RuntimeException {
	String message;
	public BookNotAvailableException(String message) {
		super(message);
	}
}
