package com.bgasparotto.springboot.veggieburger.web;

import java.util.List;

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

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Customer customer) {
		return new ModelAndView("customers/view", "customer", customer);
	}

	@GetMapping("/new")
	public String createForm(@ModelAttribute Customer customer) {
		return "customers/form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Customer customer, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			String viewName = "/customers/form";
			String modelName = "formErrors";
			List<ObjectError> modelObject = result.getAllErrors();

			return new ModelAndView(viewName, modelName, modelObject);
		}

		customer = repository.save(customer);
		String message = "Customer successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		String viewName = "redirect:/customers/{customer.id}";
		return new ModelAndView(viewName, "customer.id", customer.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Customer customer) {
		return new ModelAndView("customers/form", "customer", customer);
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