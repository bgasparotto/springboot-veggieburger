package com.bgasparotto.springboot.veggieburger.persistence;

import java.math.BigDecimal;

import com.bgasparotto.springboot.veggieburger.model.Item;

public class ItemRepositoryTest extends JpaRepositoryTest<Item, Long> {

	/**
	 * Constructor.
	 *
	 */
	public ItemRepositoryTest() {
		super(Item::getId, Item::setId);
	}

	@Override
	protected Item getUnpersistedEntity() {
		Item item = new Item();
		item.setName("New Veggie Burger");
		item.setPrice(new BigDecimal(20));
		
		return item;
	}

	@Override
	protected Long getExistentEntityId() {
		return 5L;
	}

	@Override
	protected Long getNonExistentEntityId() {
		return 6L;
	}
}