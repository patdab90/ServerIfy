package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;

/**
 * "id": { "value": 666, "type": "Integer" }
 * 
 * @author Patryk
 * 
 */
public class MessageParam implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 558911541236012180L;
	private String value;
	private String type;
	private String username;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
