/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.controller;

import java.math.BigDecimal;

import com.chris.bookstore.books.persistent.Book;

public class BooksForm {
	
	private BigDecimal totalAmount;
	
	private BookForm[] books;
	
	public BooksForm() {
		totalAmount = new BigDecimal(0);
	}

	public BookForm[] getBooks() {
		return books;
	}

	public void setBooks(BookForm[] books) {
		this.books = books;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	
	
	
	
}
