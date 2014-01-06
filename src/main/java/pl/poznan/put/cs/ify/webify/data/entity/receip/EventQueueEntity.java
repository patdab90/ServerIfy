package pl.poznan.put.cs.ify.webify.data.entity.receip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.SerializationUtils;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Entity
@Table(name = "queue")
public class EventQueueEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231231L;
	@Lob
	@Column(nullable = false)
	private byte[] data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "source_user_id", referencedColumnName = "id") })
	private UserEntity sourceUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "target_user_id", referencedColumnName = "id") })
	private UserEntity targetUser;

	public Object getDataObject() {
		return SerializationUtils.deserialize(data);
	}

	public void setDataObject(Object dataObject) {
		data = SerializationUtils.serialize(dataObject);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public UserEntity getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(UserEntity sourceUser) {
		this.sourceUser = sourceUser;
	}

	public UserEntity getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(UserEntity targetUser) {
		this.targetUser = targetUser;
	}

}
