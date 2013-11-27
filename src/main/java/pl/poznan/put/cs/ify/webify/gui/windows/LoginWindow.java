package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.App;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.service.IUserService;
import pl.poznan.put.cs.ify.webify.service.impl.UserService;

import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Panel;

@Component(value = "loginWindow")
@Scope(value = "session")
public class LoginWindow extends BaseWindow implements LoginListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userBo;

	@Autowired
	private UserSession session;

	@Override
	public void onLogin(LoginEvent event) {
		String username = event.getLoginParameter("username");
		String password = event.getLoginParameter("password");
		if (userBo == null) {
			userBo = new UserService();
		}

		if (userBo.login(username, password)) {
			getApplication().getMainWindow().showNotification(
					"Zalogowano jako: " + username);
			session.setLogged(true);
			session.setUserName(username);
			session.setUserPassword(password);
			((MainWindow) getApplication().getMainWindow()).refresh();
		} else {
			getApplication().getMainWindow().showNotification(
					"Nieprawidłowe dane logowania");
		}

	}

	public LoginWindow() {
		super(App.APPLICATION_TITLE);
		LoginForm form = new LoginForm();
		form.addListener((LoginListener) this);
		Panel loginPanel = new Panel("Login");
		loginPanel.setWidth("250px");
		loginPanel.addComponent(form);
		addComponent(loginPanel);

	}

	@Override
	protected void tryToAutowire() {
		if (session == null) {
			session = (UserSession) helper.getBean("userSession");
		}

	}

}
