package com.bgasparotto.springboot.veggieburger.persistence;

import com.bgasparotto.springboot.veggieburger.model.Customer;

public class CustomerRepositoryTest extends JpaRepositoryTest<Customer, Long> {
	
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
		customer.setName("Mickey");
		customer.setAddress("11 Some Random Street");
		
		return customer;
	}

	@Override
	protected int getExpectedListSize() {
		return 3;
	}

	@Override
	protected Long getUNonexistentEntityId() {
		return 4L;
	}
}