package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.gui.components.EditGoupPanel;
import pl.poznan.put.cs.ify.webify.gui.components.GroupListPanel;
import pl.poznan.put.cs.ify.webify.gui.components.InvitedGroupsComponent;
//import pl.poznan.put.cs.ify.webify.bo.impl.UserBo;
import pl.poznan.put.cs.ify.webify.gui.components.LoginComponent;
import pl.poznan.put.cs.ify.webify.gui.components.TitlePanel;
//import pl.poznan.put.cs.ify.webify.gui.components.MenuComponent;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.IUserService;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

@Component(value = "mainWindow")
@Scope(value = "session")
public class MainWindow extends BaseWindow {

	/**
	 * GUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserSession session;

	@Autowired
	private IUserService userService;

	@Autowired
	private LoginWindow loginWindow;

	@Autowired
	private LoginComponent loginComponent;

	@Autowired
	private RegisterWindow registerWindow;

	@Autowired
	private GroupListPanel groupListPanel;

	@Autowired
	private EditGoupPanel editGoupPanel;

	@Autowired
	private NewGroupWindow groupWindow;

	@Autowired
	private TitlePanel titlePanel;

	@Autowired
	private InvitedGroupsComponent invitedGroupsComponent;

	private Button logoutButton;

	// @Autowired
	// private MenuComponent menuComponent;

	public MainWindow() {

	}

	@Override
	protected void tryToAutowire() {

	}

	protected void loginView() {
		loginComponent.setMain(this);
		loginComponent.setLogin(loginWindow);
		loginComponent.setRegister(registerWindow);
		addComponent(loginComponent);
	}

	public void init() {
		titlePanel.init(application);
		addComponent(titlePanel);
		if (!session.isLogged()) {
			loginView();
		} else {
			groupListPanel.setMainWindow(this);
			groupListPanel.setGroupWindow(groupWindow);
			groupListPanel.init(session);

			logoutButton = new Button("Wyloguj", new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					session.setLogged(false);
					session.setUserName(null);
					session.setUserPassword(null);
					refresh();
				}
			});
			addComponent(new Label("Jesteś zalogowany jako "
					+ session.getUserName()));
			addComponent(logoutButton);
			HorizontalLayout layout = new HorizontalLayout();
			layout.addComponent(groupListPanel);
			layout.addComponent(editGoupPanel);
			this.addComponent(layout);
			invitedGroupsComponent.init(session);
			this.addComponent(invitedGroupsComponent);

		}
	}

	public void refresh() {
		this.removeAllComponents();
		for (Window w : getChildWindows()) {
			removeWindow(w);
		}
		init();
	}

}
