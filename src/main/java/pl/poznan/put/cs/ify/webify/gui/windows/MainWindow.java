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
		// if (loginWindow == null) {
		// loginWindow = (LoginWindow) helper.getBean("loginWindow");
		// log.error("nowe okno logowania");
		// }
		// if (registerWindow == null) {
		// registerWindow = (RegisterWindow) helper.getBean("registerWindow");
		// log.error("nowe okno rejestracji");
		// }
		// if (loginComponent == null) {
		// loginComponent = (LoginComponent) helper.getBean("loginComponent");
		// log.error("nowe komponent logowania");
		// }
		// if (menuComponent == null) {
		// menuComponent = (MenuComponent) helper.getBean("menuComponent");
		// menuComponent.setApplication(getApplication());
		// menuComponent.setSession(session);
		// log.error("nowy komponent menu");
		// }

	}

	public void init() {
		if (!session.isLogged()) {
			loginComponent.setMain(this);
			loginComponent.setLogin(loginWindow);
			loginComponent.setRegister(registerWindow);
			addComponent(loginComponent);
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
