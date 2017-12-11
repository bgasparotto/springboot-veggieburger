package com.bgasparotto.springboot.veggieburger.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

/**
 * @author Bruno Gasparotto
 *
 */
public class ItemApiTest extends RestApiTest {

	@Test
	public void testHome() throws Exception {
		mvc().perform(get(basePath()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("items")));
	}
}