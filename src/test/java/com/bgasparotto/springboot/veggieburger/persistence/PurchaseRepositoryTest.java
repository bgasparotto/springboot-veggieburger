package com.bgasparotto.springboot.veggieburger.persistence;

import java.util.Date;

import com.bgasparotto.springboot.veggieburger.model.Purchase;

public class PurchaseRepositoryTest extends JpaRepositoryTest<Purchase, Long> {
	
	/**
	 * Constructor.
	 *
	 */
	public PurchaseRepositoryTest() {
		super(Purchase::getId, Purchase::setId);
	}

	@Override
	protected Long getExistentEntityId() {
		return 5L;
	}

	@Override
	protected Purchase getUnpersistedEntity() {
		Purchase purchase = new Purchase();
		purchase.setDate(new Date());
		purchase.setTotalValue(100.0);
		
		return purchase;
	}

	@Override
	protected Long getNonExistentEntityId() {
		return 6L;
	}
}