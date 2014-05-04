package org.ramanh.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.ramanh.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="rest/**",headers = { "Accept=text/xml, application/json" })
public class UserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	private Map<String,User> usersMap = new HashMap<>();
	
	@PostConstruct
	protected void initUserMap(){
		for (int i=0;i<1000;i++) {
			User user = new User();
			user.setId(""+i);
			user.setFirstMame("Hello");
			user.setLastMame("Rest"+i);
			usersMap.put(""+i, user);		
		}
		logger.info("Preloaded %s users",usersMap.size());
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Collection<User> list() {
		return usersMap.values();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		return usersMap.get(id);
	}
}