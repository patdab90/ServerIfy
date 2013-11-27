package pl.poznan.put.cs.ify.webify.gui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

@Component(value = "loginComponent")
@Scope(value = "session")
public class LoginComponent extends CustomComponent implements
		Window.CloseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Window login;
	private Window register;
	private Window main;
	private final Label info;

	private final Button registerButton;
	private final Button loginButton;

	public LoginComponent() {

		@SuppressWarnings("unused")
		final VerticalLayout layout = new VerticalLayout();

		loginButton = new Button("Logowanie do systemu", this,
				"loginButtonClick");
		registerButton = new Button("Rejestracja nowego użytkownika", this,
				"registerButtonClick");
		info = new Label("Nie jesteś zalogowany");

		CssLayout layoutCss = new CssLayout() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			protected String getCss(Component c) {

				return null;
			}
		};

		layoutCss.addComponent(info);
		layoutCss.addComponent(loginButton);
		layoutCss.addComponent(registerButton);

		setCompositionRoot(layoutCss);
	}

	public void setMain(Window main) {
		this.main = main;
	}

	public void setLogin(Window login) {
		this.login = login;
	}

	public void setRegister(Window register) {
		this.register = register;
	}

	public void loginButtonClick(Button.ClickEvent event) {

		main.addWindow(login);
		login.addListener(this);
		login.setSizeFull();

		loginButton.setEnabled(false);

		info.setValue("Logowanie...");
	}

	public void registerButtonClick(Button.ClickEvent event) {

		main.addWindow(register);
		register.addListener(this);
		register.setSizeFull();
		registerButton.setEnabled(false);

		info.setValue("Rejestracja...");
	}

	public void closeButtonClick(Button.ClickEvent event) {

		info.setValue("Nie jesteś zalogowany");
	}

	/** In case the window is closed otherwise. */
	@Override
	public void windowClose(CloseEvent e) {

		loginButton.setEnabled(true);
		registerButton.setEnabled(true);

		info.setValue("Nie jesteś zalogowany");
	}
}
