/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.persistent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chris
 *
 */

@Entity
@Table(name = "books")
public class Book implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="id")
	private Long id;
	
	@Column(name = "title")
	@NotNull
	@Size(min=2, max=30)
	private String title;
	
	@Column(name = "author")
	@NotNull
	@Size(min=2, max=30)
	private String author;
	
	@Column(name = "price")
	@Digits(integer=10, fraction=2)
	@NotNull
	@DecimalMax("50000000.00")
	@DecimalMin("1.00")
	private BigDecimal price;
	
	@Column(name = "quantity")
	@NotNull
	@Digits(integer=6, fraction=0)
	private Integer quantity = 0 ;

	
	
	public Book() {
		
	}
	public Book(String title, String author, BigDecimal price, Integer quantity) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	/**
	 * @return the id
	 */
	
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


}
