package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;

public class MessageUserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718562503043815691L;

	private String userName;
	private String firstName;
	private String lastName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
