package com.bgasparotto.springboot.veggieburger.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

/**
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(min = 2,
			max = 30,
			message = "Customer's name length must be between {min} and {max}")
	private String name;

	@NotNull
	@Length(min = 2,
			max = 300,
			message = "Customer's name length must be between {min} and {max}")
	private String address;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<Purchase> purchases;

	/**
	 * Gets the Customer's {@code id}.
	 *
	 * @return The Customer's {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Customer's {@code name}.
	 *
	 * @return The Customer's {@code name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the Customer's {@code address}.
	 *
	 * @return The Customer's {@code address}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets the Customer's {@code purchases}.
	 *
	 * @return The Customer's {@code purchases}
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * Sets the Customer's {@code id}.
	 *
	 * @param id
	 *            The Customer's {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Customer's {@code name}.
	 *
	 * @param name
	 *            The Customer's {@code name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the Customer's {@code address}.
	 *
	 * @param address
	 *            The Customer's {@code address} to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the Customer's {@code purchases}.
	 *
	 * @param purchases
	 *            The Customer's {@code purchases} to set
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", purchases=");
		builder.append(purchases);
		builder.append("]");
		return builder.toString();
	}
}