/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chris.bookstore.books.business.BookService;
import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.books.persistent.BookState;
import com.google.common.collect.Iterables;


/**
 * @author chris
 *
 */


@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired	
	BookService bookService;

	
	@RequestMapping(value = {"/list/{search}", "/list" }) 
	public String list(@ModelAttribute BooksForm booksForm, @PathVariable Optional<String> search, Model model) {
		Book[] books = null;
		if (search.isPresent()) {
			books = bookService.list(search.get());
		}else {
			books = bookService.list(null);
		}
		booksForm.setBooks(beanTransform(books));		
		
		model.addAttribute("booksForm", booksForm);
	    return "books";
	}
	
	

	@RequestMapping(value = {"/user/buy" }) 
	public String buy(@ModelAttribute BooksForm booksForm,  Model model) {
		
		// to buy
		BookForm[] pickOutBooks = pickOutSelectedBooks(booksForm.getBooks());
		int[] buyingStatus = bookService.buy(pickOutBooks);
		Boolean errExist = this.errorExist(buyingStatus);

		//handling returns.
		int i = 0;
		for(BookForm b : pickOutBooks) {
			b.setStatus(BookState.to(buyingStatus[i++])); 
			BookForm boo = getInArray(b.getId(), booksForm.getBooks());
			boo.setStatus(b.getStatus());
			if(!errExist)
				boo.setQuantity(boo.getQuantity() - 1);
		}
		
		model.addAttribute("booksForm", booksForm);
		model.addAttribute("showMeTheStatus", true);
		model.addAttribute("errExist", errExist);

	    return "books";
	}
	
	
	private BookForm[] beanTransform(Book[] books) {
		BookForm[] rebook = new BookForm[books.length];
		int i = 0;
		for(Book b : books) {
			BookForm bf = new BookForm(); 
			BeanUtils.copyProperties(b, bf);
			rebook[i++] = bf;
		}
		return rebook;
	}
	
	
	private BookForm[] pickOutSelectedBooks(BookForm[] inBooks) {
		List<BookForm> books = new ArrayList<BookForm>();
		for(BookForm bf : inBooks) {
			if(bf.getSelect()!= null && bf.getSelect()) {
//				bf.setQuantity(bf.getQuantity() - 1); 
//				bf.setSelect(false); //keep selected
				BookForm b = new BookForm(); 
				BeanUtils.copyProperties(bf, b);
				b.setQuantity(1); // only one book to buy
				books.add(b);
			}
		}
		return  Iterables.toArray( books , BookForm.class);
	}
	
	private BookForm getInArray(Long id, BookForm[] collBooks) {
		for(BookForm b : collBooks) {
			if(b.getId() == id) {
				return b;
			}
		}
		return null;
	}
	
	
	private Boolean errorExist(int[] buyingStatus ) {
		
		if(buyingStatus.length == 0) return null;
		
		for(int i : buyingStatus) {
			BookState sta = BookState.to(i);
			if(sta == BookState.DOES_NOT_EXIST || sta == BookState.NOT_IN_STOCK)
				return  true;
		}
		return false;
	}
	
}














