package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.App;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.IGroupService;
import pl.poznan.put.cs.ify.webify.service.IUserService;

import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

@Component(value = "newGroupWindow")
@Scope(value = "session")
public class NewGroupWindow extends BaseWindow {

	private UserSession session;
	private TextField groupName;
	private TextField newMemberName;
	private Button addUserButton;
	private ListSelect userList;
	private boolean userValid;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	public NewGroupWindow(UserSession s) {
		super(App.APPLICATION_TITLE);
		session = s;
		final Form fl = new Form();

		groupName = new TextField("Nazwa użytkownika");
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

		newMemberName = new TextField("Nazwa użytkownika");
		newMemberName.setImmediate(true);
		newMemberName.setValidationVisible(true);
		newMemberName.setRequired(true);
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
				return userValid = userList.containsId(name);
			}
		});

		addUserButton = new Button("Dodaj użytkownika do grupy",
				new ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (userValid == false)
							return;
						String username = newMemberName.getValue().toString();
						Item item = userList.addItem(username);
						log.debug("init(): item=" + item);
					}
				});

		userList = new ListSelect("Dodani użytwkonicy");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768598430767875263L;

	@Override
	protected void tryToAutowire() {

	}

}
