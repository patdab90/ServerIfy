package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;

public class MessageGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
