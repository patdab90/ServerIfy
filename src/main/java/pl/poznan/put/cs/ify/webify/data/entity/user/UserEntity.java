package pl.poznan.put.cs.ify.webify.data.entity.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.SerializationUtils;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.utils.StringUtils;

/**
 * Encja użytkownika
 * 
 * @date 30-05-2013
 * 
 */
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	/**
	 * GUID
	 */
	private static final long serialVersionUID = 6039679067309708768L;

	/**
	 * Nazwa użytkownika
	 */
	@Column(length = 255, nullable = false, unique = true)
	private String username;

	/**
	 * Hasło użytkownika
	 */
	@Column(length = 255, nullable = false)
	private String password;

	/**
	 * Imię użytkownika
	 */
	@Column(length = 255, nullable = false)
	private String firstName;

	/**
	 * Nazwisko użytkownika
	 */
	@Column(length = 255, nullable = false)
	private String lastName;

	/**
	 * Czy użytkownik jest aktywny
	 */
	@Column(nullable = false)
	private boolean enabled = false;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	transient private List<GroupPermissionEntity> groups = new LinkedList<GroupPermissionEntity>();

	/**
	 * Role użytkownika - po serializacji
	 */
	@Column(nullable = false)
	@Lob
	private byte[] rolesLob;

	/**
	 * Przed serializacją
	 */
	transient private List<UserRole> roles = new ArrayList<UserRole>();

	public List<GroupPermissionEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupPermissionEntity> groups) {
		this.groups = groups;
	}

	public void addGroup(GroupPermissionEntity group) {
		this.groups.add(group);
	}

	public void addAllGroups(Collection<GroupPermissionEntity> groups) {
		this.groups.addAll(groups);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		if (username != null || !"".equals(username)) {
			this.password = StringUtils.sh1(username, password);
		}
	}

	/**
	 * @return the name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setFirstName(String name) {
		this.firstName = name;
	}

	/**
	 * @return the surname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setLastName(String surname) {
		this.lastName = surname;
	}

	/**
	 * @return the roles
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public List<UserRole> getRoles() {
		this.roles = (List<UserRole>) SerializationUtils.deserialize(rolesLob);
		if (roles == null)
			roles = new ArrayList<UserRole>();
		return roles;
	}

	/**
	 * Dodawanie roli użytkownikowi
	 * 
	 * @date 30-05-2013
	 * 
	 * @param role
	 */
	public void addRole(UserRole role) {
		if (!this.roles.contains(role)) {
			this.roles.add(role);
		}
		this.rolesLob = SerializationUtils.serialize(this.roles);
	}

	/**
	 * Usuwanie roli
	 * 
	 * @date 30-05-2013
	 * 
	 * @param role
	 */
	public void removeRole(UserRole role) {
		if (this.roles.contains(role)) {
			this.roles.remove(role);
		}
		this.rolesLob = SerializationUtils.serialize(this.roles);
	}

	/**
	 * Sprawdzanie czy użytkownik posiada rolę
	 * 
	 * @date 30-05-2013
	 * 
	 * @param role
	 * @return
	 */
	public boolean hasRole(UserRole role) {
		return this.getRoles().contains(role);
	}

	/**
	 * @return the rolesLob
	 */
	public byte[] getRolesLob() {
		return rolesLob;
	}

	/**
	 * @param rolesLob
	 *            the rolesLob to set
	 */
	@SuppressWarnings("unchecked")
	public void setRolesLob(byte[] rolesLob) {
		this.rolesLob = rolesLob;
		this.roles = (List<UserRole>) SerializationUtils
				.deserialize(this.rolesLob);
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return username + " password=" + password;
	}

}
