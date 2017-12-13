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
import com.bgasparotto.springboot.veggieburger.service.PurchaseService;

/**
 * @author Bruno Gasparotto
 *
 */
@Controller
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired PurchaseService service;

	@GetMapping
	public ModelAndView list() {
		List<Purchase> purchases = repository.findAll();
		return new ModelAndView("purchases/list", "purchases", purchases);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Purchase purchase) {
		return new ModelAndView("purchases/view", "purchase", purchase);
	}

	@GetMapping("/new")
	public ModelAndView createForm(@ModelAttribute Purchase purchase) {
		List<Customer> availableCustomers = customerRepository.findAll();
		List<Item> availableItems = itemRepository.findAll();
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("availableCustomers", availableCustomers);
		model.put("availableItems", availableItems);
		
		return new ModelAndView("purchases/form", model);
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Purchase purchase, BindingResult result,
			RedirectAttributes redirect) {

		/* Validate against errors. */
		if (result.hasErrors()) {
			String viewName = "/purchases/form";
			String modelName = "formErrors";
			List<ObjectError> modelObject = result.getAllErrors();

			return new ModelAndView(viewName, modelName, modelObject);
		}

		/* Save the new purchase. */
		purchase = service.create(purchase);
		String message = "Purchase successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		/* Return the new saved purchase. */
		String viewName = "redirect:/purchases/{purchase.id}";
		return new ModelAndView(viewName, "purchase.id", purchase.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Purchase purchase) {
		List<Customer> availableCustomers = customerRepository.findAll();
		List<Item> availableItems = itemRepository.findAll();
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("availableCustomers", availableCustomers);
		model.put("availableItems", availableItems);
		model.put("purchase", purchase);
		
		return new ModelAndView("purchases/form", model);
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
		ModelAndView mv = new ModelAndView("purchases/list", "purchases",
				purchases);
		mv.addObject("globalMessage", "Purchase successfully removed");
		
		return mv;
	}
}