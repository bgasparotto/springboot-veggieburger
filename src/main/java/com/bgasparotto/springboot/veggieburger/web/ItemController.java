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

import com.bgasparotto.springboot.veggieburger.model.Item;
import com.bgasparotto.springboot.veggieburger.persistence.ItemRepository;

/**
 * @author Bruno Gasparotto
 *
 */
@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository repository;

	@GetMapping
	public ModelAndView list() {
		List<Item> items = repository.findAll();
		return new ModelAndView("items/list", "items", items);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Item item) {
		return new ModelAndView("items/view", "item", item);
	}

	@GetMapping("/new")
	public String createForm(@ModelAttribute Item item) {
		return "items/form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Item item, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			String viewName = "/items/form";
			String modelName = "formErrors";
			List<ObjectError> modelObject = result.getAllErrors();

			return new ModelAndView(viewName, modelName, modelObject);
		}

		item = repository.save(item);
		String message = "Item successfully saved";
		redirect.addFlashAttribute("globalMessage", message);

		String viewName = "redirect:/items/{item.id}";
		return new ModelAndView(viewName, "item.id", item.getId());
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Item item) {
		return new ModelAndView("items/form", "item", item);
	}
	
	@GetMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id,
			RedirectAttributes redirect) {
		repository.delete(id);

		List<Item> items = repository.findAll();
		ModelAndView mv = new ModelAndView("items/list", "items",
				items);
		mv.addObject("globalMessage", "Item successfully removed");
		
		return mv;
	}
}