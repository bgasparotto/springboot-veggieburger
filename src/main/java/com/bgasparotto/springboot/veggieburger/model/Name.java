package com.bgasparotto.springboot.veggieburger.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Entity that represents a individual's name.
 * 
 * @author Bruno Gasparotto
 *
 */
@Embeddable
public class Name {

	@Size(min = 2, 
		  max = 32,
		  message = "First name size must be between {min} and {max}")
	private String firstName;
	
	@Size(max = 32, message = "Maximum last name size is {max}")
	private String lastName;

	/**
	 * Constructor.
	 *
	 */
	public Name() {
		this(null, null);
	}

	/**
	 * 
	 * Constructor.
	 *
	 * @param firstName
	 *            The first name
	 * @param lastName
	 *            The last name
	 */
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets the Name's {@code firstName}.
	 *
	 * @return the {@code Name}'s {@code firstName}
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the Name's {@code lastName}.
	 *
	 * @return the {@code Name}'s {@code lastName}
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Name's {@code firstName}.
	 *
	 * @param firstName
	 *            the {@code Name}'s {@code firstName} to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the Name's {@code lastName}.
	 *
	 * @param lastName
	 *            the {@code Name}'s {@code lastName} to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}
}