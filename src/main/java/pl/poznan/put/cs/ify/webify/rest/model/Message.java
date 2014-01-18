package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		Message other = (Message) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (values.size() != other.values.size())
			return false;
		else if (!values.keySet().containsAll(other.getValues().keySet()))
			return false;
		else if (!values.values().containsAll(other.values.values()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [user=" + user + ", event=" + event + ", values="
				+ values + "]";
	}

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
