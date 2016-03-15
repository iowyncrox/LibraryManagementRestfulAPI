package io.spark.librarymanagement.model.user;

/**
 * custom exception for {@link User} to throw invalid arguments
 *
 * @author sunny
 */
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exception;

	public UserException(String exception) {
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

	/**
	 * setter method
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
}