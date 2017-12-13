/**
 * 
 */
package com.bgasparotto.springboot.veggieburger.service;

import com.bgasparotto.springboot.veggieburger.model.Purchase;

/**
 * Service for operations related to {@link Purchase}.
 * 
 * @author Bruno Gasparotto
 *
 */
public interface PurchaseService {

	/**
	 * Creates a purchase, calculating its total value with the given items.
	 * 
	 * @param purchase
	 *            The purchase to be calculated and created on database
	 * @return The created purchase on database
	 */
	Purchase create(Purchase purchase);
}