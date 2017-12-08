package com.bgasparotto.springboot.veggieburger.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bruno Gasparotto
 *
 */
@Controller
@RequestMapping("/environment")
public class EnvironmentController {

	@GetMapping
	public String index() {
		return "environment";
	}
}