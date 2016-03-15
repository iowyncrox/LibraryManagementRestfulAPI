package io.spark.librarymanagement.model.user.gender;

/**
 * gender to contain M or F
 *
 * @author sunny
 */
public enum Gender {
	M("M"), F("F");

	private String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return this.gender;
	}
}
