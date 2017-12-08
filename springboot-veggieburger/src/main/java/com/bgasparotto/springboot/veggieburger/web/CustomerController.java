package com.bgasparotto.springboot.veggieburger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgasparotto.springboot.veggieburger.model.Customer;
import com.bgasparotto.springboot.veggieburger.persistence.CustomerRepository;

/**
 * @author Bruno Gasparotto
 *
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	@GetMapping
	public ModelAndView list() {
		List<Customer> customers = repository.findAll();
		return new ModelAndView("customers/list", "customers", customers);
	}
}