package io.spark.librarymanagement.model.book.author;

import io.spark.librarymanagement.model.book.Book;

/**
 * creates the name of the author for {@link Book}
 *
 * @author sunny
 */
public class Author {

	private String name;

	public Author(String name) {
		this.setName(name);
	}

	/**
	 * getter method
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter method
	 */
	public void setName(String name) {
		this.name = name;
	}

}