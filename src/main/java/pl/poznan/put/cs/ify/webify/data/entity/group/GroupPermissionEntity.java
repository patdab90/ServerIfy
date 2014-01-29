package pl.poznan.put.cs.ify.webify.data.entity.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Entity
@Table(name = "grouppermissions", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "group_id", "user_id" }),
		@UniqueConstraint(columnNames = { "name", "username" }),
		@UniqueConstraint(columnNames = { "group_id", "name", "username" }),
		@UniqueConstraint(columnNames = { "user_id", "name", "username" }) })
public class GroupPermissionEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7492343834172417218L;

	@Column(nullable = false)
	private boolean d = false;

	@Column(nullable = false)
	private boolean a = false;

	@Column(nullable = false)
	private boolean r = false;

	@Column(nullable = false)
	private boolean x = false;

	@Column(nullable = false)
	private boolean i = true;

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = GroupEntity.class)
	@JoinColumns(value = {
			@JoinColumn(name = "group_id", referencedColumnName = "id"),
			@JoinColumn(name = "name", referencedColumnName = "name") })
	private GroupEntity group;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserEntity.class)
	@JoinColumns(value = {
			@JoinColumn(name = "user_id", referencedColumnName = "id"),
			@JoinColumn(name = "username", referencedColumnName = "username") })
	private UserEntity user;

	public boolean isD() {
		return d;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public boolean isA() {
		return a;
	}

	public void setA(boolean a) {
		this.a = a;
	}

	public boolean isR() {
		return r;
	}

	public void setR(boolean r) {
		this.r = r;
	}

	public boolean isX() {
		return x;
	}

	public void setX(boolean x) {
		this.x = x;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
