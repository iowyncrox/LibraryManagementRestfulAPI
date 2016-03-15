package io.spark.librarymanagement.model.book;

import java.util.List;

import io.spark.librarymanagement.dao.userdao.UserDaoImpl;
import io.spark.librarymanagement.model.book.author.Author;
import io.spark.librarymanagement.model.user.User;

/**
 * helper class to validate {@link Book} fields
 *
 * @author sunny
 */
public class BookArgumentValidator {

	/**
	 * check if author has a name
	 *
	 * @param author
	 * @return true if author contains a valid name, false otherwise
	 * @throws BookException
	 *             if an invalid argument is passed
	 */
	public boolean isValidAuthors(List<Author> author) throws BookException {
		if (author == null) {
			throw new BookException("Author has to have a name");
		}
		for (final Author element : author) {
			if (element.getName() == null) {
				throw new BookException("Author has to have a name");
			}
		}
		return true;
	}

	/**
	 * check if book has been checked out by a {@link User}
	 *
	 * @param id
	 *            {@link User} id
	 * @return true if a valid {@link User} id, false otherwise
	 * @throws BookException
	 *             if an invalid argument is passed
	 */
	public boolean isValidCheckedOutBy(int id) throws BookException {
		if (!(new UserDaoImpl().getUserById(id) != null)) {
			throw new BookException("User does not exist");
		}
		return true;
	}

	/**
	 * check if book has a valid name
	 *
	 * @param name
	 * @return true if a book has a valid name, false otherwise
	 * @throws BookException
	 *             if an invalid argument is passed
	 */
	public boolean isValidName(String name) throws BookException {
		if (name == null) {
			throw new BookException("Book has to have a name");
		}
		return true;
	}
}