package io.spark.librarymanagement.service;

/**
 * helper class to return invalid message to the wrong JSON input
 *
 * @author sunny
 */
public class ErrorJsonMessage {

	private String message;

	public ErrorJsonMessage(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
