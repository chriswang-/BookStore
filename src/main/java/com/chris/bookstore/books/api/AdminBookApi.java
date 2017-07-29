/**
 * Chris Book Store.
 */

package com.chris.bookstore.books.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.assertj.core.internal.BigDecimals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chris.bookstore.books.business.BookService;
import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.common.RestMessage;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * @author chris
 *
 */

@RestController
@RequestMapping("/api/books/admin")
public class AdminBookApi {
	
	@Autowired	
	BookService bookService;

	
	
	/**
	 * Usage for adding a book.
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.GET)
	public ResponseEntity<RestMessage> addBookGet() {
		
		return new ResponseEntity<>( new RestMessage(RestMessage.USAGE, "Usage: POST method, for adding book purpose." ), HttpStatus.OK);
	}
	
	/**
	 * Do add a book
	 * @param book
	 * @return
	 */
	@RequestMapping(value="add", method = RequestMethod.POST)
	public ResponseEntity<RestMessage> addBookPost(@RequestBody Book book) {
		Boolean result = bookService.add(book, book.getQuantity());
		
		Book dbBook = bookService.getByTitle(book.getTitle());
		
		if(result) {
			return new ResponseEntity<>(new RestMessage(result.toString(), "Book has been added, book's quantity will been accumulated if it'is exist aleady.", dbBook), HttpStatus.OK); 
		}else {
			return new ResponseEntity<>(new RestMessage(result.toString(), "Book didn't be added, please check the book you given."), HttpStatus.OK); 
		}
	}
	
	
	
	/**
	 * init usage method.
	 * @return
	 */
	@RequestMapping(value="init", method = RequestMethod.GET)
	public ResponseEntity<RestMessage> initGet() {
		return new ResponseEntity<>(new RestMessage(RestMessage.USAGE, "POST method, You should provide the param 'splitter' & 'url' as json format."), HttpStatus.OK); 

	}

	
	
	/**
	 * init 
	 * @param books  //https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt
	 * @return
	 */
	@RequestMapping(value="init", method = RequestMethod.POST)
	public ResponseEntity<RestMessage> initPost(@RequestBody Map<String,String> param) {
		
		String splitter = param.get("splitter");
		String url = param.get("url");
		
		if(null == splitter || null == url) {
			return new ResponseEntity<>(new RestMessage("Error", "You should provide the param 'splitter' & 'url' as json format."), HttpStatus.OK); 
		}
		
		//records bad book infos.
		List<String> badBook = new ArrayList<String>();
		
		//Good book count
		Integer goodBookCounter = 0;
		
	    URL urlObj;
	    Book eachBook = null;
		try {
			urlObj = new URL(url);
	        URLConnection yc = urlObj.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	        yc.getInputStream()));
	        String inputLine = new String();
	        while ((inputLine = in.readLine()) != null) {
	            System.out.println(inputLine);
	            StringBuffer errBuf = new StringBuffer();
	            eachBook = makeBookFromUrl(inputLine,splitter,errBuf);
	            Boolean r = bookService.add(eachBook, eachBook.getQuantity());
	            if(!r) {
	            	badBook.add("Reject by service, line item: " + inputLine + errBuf.toString());
	            }else {
	            	goodBookCounter ++; 
	            }
	            
	        }
	        in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestMessage("Error", "Url you given is bad."), HttpStatus.OK); 

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestMessage("Error", "Network error. we stuck at book:",eachBook), HttpStatus.OK); 
		} catch(Throwable t) {
			return new ResponseEntity<>(new RestMessage("Error", "Some error occur in conent. we stuck at book:"+t.getMessage(), eachBook), HttpStatus.OK); 

		}

		return new ResponseEntity<>(new RestMessage("Ok", "Yes, we've loaded: "+ goodBookCounter + " books, The unqualified bad books are recjected as below: ", badBook), HttpStatus.OK); 
	}
	
	
	private Book makeBookFromUrl(String line,String splitter, StringBuffer errorInfo) {
		Book book = new Book();
		try {
			Iterable<String> itor = Splitter.on(splitter).split(line);
			String[] list = Iterables.toArray(itor, String.class);
			
			if(list.length != 4) return null;
			
			book.setTitle(list[0]);
			book.setAuthor(list[1]);
			book.setPrice(new BigDecimal(list[2].replaceAll(",", "")));
			book.setQuantity(Ints.tryParse(list[3]));
		}
		catch(Throwable t) {
			errorInfo.append(" | Book transformation error: ").append(t.getMessage()+", "+t.getClass().getSimpleName());
			return book;
		}
		return book;
	}
	
	
	
}















