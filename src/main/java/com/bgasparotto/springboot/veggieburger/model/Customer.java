package com.bgasparotto.springboot.veggieburger.model;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	
	@Valid
	@NotNull
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Address address;

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
	 * @param id
	 *            The customer's {@code id}
	 * @param name
	 *            The customer's {@code name}
	 */
	public Customer(Long id, Name name) {
		this(id, name, null, null);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The customer's {@code id}
	 * @param name
	 *            The customer's {@code name}
	 * @param addresses
	 *            The customer's {@code address}
	 * @param purchases
	 *            The customer's {@code purchases}
	 */
	public Customer(Long id, Name name, Address address,
			Set<Purchase> purchases) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.purchases = purchases;
	}

	/**
	 * Gets the Customer's {@code id}.
	 *
	 * @return the {@code Customer}'s {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Customer's {@code name}.
	 *
	 * @return the {@code Customer}'s {@code name}
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Gets the Customer's {@code address}.
	 *
	 * @return the {@code Customer}'s {@code address}
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Gets the Customer's {@code purchases}.
	 *
	 * @return the {@code Customer}'s {@code purchases}
	 */
	public Set<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * Sets the Customer's {@code id}.
	 *
	 * @param id
	 *            the {@code Customer}'s {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Customer's {@code name}.
	 *
	 * @param name
	 *            the {@code Customer}'s {@code name} to set
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * Sets the Customer's {@code address}.
	 *
	 * @param address
	 *            the {@code Customer}'s {@code address} to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Sets the Customer's {@code purchases}.
	 *
	 * @param purchases
	 *            the {@code Customer}'s {@code purchases} to set
	 */
	public void setPurchases(Set<Purchase> purchases) {
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
		builder.append(Optional.ofNullable(purchases)
				.map(Set::size)
				.orElse(null));
		builder.append("]");
		return builder.toString();
	}
}