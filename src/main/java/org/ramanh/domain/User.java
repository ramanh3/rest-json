package org.ramanh.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	String id;
	String firstName;
	String lastName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *  
	 * @return
	 */
	@NotBlank(message="org.ramanh.domain.user.invalid.firstname.empty")
	@Pattern(regexp="[a-z,A-Z]*",message="org.ramanh.domain.user.invalid.firstname.character")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotBlank(message="org.ramanh.domain.user.invalid.lastname.empty")
	@Pattern(regexp="\\w*",message="org.ramanh.domain.user.invalid.lastname.character")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
