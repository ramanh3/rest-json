package org.ramanh.controller;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ramanh.domain.User;
import org.ramanh.domain.exception.ErrorInfo;
import org.ramanh.domain.exception.InvaildObjectErrorInfo;
import org.ramanh.domain.exception.InvalideObjectResponseException;
import org.ramanh.domain.exception.NotFoundResponseException;
import org.ramanh.domain.exception.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		String id = getNextUserId()+"";
		user.setId(id);
		usersMap.put(id, user);		
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT,reason="Object Updates")	
	public void updateUser(@RequestBody @Valid User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors();
			throw new InvalideObjectResponseException(bindingResult);
			}
		String id = user.getId();
		if(null!=usersMap.get(id)){
			usersMap.put(id, user);	
		}else {
			throw new NotFoundResponseException(String.format("Object with id %s not found!",id));
		};
		
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT,reason="Object Deleted")	
	public void deleteUser(@PathVariable String id) {
		if(null!=usersMap.get(id)){
			usersMap.remove(id);	
		};
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundResponseException.class)
	@ResponseBody
	public ErrorInfo handleNotFoundRequest(HttpServletRequest req, ResponseException ex) {
	    return new ErrorInfo(ex);
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(InvalideObjectResponseException.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, InvalideObjectResponseException ex) {
	    return new InvaildObjectErrorInfo(ex);
	} 
	
	
	@PostConstruct
	protected void initUserMap(){
		random.setSeed(new Date().getTime());
		for (int i=0;i<15;i++) {
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