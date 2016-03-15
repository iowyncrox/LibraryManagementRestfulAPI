package io.spark.librarymanagement.model;

import io.spark.librarymanagement.model.book.Book;
import io.spark.librarymanagement.model.user.User;

/**
 * interface to validate parameters passed to the {@link Book} and {@link User}
 *
 * @author sunny
 */
public interface Validable {
	boolean isValid() throws Exception;
}
