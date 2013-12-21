package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.App;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.service.IUserService;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

@Component(value = "registerWindow")
@Scope(value = "session")
public class RegisterWindow extends BaseWindow implements TextChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Button submit;

	// private final TextField peselField;
	private TextField firstName;
	private TextField lastName;
	private TextField password;
	private TextField address;
	private TextField zipcode;
	private TextField city;

	// @Autowired
	// private IUserRegistrationProcess registrationProcess;

	@Autowired
	private IUserService userBo;

	@Autowired
	private IUserDAO userDAO;

	public RegisterWindow() {
		super(App.APPLICATION_TITLE);
		final Form fl = new Form();
		final TextField uName = new TextField("Nazwa użytkownika");
		uName.setImmediate(true);
		uName.setValidationVisible(true);
		uName.setRequired(true);
		uName.addValidator(new Validator() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (!isValid(value))
					throw new InvalidValueException(
							"Nazwa użytkownika musi być unikalna");

			}

			@Override
			public boolean isValid(Object value) {
				String name = (String) value;
				UserEntity obj = userBo.getByUsername(name);
				if (obj == null)
					return true;
				return false;
			}
		});

		password = new TextField("Hasło");
		password.setRequired(true);
		password.setRequiredError("Hasło nie może być puste");
		password.setImmediate(true);
		firstName = new TextField("Imię");
		firstName.setRequired(true);
		firstName.setRequiredError("Imię nie może być puste");
		firstName
				.addValidator(new RegexpValidator(
						"^[A-ZĄĘŚĆŃŹŻŁÓ][a-ząęśćńźżłó]+$",
						"Imię nie spełnia wymagań Ustawy o zmianie imion i nazwisk (Dz.U. z 2005 Nr 233, poz. 1992)"));
		firstName.setImmediate(true);
		firstName.setValidationVisible(true);
		lastName = new TextField("Nazwisko");
		lastName.setImmediate(true);
		lastName.setRequired(true);
		lastName.setRequiredError("Nazwisko nie może być puste");
		lastName.addValidator(new RegexpValidator(
				"^[A-ZĄĘŚĆŃŹŻŁÓ][a-ząęśćńźżłó]+$",
				"Nazwisko nie spełnia wymagań Ustawy o zmianie imion i nazwisk (Dz.U. z 2005 Nr 233, poz. 1992)"));
		address = new TextField("Adres");
		address.setRequired(true);
		address.setRequiredError("Adres nie może być pusty");
		zipcode = new TextField("Kod pocztowy");
		zipcode.setRequired(true);
		zipcode.setRequiredError("Kod pocztowy nie może być pusty");
		zipcode.addValidator(new RegexpValidator(
				"[0-9]{2}-[0-9]{3}",
				"Nieprawidłowy format kodu pocztowego. Kod musi być sformatowany zgodnie z Rozporządzeniem Ministerstwa Łączności nr 89 z dnia 17.11.1872"));
		city = new TextField("Miasto");
		city.setRequired(true);
		city.setRequiredError("Miasto nie może być puste");
		// city.addValidator(new RegexpValidator(
		// "^[A-ZĄĘŚĆŃŹŻŁÓ][a-ząęśćńźżłó]+$",
		// "Nieprawidłowy format nazwy mista"));
		submit = new Button("Rejestruj", new ClickListener() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = -3860666628204106131L;

			@Override
			@Transactional
			public void buttonClick(ClickEvent event) {
				fl.commit();

				UserEntity user = new UserEntity();
				String username = fl.getField("uName").getValue().toString();
				String pass = fl.getField("password").getValue().toString();
				log.debug("password=" + pass);
				user.setLastName(fl.getField("lastName").getValue().toString());
				user.setFirstName(fl.getField("firstName").getValue()
						.toString());
				user.setUsername(username);
				user.setPassword(pass);
				user.addRole(UserRole.USER);

				userDAO.persist(user);
				getApplication().getMainWindow().showNotification(
						"Użytkownik zarejestrowany: "
								+ fl.getField("uName").getValue().toString());
				getApplication().getMainWindow().removeWindow(
						event.getButton().getWindow());
			}
		});

		fl.addField("uName", uName);
		fl.addField("password", password);
		fl.addField("firstName", firstName);
		fl.addField("lastName", lastName);
		fl.getLayout().addComponent(submit);
		addComponent(fl);

	}

	@Override
	protected void tryToAutowire() {

	}

	@Override
	public void textChange(TextChangeEvent event) {

	}

}
