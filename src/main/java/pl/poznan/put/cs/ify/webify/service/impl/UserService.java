package pl.poznan.put.cs.ify.webify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.annotation.user.ViewAccessible;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
//import pl.poznan.put.cs.ify.webify.data.annotation.user.ViewAccessible;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.service.IUserService;
//import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.utils.StringUtils;

@Component
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.put.cs.ify.webify.bo.IUserBo#canAccessView(java.lang.Long,
	 * java.lang.Object)
	 */
	@Override
	public boolean canAccessView(final Long id, final Object view) {
		return this.canAccessView(userDAO.findById(id, UserEntity.class), view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.put.cs.ify.webify.bo.IUserBo#canAccessView(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public boolean canAccessView(final String username, final Object view) {
		return this.canAccessView(userDAO.findByUserName(username), view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.put.cs.ify.webify.bo.IUserBo#canAccessView(pl.poznan.put.cs
	 * .ify.webify.data.entity. user.UserEntity, java.lang.Object)
	 */
	@Override
	public boolean canAccessView(final UserEntity user, final Object view) {

		ViewAccessible ann = view.getClass()
				.getAnnotation(ViewAccessible.class);
		if (ann == null)
			return true;
		for (String username : ann.users()) {
			if (user.getUsername().equals(username)) {
				return true;
			}
		}

		for (UserRole role : ann.roles()) {
			if (user.hasRole(role))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.poznan.put.cs.ify.webify.bo.IUserBo#getByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserEntity getByUsername(final String username) {
		return userDAO.findByUserName(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.poznan.put.cs.ify.webify.bo.IUserBo#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean login(final String username, final String password) {
		return loginHash(username, StringUtils.sh1(username, password));
	}

	@Override
	public boolean loginHash(final String username, final String passHash) {
		return getUser(username, passHash) != null;
	}

	@Override
	@Transactional
	public UserEntity getUser(final String username, final String password) {
		final UserEntity user = userDAO.findByUserName(username);
		if (user == null) {
			return null;
		}
		if (password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean isBroadcast(UserEntity user) {
		if (user == null) {
			return false;
		}
		return "BROADCAST".equals(user.getUsername());
	}

	@Override
	@Transactional
	public UserEntity registerUser(String username, String pass,
			String firstName, String lastName) {
		UserEntity user = new UserEntity();
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setUsername(username);
		user.setPassword(pass);
		user.addRole(UserRole.USER);

		userDAO.persist(user);
		return user;
	}
}
