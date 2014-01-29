package pl.poznan.put.cs.ify.webify.gui.windows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

	/**
	 * 
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userBo;

	@Autowired
	private UserSession session;

	@Override
	public void onLogin(LoginEvent event) {

		String username = event.getLoginParameter("username");
		String password = event.getLoginParameter("password");
		log.debug("onLogin() username=" + username + " password=" + password);
		if (userBo == null) {
			userBo = new UserService();
		}

		if (userBo.login(username, password)) {
			log.debug("onLogin() LOGED username=" + username + " password="
					+ password);
			getApplication().getMainWindow().showNotification(
					"Zalogowano jako: " + username);
			session.setLogged(true);
			session.setUserName(username);
			session.setUserPassword(password);
			((MainWindow) getApplication().getMainWindow()).refresh();
		} else {
			log.debug("onLogin() UNLOGED username=" + username + " password="
					+ password);
			getApplication().getMainWindow().showNotification(
					"Nieprawidłowe dane logowania");
		}

	}

	public LoginWindow() {
		this.setWidth("290px");
		this.setHeight("335px");
		this.setName("Rejestracja");
		this.setModal(true);
		this.setResizable(false);
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
