package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.IGroupService;
import pl.poznan.put.cs.ify.webify.service.IUserService;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@Component(value = "newGroupWindow")
@Scope(value = "session")
public class NewGroupWindow extends BaseWindow {

	// private UserSession session;
	private TextField groupName;
	private TextField newMemberName;
	private Button addUserButton;
	private Button addGroupButton;
	private ListSelect userList;
	private boolean userValid;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	public NewGroupWindow() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768598430767875263L;

	@Override
	protected void tryToAutowire() {

	}

	public void init(UserSession session) {
		this.setWidth("500px");
		final Form fl = new Form();
		createGroupNameField();
		createNewMemberNameFiled();
		createAddUserButtonField();
		createMembersListField(session);
		createCommitButton(fl, session);

		fl.addField("groupName", groupName);
		fl.addField("memberName", newMemberName);
		fl.getLayout().addComponent(addUserButton);
		fl.addField("membersList", userList);
		fl.getLayout().addComponent(addGroupButton);
		addComponent(fl);
	}

	protected void createCommitButton(final Form fl, final UserSession session) {
		addGroupButton = new Button("Utwóż grupę", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				fl.commit();
				UserEntity user = userService.getByUsername(session
						.getUserName());
				String groupName = fl.getField("groupName").getValue()
						.toString();
				GroupEntity group = groupService.createGroupe(user, groupName);
				for (Object userNameObj : userList.getItemIds()) {
					String userName = userNameObj.toString();
					UserEntity member = userService.getByUsername(userName);
					groupService.inviteUser(user, group, member);
				}
				getApplication().getMainWindow().showNotification(
						"Grupa zarejestrowana: " + groupName);
				getApplication().getMainWindow().removeWindow(
						NewGroupWindow.this);
				NewGroupWindow.this.close();
			}
		});
	}

	protected void createMembersListField(UserSession session) {
		userList = new ListSelect("Dodani użytwkonicy");
		userList.removeAllItems();
		userList.addItem(session.getUserName());
		userList.setNullSelectionAllowed(true);
		userList.setImmediate(true);
		userList.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty() == null) {
					return;
				}
				log.debug("valueChange = " + event.getProperty().toString());
				if (!userList.containsId(event.getProperty().toString())) {
					return;
				}
				userList.removeItem(event.getProperty().toString());
				userList.requestRepaint();
			}
		});
	}

	protected void createAddUserButtonField() {
		addUserButton = new Button("Dodaj użytkownika do grupy",
				new ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						String username = newMemberName.getValue().toString();
						if (username == null || username.equals("")) {
							return;
						}
						if (userValid == false)
							return;

						userList.addItem(username);
						newMemberName.setValue("");
					}
				});
	}

	protected void createNewMemberNameFiled() {
		newMemberName = new TextField("Nazwa użytkownika");
		newMemberName.setImmediate(true);
		newMemberName.setValidationVisible(true);
		newMemberName.addValidator(new Validator() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (!isValid(value))
					throw new InvalidValueException(
							"Taki użytkownik nie istnieje");

			}

			@Override
			public boolean isValid(Object value) {
				String name = (String) value;
				UserEntity obj = userService.getByUsername(name);
				if (obj != null)
					return userValid = true;
				return userValid = false;
			}
		});

		newMemberName.addValidator(new Validator() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (!isValid(value))
					throw new InvalidValueException(
							"Taki użytkownik jest już na liście");

			}

			@Override
			public boolean isValid(Object value) {
				String name = (String) value;
				return userValid = !userList.containsId(name);
			}
		});
	}

	protected void createGroupNameField() {
		groupName = new TextField("Nazwa group");
		groupName.setImmediate(true);
		groupName.setValidationVisible(true);
		groupName.setRequired(true);
		groupName.addValidator(new Validator() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (!isValid(value))
					throw new InvalidValueException(
							"Grupa musi mieć unikalną nazwę");

			}

			@Override
			public boolean isValid(Object value) {
				String name = (String) value;
				GroupEntity obj = groupService.findByName(name);
				if (obj == null)
					return true;
				return false;
			}
		});
	}

	public void refresh(UserSession session) {
		this.removeAllComponents();
		for (Window w : getChildWindows()) {
			removeWindow(w);
		}
		init(session);
	}
}
