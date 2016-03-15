package io.spark.librarymanagement.service.bookservice;

import java.util.List;

import io.spark.librarymanagement.dao.bookdao.BookDaoImpl;
import io.spark.librarymanagement.model.book.Book;
import io.spark.librarymanagement.model.book.BookException;
import io.spark.librarymanagement.model.book.SingletonBookId;
import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserException;
import io.spark.librarymanagement.resource.JsonTransformer;

/**
 * service class to help in the manipulation of {@link Book}
 *
 * @author sunny
 */
public class BookService {

	/**
	 * check to see if a {@link Book} is available to be checked by the
	 * {@link User} and update the checkOutBy instance field of {@link Book} to
	 * {@link User} id
	 *
	 * @param userId
	 * @param bookId
	 * @return
	 * @throws UserException
	 * @throws BookException
	 */
	public Book checkOutBook(int userId, int bookId) throws UserException, BookException {
		return new BookDaoImpl().checkOutBook(userId, bookId);
	}

	/**
	 * get {@link Book} by name
	 */
	public List<Book> getBookByName(String name) {
		return new BookDaoImpl().getBookByName(name);
	}

	/**
	 * get {@link Book} checked out by the {@link User}
	 *
	 * @param userId
	 * @param bookId
	 * @throws UserException
	 *             if the user is invalid
	 * @throws BookException
	 *             if the book is invalid
	 */
	public Book getCheckedOutBook(int userId, int bookId) throws UserException, BookException {
		return new BookDaoImpl().getCheckedOutBook(userId, bookId);
	}

	/**
	 * get {@link Book} checked by the {@link User}
	 *
	 * @param body
	 *            JSON body sent by the client
	 * @return updated details of the book
	 * @throws BookException
	 *             if the book JSON is invalid
	 */
	public Book setBookDetails(String body) throws BookException {
		final Book book = JsonTransformer.fromJson(body, Book.class);
		book.isValid();
		book.setId(SingletonBookId.getId());
		final BookDaoImpl bookDaoImpl = new BookDaoImpl();
		bookDaoImpl.addBook(book);
		return bookDaoImpl.getBookById(book.getId());
	}
}