package pl.poznan.put.cs.ify.webify.gui.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;

public class GroupListPanel extends Panel {

	/**
	 * 
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -3523899265424732121L;
	private ListSelect select;
	private UserSession user;

	private IUserDAO userDAO;

	private IGroupService groupService;

	public GroupListPanel(UserSession user, IGroupService groupService,
			IUserDAO userDAO) {
		super();
		this.userDAO = userDAO;
		this.groupService = groupService;
	}

	public void init() {
		select = new ListSelect();
		UserEntity userEntity = userDAO.findByUserName(user.getUserName());
		List<GroupEntity> groups = groupService.getGroups(userEntity);
		for (GroupEntity group : groups) {
			Item item = select.addItem(group.getName());
			log.debug("init(): item=" + item);
		}
		select.setRows(groups.size());
		select.setNullSelectionAllowed(false);
		select.setReadOnly(true);
		select.addListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 11231231312323434L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}
}
