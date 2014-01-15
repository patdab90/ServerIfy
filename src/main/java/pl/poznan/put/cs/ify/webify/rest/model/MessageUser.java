package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;

/**
 * "user": { "group": "ify", "device": "motorola", "username": "alx", "recipe":
 * "BadumRecipe" },
 * 
 * @author Patryk
 * 
 */

public class MessageUser implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -6368381722642162198L;
	private String username;
	private String password;
	private String group;
	private String device;
	private String recipe;

	public MessageUser() {

	}

	public MessageUser(String username, String group, String device,
			String recipe) {
		this.username = username;
		this.group = group;
		this.device = device;
		this.recipe = recipe;	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String un) {
		username = un;
	}

	public synchronized String getGroup() {
		return group;
	}

	public synchronized void setGroup(String group) {
		this.group = group;
	}

	public synchronized String getDevice() {
		return device;
	}

	public synchronized void setDevice(String device) {
		this.device = device;
	}

	public synchronized String getRecipe() {
		return recipe;
	}

	public synchronized void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}