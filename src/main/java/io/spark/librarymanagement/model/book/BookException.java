package io.spark.librarymanagement.model.book;

/**
 * custom exception for {@link Book} to throw invalid arguments
 *
 * @author sunny
 */
public class BookException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exception;

	public BookException(String exception) {
		this.setException(exception);
	}

	/**
	 * getter method
	 */
	public String getException() {
		return this.exception;
	}

	/**
	 * @return custom exception message
	 */
	@Override
	public String getMessage() {
		return this.exception;
	}

	/*
	 * setter method
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
}