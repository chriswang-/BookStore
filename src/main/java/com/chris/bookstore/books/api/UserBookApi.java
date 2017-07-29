/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chris.bookstore.books.business.BookService;
import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.common.RestMessage;

/**
 * @author chris
 *
 */


@RestController
@RequestMapping("/api/books/user")

public class UserBookApi {
	
	@Autowired	
	BookService bookService;

	
	/**
	 * Usage for buying books.
	 * @return
	 */
	@RequestMapping(value="buy", method = RequestMethod.GET)
	public ResponseEntity<RestMessage> buyBooksGet() {
		
		// TODO: Not test here, because the project won't use this API. 

		return new ResponseEntity<>( new RestMessage(RestMessage.OK, "Usage: POST method, for buying book purpose." ), HttpStatus.OK);
	}
	
	
	/**
	 * Usage for buying books.
	 * @return
	 */
	@RequestMapping(value="buy", method = RequestMethod.POST)
	public ResponseEntity<RestMessage> buyBooksPost(@RequestBody Book[] books) {
		
		// TODO: Not test here, because the project won't use this API. 
		
		int[] rs = bookService.buy(books);

		return new ResponseEntity<>( new RestMessage(RestMessage.OK, "Usage: POST method, for buying book purpose." ,rs), HttpStatus.OK);
	}
	
}
