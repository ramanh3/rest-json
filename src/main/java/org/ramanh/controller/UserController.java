package org.ramanh.controller;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.ramanh.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="rest/**",headers = { "Accept=text/xml, application/json" })
public class UserController {
	private SecureRandom random = new SecureRandom();
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private Map<String,User> usersMap = new HashMap<>();
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Collection<User> listUsers() {
		return usersMap.values();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		return usersMap.get(id);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED,reason="New Object created")	
	public void addUser(@RequestBody User user){
		int id = getNextUserId();
		usersMap.put(id+"", user);		
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT,reason="Object Updates")	
	public void updateUser(@RequestBody User user){
		String id = user.getId();
		if(null!=usersMap.get(id)){
			usersMap.put(id, user);	
		};
		
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT,reason="Object Deleted")	
	public void deleteeUser(@PathVariable String id) {
		if(null!=usersMap.get(id)){
			usersMap.remove(id);	
		};
	}
	
	@PostConstruct
	protected void initUserMap(){
		random.setSeed(new Date().getTime());
		for (int i=0;i<1000;i++) {
			User user = new User();
			user.setId(""+i);
			user.setFirstName("Hello");
			user.setLastName("Rest"+i);
			usersMap.put(""+i, user);		
		}
		logger.info("Preloaded %s users",usersMap.size());
	}
	
	private int getNextUserId() {
		int id = random.nextInt();
		while (usersMap.containsKey(id)) {
			id = random.nextInt();			
		}
		return id;
	}
}