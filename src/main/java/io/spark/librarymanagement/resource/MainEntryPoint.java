package io.spark.librarymanagement.resource;

import java.util.List;

import io.spark.librarymanagement.dao.bookdao.BookDaoImpl;
import io.spark.librarymanagement.dao.userdao.UserDaoImpl;
import io.spark.librarymanagement.model.book.Book;
import io.spark.librarymanagement.model.book.BookException;
import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserException;
import io.spark.librarymanagement.service.ErrorJsonMessage;
import io.spark.librarymanagement.service.bookservice.BookService;
import io.spark.librarymanagement.service.userservice.UserService;
import spark.Spark;

public class MainEntryPoint {

	public static void main(String[] args) {
		/**
		 * Get all Users
		 */
		Spark.get("/users", "application/json", (request, response) -> {
			response.status(200);
			response.type("application/json");
			return (new UserDaoImpl().getAllUsers());
		}, new JsonTransformer());

		/**
		 * Create a new User
		 */
		Spark.post("/users", "application/json", (request, response) -> {
			final String body = request.body();
			try {
				response.status(201);
				response.type("application/json");
				return new UserService().setUserDetails(body);
			} catch (final UserException e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage(e.getMessage());
			} catch (final Exception e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("Enter correct format JSON");
			}
		}, new JsonTransformer());

		/**
		 * Get user by ID
		 */
		Spark.get("/users/:id", "application/json", (request, response) -> {
			final String id = request.params(":id");
			final User user = new UserDaoImpl().getUserById(Integer.parseInt(id));
			if (user == null) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("ID does not exist");
			} else {
				response.status(200);
				response.type("application/json");
				return user;
			}
		}, new JsonTransformer());

		/**
		 * Update a User
		 */
		Spark.put("/users/:id", "application/json", (request, response) -> {
			final String id = request.params(":id");
			final User user = new UserService().getUserDetailsById(Integer.parseInt(id));
			if (user == null) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("No user present with the id: " + id);
			} else {
				try {
					response.status(200);
					response.type("application/json");
					return new UserService().updateUserDetails(request.body(), Integer.parseInt(id));
				} catch (final UserException e) {
					response.status(400);
					response.type("application/json");
					return new ErrorJsonMessage(e.getMessage());
				} catch (final Exception e) {
					response.status(400);
					response.type("application/json");
					return new ErrorJsonMessage("Enter correct format JSON");
				}
			}
		}, new JsonTransformer());

		/**
		 * return all response by compressing via gzip
		 */
		Spark.after((request, response) -> {
			response.header("Content-Encoding", "gzip");
		});

		/**
		 * Get all books
		 */
		Spark.get("/books", "application/json", (request, response) -> {
			response.status(200);
			response.type("application/json");
			return new BookDaoImpl().getAllBooks();
		}, new JsonTransformer());

		/**
		 * Create a new Book
		 */
		Spark.post("/books", "application/json", (request, response) -> {
			try {
				final Book book = new BookService().setBookDetails(request.body());
				response.status(201);
				response.type("application/json");
				return book;
			} catch (final BookException e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage(e.getMessage());
			} catch (final Exception e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("Enter correct format JSON");
			}
		}, new JsonTransformer());

		/**
		 * Get Book by Name: /books
		 */
		Spark.get("/books/:name", "application/json", (request, response) -> {
			final String name = request.params(":name");
			final List<Book> list = new BookService().getBookByName(name.toLowerCase());
			if (list.isEmpty()) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("No Books with the name: " + name);
			} else {
				response.status(200);
				response.type("application/json");
				return list;
			}
		}, new JsonTransformer());

		/**
		 * Book CheckOutBy User: /userid/bookid
		 */
		Spark.put("/users/:userId/:bookId", "application/json", (request, response) -> {
			try {
				final String userId = request.params(":userId");
				final String bookId = request.params(":bookId");
				response.status(200);
				response.type("application/json");
				return new BookService().checkOutBook(Integer.parseInt(userId), Integer.parseInt(bookId));
			} catch (UserException | BookException e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage(e.getMessage());
			} catch (final Exception e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("Enter correct format JSON");
			}

		}, new JsonTransformer());

		/**
		 * Get book checked out by user
		 */
		Spark.get("/users/:userId/:bookId", "application/json", (request, response) -> {
			try {
				final String userId = request.params(":userId");
				final String bookId = request.params(":bookId");
				response.status(200);
				response.type("application/json");
				return new BookService().getCheckedOutBook(Integer.parseInt(userId), Integer.parseInt(bookId));
			} catch (UserException | BookException e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage(e.getMessage());
			} catch (final Exception e) {
				response.status(400);
				response.type("application/json");
				return new ErrorJsonMessage("Enter correct format JSON");
			}
		}, new JsonTransformer());
	}
}