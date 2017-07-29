/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.controller;

import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.books.persistent.BookState;

public class BookForm extends Book{
	
	private Boolean select;
	private BookState status;
	
	
	public Boolean getSelect() {
		return select;
	}
	public void setSelect(Boolean select) {
		this.select = select;
	}
	public BookState getStatus() {
		return status;
	}
	public void setStatus(BookState status) {
		this.status = status;
	}

	
	
	
}
