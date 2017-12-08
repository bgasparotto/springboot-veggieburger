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

import com.bgasparotto.springboot.veggieburger.model.Purchase;
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
	public String createForm(@ModelAttribute Purchase purchase) {
		return "orders/form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Purchase purchase, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			String viewName = "/orders/form";
			String modelName = "formErrors";
			List<ObjectError> modelObject = result.getAllErrors();

			return new ModelAndView(viewName, modelName, modelObject);
		}

		purchase = repository.save(purchase);
		String message = "Order successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		String viewName = "redirect:/orders/{order.id}";
		return new ModelAndView(viewName, "order.id", purchase.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Purchase purchase) {
		return new ModelAndView("orders/form", "order", purchase);
	}
	
	@GetMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id,
			RedirectAttributes redirect) {
		repository.delete(id);

		List<Purchase> purchases = repository.findAll();
		ModelAndView mv = new ModelAndView("orders/list", "orders",
				purchases);
		mv.addObject("globalMessage", "Order successfully removed");
		
		return mv;
	}
}