package pl.poznan.put.cs.ify.webify.gui.components;

import java.nio.channels.IllegalSelectorException;
import java.util.List;

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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@Component(value = "editGoupPanel")
@Scope(value = "session")
public class EditGoupPanel extends Panel {

	private static final long serialVersionUID = 5346057885095040354L;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;
	@Autowired
	private EditUserComponent editUserComponent;

	private CustomComponent leftComponent;
	private CustomComponent rightComponent;

	private ListSelect userList;
	private Button deleteGroupButton;
	private TextField newMemberName;
	private Button addMemberButton;
	private Button userDeleteButton;

	private GroupEntity group;

	public EditGoupPanel() {
		super();

	}

	protected void createMembersListField(UserSession session) {
		userList = new ListSelect("Członkowie grupy");
		userList.setNullSelectionAllowed(true);
		List<UserEntity> groupMembers = groupService.getMembers(group);
		for (UserEntity userEntity : groupMembers) {
			userList.addItem(userEntity.getUsername());
		}
		userList.setImmediate(true);
		userList.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty() == null) {
					return;
				}
				UserEntity user = userService.getByUsername(event.getProperty()
						.toString());
				editUserComponent.refresh(user);
				editUserComponent.setVisible(true);
			}
		});
	}

	protected void createDeleteButton() {
		deleteGroupButton = new Button("Usun grupę", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void init(UserSession session, String groupName) {
		this.removeAllComponents();
		group = groupService.findByName(groupName);
		if (group == null) {
			throw new IllegalSelectorException();
		}
		createMembersListField(session);
		createDeleteButton();
		leftComponent = new CustomComponent();
		leftComponent.addComponent(userList);
		leftComponent.addComponent(deleteGroupButton);
	}
}
