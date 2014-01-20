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
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
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

	@Column(nullable = false)
	private String recipe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "group_id", referencedColumnName = "id") })
	private GroupEntity group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "source_user_id", referencedColumnName = "id") })
	private UserEntity sourceUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "target_user_id", referencedColumnName = "id") })
	private UserEntity targetUser;

	public byte[] getData() {
		return data;
	}

	public Object getDataObject() {
		return SerializationUtils.deserialize(data);
	}

	public GroupEntity getGroup() {
		return group;
	}

	public String getRecipe() {
		return recipe;
	}

	public UserEntity getSourceUser() {
		return sourceUser;
	}

	public UserEntity getTargetUser() {
		return targetUser;
	}

	public void setData(final byte[] data) {
		this.data = data;
	}

	public void setDataObject(final Object dataObject) {
		data = SerializationUtils.serialize(dataObject);
	}

	public void setGroup(final GroupEntity group) {
		this.group = group;
	}

	public void setRecipe(final String recipe) {
		this.recipe = recipe;
	}

	public void setSourceUser(final UserEntity sourceUser) {
		this.sourceUser = sourceUser;
	}

	public void setTargetUser(final UserEntity targetUser) {
		this.targetUser = targetUser;
	}

}
