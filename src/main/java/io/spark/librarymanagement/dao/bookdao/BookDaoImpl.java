package io.spark.librarymanagement.dao.bookdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import io.spark.librarymanagement.dao.userdao.UserDaoImpl;
import io.spark.librarymanagement.model.book.Book;
import io.spark.librarymanagement.model.book.BookException;
import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserException;

/**
 * methods to manipulate {@link Book} objects from the database
 *
 * @author sunny
 */
public class BookDaoImpl implements BookDao {

	private static final Map<Integer, Book> BOOKDATABASE = new ConcurrentHashMap<>();

	public BookDaoImpl() {

	}

	/**
	 * create a new {@link Book} in the database
	 */
	@Override
	public Book addBook(Book book) {
		BookDaoImpl.BOOKDATABASE.put(book.getId(), book);
		return book;
	}

	/**
	 * check to see if a {@link Book} is available to be checked by the
	 * {@link User} and update the checkOutBy instance field of {@link Book} to
	 * {@link User} id
	 *
	 * @throws UserException,
	 *             BookException if the book is unavailable or if the user is
	 *             unavailable
	 */
	@Override
	public Book checkOutBook(int userId, int bookId) throws UserException, BookException {
		final UserDaoImpl userDaoImpl = new UserDaoImpl();
		final User user = userDaoImpl.getUserById(userId);
		final BookDaoImpl bookDaoImpl = new BookDaoImpl();
		final Book book = bookDaoImpl.getBookById(bookId);
		if (user == null) {
			throw new UserException("User cannot be found");
		}
		if (book == null) {
			throw new BookException("Book cannot be found");
		}
		if (!(book.getCheckedOutBy() == 0)) {
			throw new BookException("Book already checked out");
		} else {
			book.setCheckedOutBy(user.getId());
			return book;
		}
	}

	/**
	 * get all {@link Book} from the database
	 */
	@Override
	public List<Book> getAllBooks() {
		final List<Book> list = new ArrayList<>();
		for (final Entry<Integer, Book> entry : BookDaoImpl.BOOKDATABASE.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	/**
	 * get {@link Book} by id
	 */
	@Override
	public Book getBookById(int id) {
		return BookDaoImpl.BOOKDATABASE.get(id);
	}

	/**
	 * get {@link Book} by name
	 */
	@Override
	public List<Book> getBookByName(String name) {
		final List<Book> list = new ArrayList<>();
		for (final Entry<Integer, Book> entry : BookDaoImpl.BOOKDATABASE.entrySet()) {
			if (entry.getValue().getName().equals(name)) {
				list.add(entry.getValue());
			}
		}
		return list;
	}

	/**
	 * get {@link Book} checked out by the {@link User}
	 *
	 * @throws UserException,
	 *             BookException if the book is unavailable or if the user is
	 *             unavailable
	 */
	public Book getCheckedOutBook(int userId, int bookId) throws UserException, BookException {
		final UserDaoImpl userDaoImpl = new UserDaoImpl();
		final User user = userDaoImpl.getUserById(userId);
		final BookDaoImpl bookDaoImpl = new BookDaoImpl();
		final Book book = bookDaoImpl.getBookById(bookId);
		if (user == null) {
			throw new UserException("User cannot be found");
		}
		if (book == null) {
			throw new BookException("Book cannot be found");
		}
		if (book.getCheckedOutBy() != 0) {
			throw new BookException("Book already checked out");
		} else {
			return book;
		}
	}
}