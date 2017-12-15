package com.bgasparotto.springboot.veggieburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name must be set")
	@Length(min = 2,
			max = 32,
			message = "Name length must be between {min} and {max}")
	private String name;

	/**
	 * Constructor.
	 *
	 */
	public Country() {
		this(null);
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            The Country's {@code name}
	 */
	public Country(String name) {
		this(null, name);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The Country's {@code id}
	 * @param name
	 *            The Country's {@code name}
	 */
	public Country(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the Country's {@code id}.
	 *
	 * @return The Country's {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Country's {@code name}.
	 *
	 * @return The Country's {@code name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Country's {@code id}.
	 *
	 * @param id
	 *            The Country's {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Country's {@code name}.
	 *
	 * @param name
	 *            The Country's {@code name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}