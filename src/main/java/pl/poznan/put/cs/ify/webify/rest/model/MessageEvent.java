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
}