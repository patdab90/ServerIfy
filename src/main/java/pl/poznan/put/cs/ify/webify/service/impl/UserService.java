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
	@Transactional
	public boolean login(final String username, final String password) {
		final UserEntity user = userDAO.findByUserName(username);
		if (user == null) {
			log.info("LOGIN: brak uzytkownika: " + username);
			return false;
		}
		if (StringUtils.md5(password).equals(user.getPassword())) {
			return true;
		}
		// log.debug("LOGIN: nieprawidłowe hasło: powinno być: "
		// + user.getPassword() + " : było: " + password + ":"
		// + StringUtils.md5(password));
		return false;
	}

}
