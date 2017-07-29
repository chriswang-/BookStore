/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.business;

import com.chris.bookstore.books.persistent.Book;

/**
 * @author chris
 *
 */
public interface BookList {
	
	  public Book[] list(String searchString);
	  
	  public boolean add(Book book, int quantity);
	  
	  public int[] buy(Book... books);
	  
}
