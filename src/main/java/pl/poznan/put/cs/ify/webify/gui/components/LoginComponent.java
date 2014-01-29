package pl.poznan.put.cs.ify.webify.gui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Component(value = "loginComponent")
@Scope(value = "session")
public class LoginComponent extends CustomComponent {

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
	}

	public void registerButtonClick(Button.ClickEvent event) {

		main.addWindow(register);
	}

	public void closeButtonClick(Button.ClickEvent event) {

		info.setValue("Nie jesteś zalogowany");
	}

}
