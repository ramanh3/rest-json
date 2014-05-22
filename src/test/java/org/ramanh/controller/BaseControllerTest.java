package org.ramanh.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/dispatcher-servlet.xml")
public class BaseControllerTest {
	@Autowired
	protected  WebApplicationContext wac;
	protected MockMvc mockMvc;
	protected ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		//add the filters we use in web.xml
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter( characterEncodingFilter,"/*").build();
	}

}
