/**
 * Chris Book Store.
 */


package com.chris.bookstore.books.persistent;

/**
 * @author chris
 *
 */
public enum BookState {
	
	OK(0),
	NOT_IN_STOCK(1),
	DOES_NOT_EXIST(2);
	
    private int value;

	BookState(int value) {
        this.value = value;
    }

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	public static BookState to(int v) {
		for(BookState b : BookState.values()) {
			if(b.getValue() == v)
				return b;
		}
		return OK;
	}
	
}
