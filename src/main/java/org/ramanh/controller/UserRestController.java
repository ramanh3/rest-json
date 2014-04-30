package org.ramanh.controller;

import java.util.ArrayList;
import java.util.List;

import org.ramanh.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="rest/**",headers = { "Accept=text/xml, application/json" })
public class UserRestController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserRestController.class);

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> list() {
		User user = new User();
		user.setId("1");
		user.setFirstMame("Hello");
		user.setLastMame("Rest");
		final List<User> users = new ArrayList<>();
		users.add(user);
		logger.info("Returing %s users", users.size());
		return users;
	}
}