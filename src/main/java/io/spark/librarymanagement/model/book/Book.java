package io.spark.librarymanagement.model.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.spark.librarymanagement.model.Validable;
import io.spark.librarymanagement.model.book.author.Author;

public class Book implements Validable {

	/**
	 * builder method to instantiate {@link Book} class
	 *
	 * @author sunny
	 */
	public static class Builder {
		private String name;
		private List<Author> authors;

		public Builder authors(List<Author> authors) {
			this.authors = authors;
			return this;
		}

		public Book build() {
			return new Book(this);
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
	}

	private int id = SingletonBookId.getId();
	private String name;
	private List<Author> authors;
	private int checkedOutBy;

	private Book(Builder builder) {
		this.name = builder.name;
		this.authors = builder.authors;
	}

	/**
	 * getter methods
	 */
	public List<Author> getAuthor() {
		return this.authors;
	}

	public int getCheckedOutBy() {
		return this.checkedOutBy;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * check if mandatory instance variables is initialized to valid values
	 */
	@Override
	public boolean isValid() throws BookException {
		final BookArgumentValidator bookArgumentValidator = new BookArgumentValidator();
		return bookArgumentValidator.isValidName(this.name) && bookArgumentValidator.isValidAuthors(this.authors);
	}

	/**
	 * setter methods
	 */
	public void setAuthor(Author[] authors) {
		this.authors = new ArrayList<Author>(Arrays.asList(authors));
	}

	public void setCheckedOutBy(int checkedOutBy) throws BookException {
		final BookArgumentValidator bookArgumentValidator = new BookArgumentValidator();
		bookArgumentValidator.isValidCheckedOutBy(checkedOutBy);
		this.checkedOutBy = checkedOutBy;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		Objects.requireNonNull(name);
		this.name = name;
	}
}