package pl.poznan.put.cs.ify.webify.data.entity.receip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Entity
@Table(name = "parameters"/*
						 * , uniqueConstraints = { @UniqueConstraint(columnNames
						 * = { "groupname", "recipe", "device", "name",
						 * "username" }) }
						 */)
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

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public byte[] getLobValue() {
		return lobValue;
	}

	public String getName() {
		return name;
	}

	public String getRecipe() {
		return recipe;
	}

	public String getStringValue() {
		return stringValue;
	}

	public String getType() {
		return type;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setBooleanValue(final Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public void setDoubleValue(final Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public void setGroup(final GroupEntity group) {
		this.group = group;
	}

	public void setIntegerValue(final Integer integerValue) {
		this.integerValue = integerValue;
	}

	public void setLobValue(final byte[] lobValue) {
		this.lobValue = lobValue;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setRecipe(final String recipe) {
		this.recipe = recipe;
	}

	public void setStringValue(final String stringValue) {
		this.stringValue = stringValue;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public void setUser(final UserEntity user) {
		this.user = user;
	}

}
