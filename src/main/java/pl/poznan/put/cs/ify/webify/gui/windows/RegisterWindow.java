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
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@Component(value = "registerWindow")
@Scope(value = "session")
public class RegisterWindow extends BaseWindow implements TextChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Button submit;

	// private final TextField peselField;
	private final TextField firstName;
	private final TextField lastName;
	private final TextField password;
	private final TextField address;
	private final TextField zipcode;
	private final TextField city;
	private final Label gender;
	private final Label birthDate;

	// @Autowired
	// private IUserRegistrationProcess registrationProcess;

	@Autowired
	private IUserService userBo;

	@Autowired
	private IUserDAO userDAO;

	public RegisterWindow() {
		super(App.APPLICATION_TITLE);

		final Form fl = new Form();
		// peselField = new TextField("Numer PESEL");
		// peselField.setRequired(true);
		// peselField.setMaxLength(11);
		// peselField.addValidator(new Validator() {
		//
		// /**
		// * GUID
		// */
		// private static final long serialVersionUID = 6695369521116529916L;
		//
		// @Override
		// public void validate(Object value) throws InvalidValueException {
		// if (!isValid(value))
		// throw new InvalidValueException(
		// "Numer PESEL musi być zgodny z Ustawą o ewidencji ludności i dowodach osobistych (Dz.U. z 2006 Nr 139, poz. 993)");
		// }
		//
		// @Override
		// public boolean isValid(Object value) {
		// return PeselUtils.isPeselValid(value.toString());
		// }
		// });
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
		city.addValidator(new RegexpValidator(
				"^[A-ZĄĘŚĆŃŹŻŁÓ][a-ząęśćńźżłó]+$",
				"Nieprawidłowy format nazwy mista"));
		gender = new Label("PŁEĆ NIEZNANA");
		gender.setCaption("Płeć");
		birthDate = new Label("BRAK DANYCH");
		birthDate.setCaption("Data urodzenia");
		submit = new Button("Rejestruj", new ClickListener() {
			/**
			 * GUID
			 */
			private static final long serialVersionUID = -3860666628204106131L;

			@Override
			@Transactional
			public void buttonClick(ClickEvent event) {
				fl.commit();

				UserEntity patient = new UserEntity();
				// UserRegistrationProcessEntity process = registrationProcess
				// .initProcess();

				// patient.setPesel(fl.getField("pesel").getValue().toString());
				patient.setLastName(fl.getField("lastName").getValue()
						.toString());
				patient.setFirstName(fl.getField("firstName").getValue()
						.toString());
				// TODO
				patient.setUsername(fl.getField("uName").getValue().toString());
				// patient.setAddress(fl.getField("address").getValue().toString());
				// patient.setCity(fl.getField("city").getValue().toString());
				// patient.setZipcode(fl.getField("zipcode").getValue().toString());
				patient.setPassword(fl.getField("password").getValue()
						.toString());
				patient.addRole(UserRole.USER);

				userDAO.persist(patient);
				// try {
				// registrationProcess.afterDataCompletionTrigger(process,
				// patient);
				// } catch (StateNotAllowedException e) {
				// getApplication().getMainWindow().showNotification(
				// "Niedozwolony stan procesu rejestracji");
				// }

				getApplication().getMainWindow().showNotification(
						"Użytkownik zarejestrowany: "
								+ fl.getField("uName").getValue().toString());
				getApplication().getMainWindow().removeWindow(
						event.getButton().getWindow());
			}
		});

		// peselField.addListener(this);

		// fl.addField("pesel", peselField);
		fl.addField("uName", uName);
		fl.addField("password", password);
		fl.addField("firstName", firstName);
		fl.addField("lastName", lastName);
		// fl.addField("address", address);
		// fl.addField("zipcode", zipcode);
		// fl.addField("city", city);
		fl.getLayout().addComponent(gender);
		fl.getLayout().addComponent(birthDate);
		fl.getLayout().addComponent(submit);
		addComponent(fl);

	}

	@Override
	protected void tryToAutowire() {

	}

	@Override
	public void textChange(TextChangeEvent event) {

		// String pesel = // (String) peselField.getData();
		// event.getText();
		// if (PeselUtils.isPeselValid(pesel)) {
		// log.info("[OK] PESEL: " + pesel);
		// } else {
		// log.info("[FAIL] PESEL: " + pesel);
		// }
		// gender.setValue(PeselUtils.getGender(pesel).toString());
		// birthDate.setValue(PeselUtils.getBirthDayString(pesel));

	}

}
