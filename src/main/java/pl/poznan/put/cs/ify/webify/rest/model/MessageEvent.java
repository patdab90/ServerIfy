package pl.poznan.put.cs.ify.webify.rest.model;

import java.io.Serializable;

/**
 * "event": { "target": "scony@htcEvo", "tag": 12 },
 * 
 * @author Patryk
 * 
 */
public class MessageEvent implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -7476992328733580528L;
	private String target;
	private int tag;

	public static int PULL_EVENT = -4;
	public static int GET_DATA_EVENT = -1;
	public static int PUT_DATA_EVENT = 0;

	public MessageEvent() {

	}

	public MessageEvent(String target, int tag) {
		this.target = target;
		this.tag = tag;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "MessageEvent [target=" + target + ", tag=" + tag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tag;
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		MessageEvent other = (MessageEvent) obj;
		if (tag != other.tag)
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

}