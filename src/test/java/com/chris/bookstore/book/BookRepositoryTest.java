/**
 * Chris Book Store.
 */


package com.chris.bookstore.book;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.chris.bookstore.books.persistent.Book;
import com.chris.bookstore.books.persistent.BookRepository;

/**
 * @author chris
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void testFindByTitle() {
//    	public Book(String title, String author, BigDecimal price, Integer quantity) {

        entityManager.persist(new Book("Gone With wind", "Kris", new BigDecimal("1.11"), 200));
        Book book = bookRepository.findByTitle("Gone With wind");
       
        assertEquals("Gone With wind", book.getTitle());
    }
    
    
    @Test
    public void searchByString() {
//    	public Book(String title, String author, BigDecimal price, Integer quantity) {

        entityManager.persist(new Book("Gone With wind 0", "Kris 0", new BigDecimal("1.11"), 200));
        entityManager.persist(new Book("Gone With wind 1", "Kris 1", new BigDecimal("1.11"), 201));
        entityManager.persist(new Book("Gone With wind 2", "Kris 2", new BigDecimal("1.11"), 202));
        entityManager.persist(new Book("Gone With wind 3", "Kris 3", new BigDecimal("1.11"), 203));
        entityManager.persist(new Book("Gone With wind 4", "Kris 4", new BigDecimal("1.11"), 204));
        entityManager.persist(new Book("Gone With wind 5", "Kris 5", new BigDecimal("1.11"), 205));
        entityManager.persist(new Book("Gone With wind 6", "Kris 6", new BigDecimal("1.11"), 206));
        entityManager.persist(new Book("Gone With wind 7", "Kris 7", new BigDecimal("1.11"), 207));

        Book[] books = bookRepository.searchByString("Gone With");
       
        assertEquals(8, books.length);

    }    
    
    
}
    
    
    
    
    
