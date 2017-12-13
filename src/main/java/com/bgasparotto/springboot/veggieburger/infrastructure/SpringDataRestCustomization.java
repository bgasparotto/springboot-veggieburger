package com.bgasparotto.springboot.veggieburger.infrastructure;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.bgasparotto.springboot.veggieburger.model.Customer;
import com.bgasparotto.springboot.veggieburger.model.Item;
import com.bgasparotto.springboot.veggieburger.model.Purchase;

@Component
public class SpringDataRestCustomization
		extends RepositoryRestConfigurerAdapter {
	
	@Override
	public void configureRepositoryRestConfiguration(
			RepositoryRestConfiguration configuration) {

		configuration.exposeIdsFor(Customer.class, Item.class, Purchase.class);
	}
}