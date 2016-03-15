package io.spark.librarymanagement.dao.userdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import io.spark.librarymanagement.model.user.User;
import io.spark.librarymanagement.model.user.UserArgumentValidator;
import io.spark.librarymanagement.model.user.UserException;
import io.spark.librarymanagement.model.user.gender.Gender;

/**
 * methods to manipulate {@link User} objects from the database
 *
 * @author sunny
 */
public class UserDaoImpl implements UserDao {

	private static final Map<Integer, User> USERDATABASE = new ConcurrentHashMap<>();

	public UserDaoImpl() {

	}

	/**
	 * create a new {@link User} in the database
	 */
	@Override
	public User createUser(User user) {
		UserDaoImpl.USERDATABASE.put(user.getId(), user);
		return user;
	}

	/**
	 * get all {@link User} from the database
	 */
	@Override
	public List<User> getAllUsers() {
		final List<User> list = new ArrayList<>();
		for (final Entry<Integer, User> entry : UserDaoImpl.USERDATABASE.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	/**
	 * get {@link User} by id from the database
	 */
	@Override
	public User getUserById(int id) {
		return UserDaoImpl.USERDATABASE.get(id);
	}

	/**
	 * update an existing {@link User} in the database
	 */
	@Override
	public User updateUserDetails(User user, int id) throws UserException {
		final User updateUser = new UserDaoImpl().getUserById(id);
		final String firstName = user.getFirstName();
		final String middleName = user.getMiddleName();
		final String lastName = user.getLastName();
		final int age = user.getAge();
		final Gender gender = user.getGender();
		final String phone = user.getPhone();
		final String zip = user.getZip();

		final UserArgumentValidator userArgumentValidator = new UserArgumentValidator();

		if (firstName != null) {
			userArgumentValidator.isValidName(firstName);
			updateUser.setFirstName(firstName);
		}

		if (middleName != null) {
			userArgumentValidator.isValidName(middleName);
			updateUser.setMiddleName(middleName);
		}

		if (lastName != null) {
			userArgumentValidator.isValidName(middleName);
			updateUser.setLastName(lastName);
		}

		if (age != 0) {
			userArgumentValidator.isValidAge(age);
			updateUser.setAge(age);
		}

		if (gender != null) {
			userArgumentValidator.isValidGender(gender);
			updateUser.setGender(gender);
		}

		if (phone != null) {
			userArgumentValidator.isValidPhoneNumber(phone);
			updateUser.setPhone(phone);
		}

		if (zip != null) {
			updateUser.setZip(zip);
		}

		return new UserDaoImpl().getUserById(id);
	}
}