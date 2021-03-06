package org.ramanh.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.Test;
import org.ramanh.domain.User;
import org.ramanh.domain.exception.InvaildObjectErrorInfo;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void testListUsers() throws Exception {
		ResultActions perform = mockMvc.perform(get("/rest/users/").accept(
				MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		perform.andExpect(jsonPath("$").isArray());
		// perform.andExpect(jsonPath("$").value(hasSize(1000)));
	}

	@Test
	public void testGetUser() throws Exception {
		String userId = "11";
		ResultActions perform = getUserRequest(userId);
		perform.andExpect(jsonPath("$.id").value(is(userId)));
		perform.andExpect(jsonPath("$.lastName").value(is("Rest" + userId)));
	}

	@Test
	public void testAddUser() throws Exception {

		User newUser = new User();
		newUser.setFirstName("Test");
		newUser.setLastName("Test1");
		String newUserJson = mapper.writeValueAsString(newUser);

		ResultActions perform = mockMvc.perform(post("/rest/users/")
				.content(newUserJson).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		perform.andExpect(status().isCreated());
	}

	@Test
	public void testUpdateUser() throws Exception {
		String userId = "11";
		// Read a a user from the service
		ResultActions perform = getUserRequest(userId);

		// update the user
		String userAsJson = perform.andReturn().getResponse()
				.getContentAsString();
		User existingUser = mapper.readValue(userAsJson, User.class);
		existingUser.setFirstName(existingUser.getFirstName() + "Updated");
	
		ResultActions performUpdate = updateUserRequest(existingUser);

		performUpdate.andExpect(status().isNoContent());
		// Read the updates user from the service

		ResultActions performGet = getUserRequest(userId);
		performGet.andExpect(jsonPath("$.id").value(is(userId)));
		performGet.andExpect(jsonPath("$.lastName").value(
				is(existingUser.getLastName())));
	}

	@Test
	public void testUpdateUnExistingUser() throws Exception {

		User unexistingUser = new User();
		unexistingUser.setId("unexistingId");
		unexistingUser.setFirstName("Test");
		unexistingUser.setLastName("Test1");
		// Try to update un-existing user
		ResultActions perform = updateUserRequest(unexistingUser);
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$.errorCode").value(is("100404")));
	}

	@Test
	public void testUpdateInvalidUser() throws Exception {

		User invaildUser = new User();
		invaildUser.setId("unexistingId");
		invaildUser.setFirstName("Tes-t");
		invaildUser.setLastName("^TestValid");
		// Try to update un-existing user
		ResultActions perform = updateUserRequest(invaildUser);
			
		perform.andExpect(status().isUnprocessableEntity());
		perform.andExpect(jsonPath("$.errorCode").value(is("100422")));
		MockHttpServletResponse response = perform.andReturn().getResponse();
		InvaildObjectErrorInfo invalidObjectError = mapper.readValue(response.getContentAsString(),InvaildObjectErrorInfo.class);
		Map<String, String> fieldErrors = invalidObjectError.getFieldErrors();
		assertNotNull("Error expected", fieldErrors);
		assertThat(fieldErrors.size(), is(2));
		assertThat(fieldErrors.get("lastName"), is("org.ramanh.domain.user.invalid.lastname.character"));
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		String userId = "11";
		// update the user
		ResultActions perform = mockMvc.perform(delete("/rest/users/" + userId)
				.accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNoContent());
		
	}

	private ResultActions getUserRequest(String userId) throws Exception {
		ResultActions performGet = mockMvc.perform(get("/rest/users/" + userId)
				.accept(MediaType.APPLICATION_JSON));

		performGet.andExpect(status().isOk());
		performGet.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		return performGet;
	}
	
	private ResultActions updateUserRequest(User user)
			throws Exception {
		String newUserJson = mapper.writeValueAsString(user);
		return mockMvc.perform(put("/rest/users/")
				.content(newUserJson).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
	}

}
