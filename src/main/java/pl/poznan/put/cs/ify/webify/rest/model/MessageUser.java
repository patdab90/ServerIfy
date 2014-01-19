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

	public MessageUser(String username, String password, String group,
			String device, String recipe) {
		this.username = username;
		this.group = group;
		this.device = device;
		this.recipe = recipe;
		this.password = password;
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

	@Override
	public String toString() {
		return "MessageUser [username=" + username + ", password=" + password
				+ ", group=" + group + ", device=" + device + ", recipe="
				+ recipe + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageUser other = (MessageUser) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (recipe == null) {
			if (other.recipe != null)
				return false;
		} else if (!recipe.equals(other.recipe))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}