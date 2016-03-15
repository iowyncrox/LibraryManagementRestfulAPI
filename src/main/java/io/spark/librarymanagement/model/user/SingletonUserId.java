package io.spark.librarymanagement.model.user;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * primary key implementation to mock {@link User} id
 *
 * @author sunny
 */
public enum SingletonUserId {
	INSTANCE;

	private static final AtomicInteger id = new AtomicInteger(0);

	/**
	 * @return increments the id and returns
	 */
	public static int getId() {
		return SingletonUserId.id.incrementAndGet();
	}

	/**
	 * @return returns the instance
	 */
	public static SingletonUserId getInstance() {
		return INSTANCE;
	}

	SingletonUserId() {

	}
}