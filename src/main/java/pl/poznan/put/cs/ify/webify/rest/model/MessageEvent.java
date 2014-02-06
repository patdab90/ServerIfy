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

	public static final int PULLEVENT = -4;
	public static final int GETDATAEVENT = -1;
	public static final int PUTDATAEVENT = 0;

	public MessageEvent() {
	}

	public MessageEvent(final String target, final int tag) {
		this.target = target;
		this.tag = tag;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final MessageEvent other = (MessageEvent) obj;
		if (tag != other.tag) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}

	public int getTag() {
		return tag;
	}

	public String getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tag;
		result = prime * result + (target == null ? 0 : target.hashCode());
		return result;
	}

	public void setTag(final int tag) {
		this.tag = tag;
	}

	public void setTarget(final String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "MessageEvent [target=" + target + ", tag=" + tag + "]";
	}

}