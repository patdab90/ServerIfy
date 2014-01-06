package pl.poznan.put.cs.ify.webify.data.entity.group;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Entity
@Table(name = "groups")
public class GroupEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5356825281601180146L;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
	private List<GroupPermissionEntity> users = new LinkedList<GroupPermissionEntity>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "user_id", referencedColumnName = "id"),
			@JoinColumn(name = "username", referencedColumnName = "username") })
	private UserEntity owner;

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<GroupPermissionEntity> getUsers() {

		return users;
	}

	public void setUsers(List<GroupPermissionEntity> users) {
		this.users = users;
	}

	public void addUser(GroupPermissionEntity user) {
		this.users.add(user);
	}

	public void addUsers(Collection<GroupPermissionEntity> users) {
		this.users.addAll(users);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
