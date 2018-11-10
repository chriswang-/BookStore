/**
 * Chris Book Store.
 */

package com.chris.bookstore.books.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.books.persistent.BookRepository;
import com.chris.bookstore.books.persistent.BookState;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
 * @author chris
 *
 */

@Service
public class BookService implements BookList {

	@Autowired
	private BookRepository bookRepository;

	
	
	public Book get(Long id) {
		
		if(null == id) 
			return null;
		
		return bookRepository.findOne(id);
	}
	
	public Book getByTitle(String title) {
		
		if(null == title) 
			return null;
		
		return bookRepository.findByTitle(title);
	}
	
	
	
	
	/*
	 * @see com.chris.bookstore.books.BookList#list(java.lang.String)
	 */
	@Override
	public Book[] list(String searchString) {

		if (null == searchString) {
			return Iterables.toArray(bookRepository.findAll(), Book.class);
		} else {
			return bookRepository.searchByString(searchString);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chris.bookstore.books.BookList#add(com.chris.bookstore.books.Book,
	 * int)
	 */
	@Override
	@Transactional
	public boolean add(Book book, int quantity) {
		
		if(null == book)
			return false;
		
		//validating a bean.
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Book>> set = validator.validate(book);
		for (ConstraintViolation<Book> constraintViolation : set) {
			return false;
		}
		

		//db book
		Book dbBook = bookRepository.findByTitle(book.getTitle());
		
		//update the quantity.
		if (null != dbBook) {
			dbBook.setQuantity(book.getQuantity() + dbBook.getQuantity());
			//persistent to  db
			bookRepository.save(dbBook);

		}else {
			//persistent to  db
			bookRepository.save(book);
		}
		
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chris.bookstore.books.BookList#buy(com.chris.bookstore.books.Book[])
	 */
	@Override
	@Transactional
	public int[] buy(Book... books) {
		
		List<Integer> result  = new ArrayList<Integer>();
		
		for(Book book : books) {
			
			//db book
			Book dbBook = bookRepository.findOne(book.getId());
			
			//the book is not present in store.
			if(null == dbBook) {
				result.add(BookState.DOES_NOT_EXIST.getValue());
				continue;
			}
			
			//the quantity in store is insufficient.																																																																																																																																																																																																																																																																																																									
			if(dbBook.getQuantity() < book.getQuantity()) {
				result.add(BookState.NOT_IN_STOCK.getValue());
				continue;
			}
			
			//update quantity.
			dbBook.setQuantity(dbBook.getQuantity() - book.getQuantity());
			bookRepository.save(dbBook);
			
			//reply the status.
			result.add(BookState.OK.getValue());

			continue;//next
		}
		
		// Need rollback programmatically if any book is failure.
		if(result.contains(BookState.NOT_IN_STOCK.getValue())  ||  result.contains(BookState.DOES_NOT_EXIST.getValue()) ) {		
			
			// To check if the transaction is present, else it will throw out a exception in Unit test case. 
			if(TransactionSynchronizationManager.isActualTransactionActive()) {
				TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
				status.setRollbackOnly();
			}
		}

		return Ints.toArray(result);
	}

}












