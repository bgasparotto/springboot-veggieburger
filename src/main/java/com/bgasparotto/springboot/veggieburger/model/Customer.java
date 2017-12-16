package com.bgasparotto.springboot.veggieburger.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

/**
 * Entity that represents a customer of the system.
 * 
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@Embedded
	private Name name;

	@NotNull
	@Length(min = 2,
			max = 300,
			message = "Address length must be between {min} and {max}")
	private String address;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Set<Purchase> purchases;

	/**
	 * Constructor.
	 *
	 */
	public Customer() {
		this(null, null);
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            The Customer's {@code name}
	 * @param address
	 *            The Customer's {@code address}
	 */
	public Customer(Name name, String address) {
		this(null, name, address);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The Customer's {@code id}
	 * @param name
	 *            The Customer's {@code name}
	 * @param address
	 *            The Customer's {@code address}
	 */
	public Customer(Long id, Name name, String address) {
		this(id, name, address, new HashSet<Purchase>());
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The Customer's {@code id}
	 * @param name
	 *            The Customer's {@code name}
	 * @param address
	 *            The Customer's {@code address}
	 * @param purchases
	 *            The Customer's {@code purchases}
	 */
	public Customer(Long id, Name name, String address, 
			Set<Purchase> purchases) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.purchases = purchases;
	}

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
	public Name getName() {
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
	public Set<Purchase> getPurchases() {
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
	public void setName(Name name) {
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
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases.clear();
		this.purchases.addAll(purchases);
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
		builder.append(Optional.ofNullable(purchases)
				.map(Set::size)
				.orElse(null));
		builder.append("]");
		return builder.toString();
	}
}