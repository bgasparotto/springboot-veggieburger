package com.bgasparotto.springboot.veggieburger.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity that represents a purchase of the system.
 * 
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	private Customer customer;

	@ManyToMany
	@Cascade(CascadeType.MERGE)
	private List<Item> items;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@Min(1)
	private BigDecimal totalValue;

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 * 
	 * <p>
	 * Initialises an object using system default values for its attributes and
	 * {@code null} for its {@code id}.
	 * </p>
	 * 
	 * <p>
	 * Consider using the constructors that receive parameters instead.
	 * </p>
	 */
	public Purchase() {
		this(null, new ArrayList<Item>(), null, null);
	}

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 * 
	 * <p>
	 * Initialises an object populating its attributes using the given
	 * parameters.
	 * </p>
	 *
	 * @param customer
	 *            The Purchase's {@code customer}
	 * @param items
	 *            The Purchase's {@code items}
	 * @param date
	 *            The Purchase's {@code date}
	 * @param totalValue
	 *            The Purchase's {@code totalValue}
	 */
	public Purchase(Customer customer, List<Item> items, Date date,
			BigDecimal totalValue) {
		this.customer = customer;
		this.items = items;
		this.date = date;
		this.totalValue = totalValue;
	}

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 * 
	 * <p>
	 * Initialises an object populating its attributes using the given
	 * parameters.
	 * </p>
	 *
	 * @param id
	 *            The Purchase's {@code id}
	 * @param customer
	 *            The Purchase's {@code customer}
	 * @param items
	 *            The Purchase's {@code items}
	 * @param date
	 *            The Purchase's {@code date}
	 * @param totalValue
	 *            The Purchase's {@code totalValue}
	 */
	public Purchase(Long id, Customer customer, List<Item> items, Date date,
			BigDecimal totalValue) {
		this.id = id;
		this.customer = customer;
		this.items = items;
		this.date = date;
		this.totalValue = totalValue;
	}

	@PreRemove
	private void onPreRemove() {
		customer.getPurchases().removeIf(p -> p.getId() == id);
	}

	/**
	 * Gets the Purchase's {@code id}.
	 *
	 * @return The Purchase's {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Purchase's {@code customer}.
	 *
	 * @return The Purchase's {@code customer}
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Gets the Purchase's {@code items}.
	 *
	 * @return The Purchase's {@code items}
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Gets the Purchase's {@code date}.
	 *
	 * @return The Purchase's {@code date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the Purchase's {@code totalValue}.
	 *
	 * @return The Purchase's {@code totalValue}
	 */
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	/**
	 * Sets the Purchase's {@code id}.
	 *
	 * @param id
	 *            The Purchase's {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Purchase's {@code customer}.
	 *
	 * @param customer
	 *            The Purchase's {@code customer} to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Sets the Purchase's {@code items}.
	 *
	 * @param items
	 *            The Purchase's {@code items} to set
	 */
	public void setItems(List<Item> items) {
		this.items.clear();
		this.items.addAll(items);
	}

	/**
	 * Sets the Purchase's {@code date}.
	 *
	 * @param date
	 *            The Purchase's {@code date} to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Sets the Purchase's {@code totalValue}.
	 *
	 * @param totalValue
	 *            The Purchase's {@code totalValue} to set
	 */
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", customer=");
		builder.append(customer);
		builder.append(", items=");
		builder.append(Optional.ofNullable(items).map(List::size).orElse(null));
		builder.append(", date=");
		builder.append(date);
		builder.append(", totalValue=");
		builder.append(totalValue);
		builder.append("]");
		return builder.toString();
	}
}