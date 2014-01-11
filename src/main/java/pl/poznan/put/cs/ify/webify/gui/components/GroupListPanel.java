package pl.poznan.put.cs.ify.webify.gui.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.gui.windows.NewGroupWindow;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

@Component(value = "groupListPanel")
@Scope(value = "session")
public class GroupListPanel extends Panel implements Window.CloseListener {

	/**
	 * 
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -3523899265424732121L;
	private ListSelect select;

	@Autowired
	private UserSession user;

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IGroupService groupService;

	private Window mainWindow;

	private NewGroupWindow groupWindow;

	private Button addNewGroupButton;

	public GroupListPanel() {
		super();
	}

	// @Transactional
	public void init(final UserSession user) {
		this.user = user;

		addNewGroupButton = new Button("Dodaj nową grupę", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				addNewGroupButton.setEnabled(false);
				groupWindow
						.addListener((Window.CloseListener) GroupListPanel.this);
				groupWindow.refresh(user);
				mainWindow.addWindow(groupWindow);
			}

		});
		// addComponent();
		addComponent(addNewGroupButton);
		createSelect();
	}

	protected void click(final UserSession user, final GroupListPanel self) {

	}

	public void setMainWindow(Window m) {
		mainWindow = m;
	}

	public void setGroupWindow(NewGroupWindow g) {
		groupWindow = g;
	}

	@Override
	public void windowClose(CloseEvent e) {
		log.debug("close window");
		addNewGroupButton.setEnabled(true);
		this.removeComponent(select);
		createSelect();
	}

	protected void createSelect() {
		select = new ListSelect();
		UserEntity userEntity = userDAO.findByUserName(user.getUserName());
		List<GroupEntity> groups = groupService.getGroups(userEntity);
		select.setRows(groups.size());
		if (groups != null)
			for (GroupEntity group : groups) {
				if (!group.equals("")) {
					select.addItem(group.getName());
				}
			}

		select.setNullSelectionAllowed(false);
		// select.setReadOnly(true);
		select.setImmediate(true);
		select.addListener(new ValueChangeListener() {

			private static final long serialVersionUID = 11231231312323434L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				log.debug(event.toString());
				log.debug(event.getProperty().getValue().toString());
			}
		});
		this.addComponent(select);
	}

}
