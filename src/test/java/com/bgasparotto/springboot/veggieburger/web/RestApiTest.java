package com.bgasparotto.springboot.veggieburger.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Bruno Gasparotto
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class RestApiTest {

	private MockMvc mvc;

	@Value("${spring.data.rest.base-path}")
	private String basePath;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	protected MockMvc mvc() {
		return mvc;
	}

	protected String basePath() {
		return basePath;
	}

	@Test
	public void validateTestSetup() {
		Assert.assertNotNull(basePath);
		Assert.assertNotNull(context);
		Assert.assertNotNull(mvc);
	}

	@Test
	public void testBasePath() throws Exception {
		mvc().perform(get(basePath())).andExpect(status().isOk());
	}
}