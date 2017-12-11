package com.bgasparotto.springboot.veggieburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(min = 2,
			max = 30,
			message = "Item's name length must be between {min} and {max}")
	private String name;

	@NotNull
	@Min(value = 1,
			message = "Item's price must be equal or greater than {value}")
	private Double price;

	/**
	 * Gets the Item's {@code id}.
	 *
	 * @return The Item's {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Item's {@code name}.
	 *
	 * @return The Item's {@code name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the Item's {@code price}.
	 *
	 * @return The Item's {@code price}
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the Item's {@code id}.
	 *
	 * @param id
	 *            The Item's {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Item's {@code name}.
	 *
	 * @param name
	 *            The Item's {@code name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the Item's {@code price}.
	 *
	 * @param price
	 *            The Item's {@code price} to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
}