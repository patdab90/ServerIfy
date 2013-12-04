package pl.poznan.put.cs.ify.webify.data.entity.receip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Entity
@Table(name = "parameters", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"groupname", "recipe", "device", "name", "username" }) })
public class ParameterEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 106775831538879658L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "username", referencedColumnName = "username"),
			@JoinColumn(name = "user_id", referencedColumnName = "id") })
	private UserEntity user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "groupname", referencedColumnName = "name"),
			@JoinColumn(name = "group_id", referencedColumnName = "id") })
	private GroupEntity group;

	@Column(nullable = false, length = 25)
	private String name;

	@Column(nullable = false, length = 25)
	private String type;

	@Column(nullable = true)
	private String stringValue;

	@Column(nullable = true)
	private Integer integerValue;

	@Column(nullable = true)
	@Lob
	private byte[] lobValue;

	@Column(nullable = true)
	private Double doubleValue;

	@Column(nullable = true)
	private Boolean booleanValue;

	@Column(nullable = false)
	private String recipe;

	@Column(nullable = false)
	private String device;

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	public byte[] getLobValue() {
		return lobValue;
	}

	public void setLobValue(byte[] lobValue) {
		this.lobValue = lobValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

}
