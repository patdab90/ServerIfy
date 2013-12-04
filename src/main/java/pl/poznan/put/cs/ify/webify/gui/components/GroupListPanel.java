package pl.poznan.put.cs.ify.webify.gui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.impl.GroupService;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;

public class GroupListPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3523899265424732121L;
	private ListSelect select;
	private UserSession user;

	private IUserDAO userDAO;

	private GroupService groupService;

	public GroupListPanel(UserSession user) {
		super();
		// init();
	}

	public GroupListPanel(ComponentContainer content, UserSession user) {
		super(content);
		// init();
	}

	public GroupListPanel(String caption, ComponentContainer content,
			UserSession user) {
		super(caption, content);
		// init();
	}

	public GroupListPanel(String caption) {
		super(caption);
		// init();
	}

	protected void init() {
		select = new ListSelect();
		UserEntity userEntity = userDAO.findByUserName(user.getUserName());
		groupService.getGroups(userEntity);
	}
}
