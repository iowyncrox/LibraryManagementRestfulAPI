package io.spark.librarymanagement.service.userservice;

import io.spark.librarymanagement.dao.userdao.UserDaoImpl;
import io.spark.librarymanagement.model.user.SingletonUserId;
import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserException;
import io.spark.librarymanagement.resource.JsonTransformer;

/**
 * Service class to help in manipulation of {@link User}
 *
 * @author sunny
 */
public class UserService {

	public UserService() {

	}

	/**
	 * get user details by {@link User} id
	 *
	 * @param id
	 *            {@link User} id
	 * @return {@link User} found by the matching id
	 */
	public User getUserDetailsById(int id) {
		return new UserDaoImpl().getUserById(id);
	}

	/**
	 * set a JSON send by the client to the {@link User}
	 *
	 * @param body
	 *            JSON
	 * @return {@link User} object
	 * @throws UserException
	 *             if the JSON contains invalid arguments
	 */
	public User setUserDetails(String body) throws UserException {
		final User user = JsonTransformer.fromJson(body, User.class);
		user.isValid();
		user.setId(SingletonUserId.getId());
		final UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.createUser(user);
		return userDaoImpl.getUserById(user.getId());
	}

	/**
	 * update a {@link User} to match the JSON
	 *
	 * @param body
	 *            JSON string body
	 * @param id
	 *            {@link User} id
	 * @return updated {@link User}
	 * @throws UserException
	 *             if the JSON contains invalid arguments
	 */
	public User updateUserDetails(String body, int id) throws UserException {
		final User user = JsonTransformer.fromJson(body, User.class);
		return new UserDaoImpl().updateUserDetails(user, id);
	}
}