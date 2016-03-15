package io.spark.librarymanagement.model.user;

import org.apache.commons.lang3.StringUtils;

import io.spark.librarymanagement.model.user.gender.Gender;

/**
 * helper class to validate {@link User} arguments
 *
 * @author sunny
 */
public class UserArgumentValidator {

	/**
	 * check if age is a valid non zero positive number
	 *
	 * @param age
	 * @return true if the argument passed is a valid non zero positive number,
	 *         false otherwise
	 * @throws UserException
	 *             if an invalid argument is passed
	 */
	public boolean isValidAge(int age) throws UserException {
		if (!(age > 0)) {
			throw new UserException("Age should be valid non zero positive number");
		}
		return true;
	}

	/**
	 * check if gender is M or F
	 *
	 * @param gender
	 * @return true if the argument passed is M or F, false otherwise
	 * @throws UserException
	 *             if an invalid argument passed
	 */
	public boolean isValidGender(Gender gender) throws UserException {
		if ((gender == null) || gender.equals(Gender.M.toString()) || gender.equals(Gender.F.toString())) {
			throw new UserException("Gender should be M or F");
		}
		return true;
	}

	/**
	 * check if name contains only alphabets
	 *
	 * @param name
	 * @return true if the argument passed contains alphabets, false otherwise
	 * @throws UserException
	 *             if an invalid argument is passed
	 */
	public boolean isValidName(String name) throws UserException {
		if (!(StringUtils.isAlpha(name))) {
			throw new UserException("Name should be alphabets");
		}
		return true;
	}

	/**
	 * check if phone is a 10 digit positive number
	 *
	 * @param phone
	 * @return true if the argument passed is a 10 digit positive number
	 * @throws UserException
	 *             if an invalid argument is passed
	 */
	public boolean isValidPhoneNumber(String phone) throws UserException {
		final String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		if (!(phone.matches(regexStr))) {
			throw new UserException("Phone should be 10-digit positive number");
		}
		return true;
	}
}