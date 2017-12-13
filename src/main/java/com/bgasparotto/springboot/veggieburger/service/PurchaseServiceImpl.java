/**
 * 
 */
package com.bgasparotto.springboot.veggieburger.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bgasparotto.springboot.veggieburger.model.Item;
import com.bgasparotto.springboot.veggieburger.model.Purchase;
import com.bgasparotto.springboot.veggieburger.persistence.PurchaseRepository;

/**
 * Implementation of {@link PurchaseService}.
 * 
 * @author Bruno Gasparotto
 *
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseRepository repository;

	@Override
	public Purchase create(Purchase purchase) {
		
		/* Calculates the total value. */
		BigDecimal totalValue = BigDecimal.ZERO;
		List<Item> items = purchase.getItems();
		for (Item item : items) {
			BigDecimal price = item.getPrice();
			totalValue = totalValue.add(price);
		}
		purchase.setTotalValue(totalValue);

		/* Save the new purchase. */
		Purchase saved = repository.save(purchase);
		return saved;
	}
}