package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.App;
import pl.poznan.put.cs.ify.webify.gui.components.GroupListPanel;
//import pl.poznan.put.cs.ify.webify.bo.impl.UserBo;
import pl.poznan.put.cs.ify.webify.gui.components.LoginComponent;
import pl.poznan.put.cs.ify.webify.gui.components.TitlePanel;
//import pl.poznan.put.cs.ify.webify.gui.components.MenuComponent;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;

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

	// @Autowired
	// private UserBo userBo;

	@Autowired
	private LoginWindow loginWindow;

	@Autowired
	private LoginComponent loginComponent;

	@Autowired
	private RegisterWindow registerWindow;

	@Autowired
	private GroupListPanel groupListPanel;

	@Autowired
	private NewGroupWindow groupWindow;

	@Autowired
	private TitlePanel titlePanel;

	// @Autowired
	// private MenuComponent menuComponent;

	public MainWindow() {
		super(App.APPLICATION_TITLE);

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
			addComponent(groupListPanel);
			// menuComponent.constructMenu();
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
