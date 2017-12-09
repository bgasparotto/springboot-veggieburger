package com.bgasparotto.springboot.veggieburger.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgasparotto.springboot.veggieburger.model.Customer;
import com.bgasparotto.springboot.veggieburger.model.Item;
import com.bgasparotto.springboot.veggieburger.model.Purchase;
import com.bgasparotto.springboot.veggieburger.persistence.CustomerRepository;
import com.bgasparotto.springboot.veggieburger.persistence.ItemRepository;
import com.bgasparotto.springboot.veggieburger.persistence.PurchaseRepository;

/**
 * @author Bruno Gasparotto
 *
 */
@Controller
@RequestMapping("/orders")
public class PurchaseController {

	@Autowired
	private PurchaseRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@GetMapping
	public ModelAndView list() {
		List<Purchase> purchases = repository.findAll();
		return new ModelAndView("orders/list", "orders", purchases);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Purchase purchase) {
		return new ModelAndView("orders/view", "order", purchase);
	}

	@GetMapping("/new")
	public ModelAndView createForm(@ModelAttribute Purchase purchase) {
		List<Customer> availableCustomers = customerRepository.findAll();
		List<Item> availableItems = itemRepository.findAll();
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("availableCustomers", availableCustomers);
		model.put("availableItems", availableItems);
		
		return new ModelAndView("orders/form", model);
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Purchase purchase, BindingResult result,
			RedirectAttributes redirect) {

		/* Validate against errors. */
		if (result.hasErrors()) {
			String viewName = "/orders/form";
			String modelName = "formErrors";
			List<ObjectError> modelObject = result.getAllErrors();

			return new ModelAndView(viewName, modelName, modelObject);
		}
		
		/* Calculates the total value. */
		Double totalValue = 0.0;
		List<Item> items = purchase.getItems();
		for (Item item : items) {
			totalValue += item.getPrice();
		}
		purchase.setTotalValue(totalValue);

		/* Save the new order. */
		purchase = repository.save(purchase);
		String message = "Order successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		/* Return the new saved order. */
		String viewName = "redirect:/orders/{order.id}";
		return new ModelAndView(viewName, "order.id", purchase.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Purchase purchase) {
		List<Customer> availableCustomers = customerRepository.findAll();
		List<Item> availableItems = itemRepository.findAll();
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("availableCustomers", availableCustomers);
		model.put("availableItems", availableItems);
		model.put("purchase", purchase);
		
		return new ModelAndView("orders/form", model);
	}
	
	@GetMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id,
			RedirectAttributes redirect) {
		Purchase purchase = repository.findOne(id);
		Customer customer = purchase.getCustomer();
		customer.getPurchases().removeIf(p -> p.getId() == id);
		
		customerRepository.save(customer);
		repository.delete(id);

		List<Purchase> purchases = repository.findAll();
		ModelAndView mv = new ModelAndView("orders/list", "orders",
				purchases);
		mv.addObject("globalMessage", "Order successfully removed");
		
		return mv;
	}
}