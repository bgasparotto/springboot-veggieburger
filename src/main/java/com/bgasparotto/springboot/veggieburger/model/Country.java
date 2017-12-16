package com.bgasparotto.springboot.veggieburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Entity that represents a country of the system.
 * 
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 2,
		  max = 2,
		  message = "Country code size must be 2")
	private String code;

	@Size(min = 2,
		  max = 64,
		  message = "Country name size must be between {min} and {max}")
	private String name;

	/**
	 * Constructor.
	 *
	 */
	public Country() {
		this(null, null, null);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The country's {@code id}
	 * @param code
	 *            The country's {@code code}
	 * @param name
	 *            The country's {@code name}
	 */
	public Country(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	/**
	 * Gets the Country's {@code id}.
	 *
	 * @return the {@code Country}'s {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Country's {@code code}.
	 *
	 * @return the {@code Country}'s {@code code}
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the Country's {@code name}.
	 *
	 * @return the {@code Country}'s {@code name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Country's {@code id}.
	 *
	 * @param id
	 *            the {@code Country}'s {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Country's {@code code}.
	 *
	 * @param code
	 *            the {@code Country}'s {@code code} to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets the Country's {@code name}.
	 *
	 * @param name
	 *            the {@code Country}'s {@code name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}