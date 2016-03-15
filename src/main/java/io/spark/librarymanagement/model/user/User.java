package io.spark.librarymanagement.model.user;

import java.io.Serializable;
import java.util.Objects;

import io.spark.librarymanagement.model.Validable;
import io.spark.librarymanagement.model.user.gender.Gender;

public class User implements Serializable, Validable {

	/**
	 * builder pattern to instantiate {@link User} class
	 *
	 * @author sunny
	 */
	public static class Builder {
		private String firstName;
		private String lastName;
		private int age;
		private Gender gender;
		private String phone;
		private String zip;
		private String middleName;

		public Builder age(int age) {
			if (age < 0) {
				throw new IllegalArgumentException("Age cannot be negative");
			}
			this.age = age;
			return this;
		}

		public User build() {
			return new User(this);
		}

		public Builder firstName(String firstName) {
			Objects.requireNonNull(firstName);
			this.firstName = firstName.trim();
			return this;
		}

		public Builder gender(Gender gender) {
			Objects.requireNonNull(this.firstName);
			this.gender = gender;
			return this;
		}

		public Builder lastName(String lastName) {
			Objects.requireNonNull(this.firstName);
			this.lastName = lastName.trim();
			return this;
		}

		public Builder middleName(String middleName) {
			this.middleName = middleName.trim();
			return this;
		}

		public Builder phone(String phone) {
			Objects.requireNonNull(this.firstName);
			this.phone = phone.trim();
			return this;
		}

		public Builder zip(String zip) {
			this.zip = zip.trim();
			return this;
		}
	}

	private static final long serialVersionUID = 1L;
	private int id = SingletonUserId.getId();
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private Gender gender;
	private String phone;
	private String zip;

	private User(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.gender = builder.gender;
		this.phone = builder.phone;
		this.zip = builder.zip;
		this.middleName = builder.middleName;
	}

	/**
	 * getter methods
	 */
	public int getAge() {
		return this.age;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public int getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getZip() {
		return this.zip;
	}

	/**
	 * check if mandatory instance variables is initialized to valid values
	 */
	@Override
	public boolean isValid() throws UserException {
		final UserArgumentValidator userArgumentValidator = new UserArgumentValidator();
		boolean result = userArgumentValidator.isValidName(this.firstName)
				&& userArgumentValidator.isValidName(this.lastName)
				&& userArgumentValidator.isValidPhoneNumber(this.phone) && userArgumentValidator.isValidAge(this.age)
				&& userArgumentValidator.isValidGender(this.gender);
		if (this.middleName != null) {
			result = userArgumentValidator.isValidName(this.middleName);
		}
		return result;
	}

	/**
	 * setter methods
	 */
	public void setAge(int age) {
		if (age < 0) {
			throw new IllegalArgumentException("Age cannot be negative");
		}
		this.age = age;
	}

	public void setFirstName(String firstName) {
		Objects.requireNonNull(firstName);
		this.firstName = firstName.trim();
	}

	public void setGender(Gender gender) {
		Objects.requireNonNull(gender);
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		Objects.requireNonNull(lastName);
		this.lastName = lastName.trim();
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName.trim();
	}

	public void setPhone(String phone) {
		Objects.requireNonNull(phone);
		this.phone = phone.trim();
	}

	public void setZip(String zip) {
		this.zip = zip.trim();
	}
}