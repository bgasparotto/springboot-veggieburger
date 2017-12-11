package com.bgasparotto.springboot.veggieburger.model;

import java.util.Date;
import java.util.List;

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
	private Double totalValue;

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
	public Double getTotalValue() {
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
		this.items = items;
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
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		// builder.append(", customer=");
		// builder.append(customer);
		// builder.append(", items=");
		// builder.append(items);
		builder.append(", date=");
		builder.append(date);
		builder.append(", totalValue=");
		builder.append(totalValue);
		builder.append("]");
		return builder.toString();
	}
}