package io.spark.librarymanagement.dao.bookdao;

import java.util.List;

import io.spark.librarymanagement.model.book.Book;
import io.spark.librarymanagement.model.book.BookException;
import io.spark.librarymanagement.model.user.UserException;

/**
 * interface to provide implementation details for {@link BookDaoImpl}
 */
public interface BookDao {
	Book addBook(Book book);

	Book checkOutBook(int userId, int bookId) throws UserException, BookException;

	List<Book> getAllBooks();

	Book getBookById(int id);

	List<Book> getBookByName(String name);
}