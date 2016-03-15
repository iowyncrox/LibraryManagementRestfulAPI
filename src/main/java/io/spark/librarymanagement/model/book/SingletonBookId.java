package io.spark.librarymanagement.model.book;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * primary key implementation to mock {@link Book} id
 *
 * @author sunny
 */
public enum SingletonBookId {

	INSTANCE;

	private static final AtomicInteger id = new AtomicInteger(0);

	/**
	 * @return increments the id and returns
	 */
	public static int getId() {
		return SingletonBookId.id.incrementAndGet();
	}

	/**
	 * @return returns the instance
	 */
	public static SingletonBookId getInstance() {
		return INSTANCE;
	}

	SingletonBookId() {

	}
}