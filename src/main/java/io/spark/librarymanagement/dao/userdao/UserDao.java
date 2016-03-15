package io.spark.librarymanagement.dao.userdao;

import java.util.List;

import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserException;

/**
 * interface to provide implementation details for {@link UserDaoImpl}
 */
public interface UserDao {
	User createUser(User user);

	List<User> getAllUsers();

	User getUserById(int id);

	User updateUserDetails(User user, int id) throws UserException;
}