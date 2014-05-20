package org.ramanh.controller;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

public class HomeControllerTest extends BaseControllerTest{

	@Test
	public void testHtmlToJspViewResolve() throws Exception {
		ResultActions perform = mockMvc.perform(get("/index.html"));
		MvcResult result = perform.andReturn();
		assertViewName(result.getModelAndView(), "index");
	}

}
