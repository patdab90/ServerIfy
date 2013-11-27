package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -3777423160039160966L;
	private MessageUser user;
	private MessageEvent event;
	private Map<String, MessageParam> values = new HashMap<String, MessageParam>();

	public MessageUser getUser() {
		return user;
	}

	public void setUser(MessageUser user) {
		this.user = user;
	}

	public MessageEvent getEvent() {
		return event;
	}

	public void setEvent(MessageEvent event) {
		this.event = event;
	}

	public Map<String, MessageParam> getValues() {
		return values;
	}

	public void setValues(Map<String, MessageParam> values) {
		this.values = values;
	}

}
