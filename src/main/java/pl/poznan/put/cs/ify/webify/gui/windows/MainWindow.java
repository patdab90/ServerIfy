package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.App;
//import pl.poznan.put.cs.ify.webify.bo.impl.UserBo;
import pl.poznan.put.cs.ify.webify.gui.components.LoginComponent;
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

	// @Autowired
	// private MenuComponent menuComponent;

	public MainWindow() {
		super(App.APPLICATION_TITLE);
	}

	@Override
	protected void tryToAutowire() {

	}

	protected void loginView() {
		registerWindow.init();
		loginComponent.setMain(this);
		loginComponent.setLogin(loginWindow);
		loginComponent.setRegister(registerWindow);
		addComponent(loginComponent);
	}

	public void init() {
		if (!session.isLogged()) {
			loginView();
		} else {
			addComponent(new Label("Zalogowny jako: " + session.getUserName()));
			// addComponent(menuComponent);
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
