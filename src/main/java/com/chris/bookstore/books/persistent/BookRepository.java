/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.persistent;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/**
 * @author chris
 *
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	
	@Query(
			value = "select id,title,author,price,quantity from books where title = ?1 ", 
			nativeQuery = true
			)
    public Book findByTitle(String title);
    
    /**
     * Search 4 Title & Author.
     * @param searchString
     * @return
     */
	@Query(
			value = "select id,title,author,price,quantity from books where title like %?1% or author like %?1% ", 
			nativeQuery = true
			)
    public Book[] searchByString(String searchString);

}