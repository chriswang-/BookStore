/**
 * Chris Book Store.
 */


package com.chris.bookstore.book;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.chris.bookstore.books.business.BookService;
import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.books.persistent.BookRepository;
import com.chris.bookstore.books.persistent.BookState;
import com.google.common.collect.Lists;

/**
 * @author chris
 *
 */

@RunWith(SpringRunner.class)
public class BookServiceTest {
	 
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }
    
    /**
     * Prepare the fake data. a mock Book Repository.
     */
    @Before
    public void setUp() {
    	
    	Book book0 = new Book("Gone Without wind 0", "Kris 0", new BigDecimal("1.11"), 200);   
    	Book book1 = new Book("Gone Without wind 1", "Kris 1", new BigDecimal("1.11"), 201);    
    	Book book2 = new Book("Gone With wind 2", "Kris 2", new BigDecimal("1.11"), 202);   
    	Book book3 = new Book("Gone With wind 3", "Kris 3", new BigDecimal("1.11"), 203);
    	
//    	Book book4 = new Book("Gone With wind 4", "Kris 4", new BigDecimal("1.11"), 204);    

    	
    	Book[] books = Arrays.array(book0, book1);
    	List<Book> books1 = Lists.newArrayList(book0, book1, book2, book3);
    	
    	//Mocking searchByString
        Mockito.when(bookRepository.searchByString("Gone Without"))
          .thenReturn(books);
    	
        //Mocking findAll
        Mockito.when(bookRepository.findAll())
        .thenReturn(books1);
        
        //Mocking findByTitle
        Mockito.when(bookRepository.findByTitle("Gone Without wind 0"))
        .thenReturn(book0);
        
        //Mocking find one .
        Mockito.when(bookRepository.findOne(0l))
        .thenReturn(book0); 
        Mockito.when(bookRepository.findOne(1l))
        .thenReturn(book0); 
        Mockito.when(bookRepository.findOne(2l))
        .thenReturn(book0); 
        Mockito.when(bookRepository.findOne(3l))
        .thenReturn(book0); 

        
        
    }
    
 
    @Autowired
    private BookService bookService;
 
    @MockBean
    private BookRepository bookRepository;

    
    @Test
    public void listServiceTest() {
    	
    	// testing list all
    	Book[] books = bookService.list(null);
    	
    	// testing list by search string, that will find in Title&Author fields in the database.
    	Book[] books1 = bookService.list("Gone Without");    	
    	
        assertEquals(4, books.length);
        assertEquals(2, books1.length);           

    }
    
    
    @Test
    public void addServiceTest() {    	
    	
    	//Bad Bean.
    	Book bookError = new Book(null,null,null,200);
    	Book bookError1 = new Book("Gone Without Error wind 0", "Kris 0", new BigDecimal("1.111111111111111111111111111111"), 200);
    	
    	//Good Bean.
    	Book book0 = new Book("Gone Without wind 0", "Kris 0", new BigDecimal("1.11"), 200);
    	Book book1000 = new Book("Gone Without wind 1000", "Kris 1000", new BigDecimal("1.11"), 20000);
    	
    	Boolean rebookError = bookService.add(bookError, 1000);
    	Boolean rebookError1 = bookService.add(bookError1, 1000);

    	Boolean rebook0 = bookService.add(book0, 1000);
    	Boolean rebook1000 =  bookService.add(book1000, 1000);

        assertEquals(false, rebookError);
        assertEquals(false, rebookError1);

        assertEquals(true, rebook0);    
        assertEquals(true, rebook1000);
        
    }
    
    @Test
    public void buyServiceTest() {
    	
    	
    	Book buyingBook0 = new Book("Gone Without wind 0", "Kris 0", new BigDecimal("1.11"), 3);   
    	Book buyingBook1 = new Book("Gone Without wind 1", "Kris 1", new BigDecimal("1.11"), 6);    
    	Book buyingBook2 = new Book("Gone Without wind 2", "Kris 2", new BigDecimal("1.11"), 20000000);    
    	Book buyingBook3 = new Book("Lord Rings book", "Kris 3", new BigDecimal("1.11"), 2);    

    	
    	// Should buy successfully.
    	buyingBook0.setId(0l); //ID is mandatory if buying a book.
    	buyingBook1.setId(1l);
    	int[] ints = bookService.buy(buyingBook0, buyingBook1);
        assertArrayEquals(new int[]{BookState.OK.getValue(),BookState.OK.getValue()},ints);
        
        //So many books to buy, the quantity is insufficient.
        buyingBook2.setId(2l);
    	int[] intss = bookService.buy(buyingBook2);
        assertArrayEquals(new int[]{BookState.NOT_IN_STOCK.getValue()},intss);
        
        //The book does not exist in the store.(ID is null or ID does not exist)
    	int[] intsss = bookService.buy(buyingBook3);
        assertArrayEquals(new int[]{BookState.DOES_NOT_EXIST.getValue()},intsss);
        
    }
    
    
}








