package com.bgasparotto.springboot.veggieburger.persistence;

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
		item.setName("New Veggie Dog");
		item.setPrice(99.0);
		
		return item;
	}

	@Override
	protected Long getExistentEntityId() {
		return 5L;
	}

	@Override
	protected Long getUNonexistentEntityId() {
		return 6L;
	}
}