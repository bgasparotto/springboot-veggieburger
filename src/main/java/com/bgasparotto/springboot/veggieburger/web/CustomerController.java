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

import com.bgasparotto.springboot.veggieburger.model.Country;
import com.bgasparotto.springboot.veggieburger.model.Customer;
import com.bgasparotto.springboot.veggieburger.persistence.CountryRepository;
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
	
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 * Prepares and returns the model and view with the necessary lists and
	 * objects available in the model object.
	 * 
	 * @param customer
	 *            The customer object if available, {@code null otherwise}
	 * @param errors
	 *            The list of object errors if a previous form was attempted to
	 *            be submitted, {@code null} otherwise
	 * @return The {@code ModelAndView} object with any non null parameters and
	 *         the object model and view
	 */
	private ModelAndView form(Customer customer, List<ObjectError> errors) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		List<Country> availableCountries = countryRepository.findAll();
		model.put("availableCountries", availableCountries);
		
		if (customer != null) {
			model.put("customer", customer);
		}
		
		if (errors != null) {
			model.put("formErrors", errors);
		}
		
		return new ModelAndView("customers/form", model);
	}

	@GetMapping
	public ModelAndView list() {
		List<Customer> customers = repository.findAll();
		return new ModelAndView("customers/list", "customers", customers);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Customer customer) {
		return new ModelAndView("customers/view", "customer", customer);
	}

	@GetMapping("/new")
	public ModelAndView createForm(@ModelAttribute Customer customer) {
		return form(customer, null);
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Customer customer, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			List<ObjectError> modelObject = result.getAllErrors();
			return form(customer, modelObject);
		}

		customer = repository.save(customer);
		String message = "Customer successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		String viewName = "redirect:/customers/{customer.id}";
		return new ModelAndView(viewName, "customer.id", customer.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Customer customer) {
		return form(customer, null);
	}
	
	@GetMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id,
			RedirectAttributes redirect) {
		repository.delete(id);

		List<Customer> customers = repository.findAll();
		ModelAndView mv = new ModelAndView("customers/list", "customers",
				customers);
		mv.addObject("globalMessage", "Customer successfully removed");
		
		return mv;
	}
}