/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chris.bookstore.books.business.BookService;
import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.common.RestMessage;
import com.google.common.primitives.Longs;

/**
 * @author chris
 *
 */


@RestController
@RequestMapping("/api/books")

public class BookApi {
	
	
	@Autowired	
	BookService bookService;
	
	
	/**
	 * get book
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = {"/get/{condition}"}, method = RequestMethod.GET)
	public ResponseEntity<RestMessage> getBook(@PathVariable String condition) {
		
		Book book = null;
		boolean isNumeric = condition.chars().allMatch( Character::isDigit );
		if(isNumeric) {
			book = bookService.get(Longs.tryParse(condition));
		}else {
			book = bookService.getByTitle(condition);
		}
		
		return new ResponseEntity<>( new RestMessage(RestMessage.OK, "Result object:", book ), HttpStatus.OK);
		
	}


	
	/**
	 * Find books
	 * @param search
	 * @return
	 */
	@RequestMapping(value = {"/list/{search}", "/list" }, method = RequestMethod.GET)
	public ResponseEntity<RestMessage> listBooks(@PathVariable Optional<String> search) {
		
		Book[] books = null;		
		if (search.isPresent()) {
			books = bookService.list(search.get());
		}else {
			books = bookService.list(null);
		}
		return new ResponseEntity<>( new RestMessage(RestMessage.OK, "Result size:"+books.length, books ), HttpStatus.OK);

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
