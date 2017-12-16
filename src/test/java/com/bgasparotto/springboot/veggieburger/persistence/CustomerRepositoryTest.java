package com.bgasparotto.springboot.veggieburger.persistence;

import com.bgasparotto.springboot.veggieburger.model.Customer;
import com.bgasparotto.springboot.veggieburger.model.Name;

public class CustomerRepositoryTest extends JpaRepositoryTest<Customer, Long> {
	
	/**
	 * Constructor.
	 *
	 */
	public CustomerRepositoryTest() {
		super(Customer::getId, Customer::setId);
	}

	@Override
	protected Long getExistentEntityId() {
		return 3L;
	}

	@Override
	protected Customer getUnpersistedEntity() {
		Customer customer = new Customer();
		Name name = new Name ("Mickey", null);
		customer.setName(name);
		customer.setAddress("11 Some Random Street");
		
		return customer;
	}

	@Override
	protected int getExpectedListSize() {
		return 3;
	}

	@Override
	protected Long getNonExistentEntityId() {
		return 4L;
	}
}