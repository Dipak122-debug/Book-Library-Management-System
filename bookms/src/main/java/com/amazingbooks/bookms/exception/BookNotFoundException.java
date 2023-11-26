package com.amazingbooks.bookms.exception;

import lombok.Data;

@Data
public class BookNotFoundException extends RuntimeException {
	String message;
	public BookNotFoundException(String message) {
		super(message);
	}
}
