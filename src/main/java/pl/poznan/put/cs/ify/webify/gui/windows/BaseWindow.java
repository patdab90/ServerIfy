package pl.poznan.put.cs.ify.webify.gui.windows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pl.poznan.put.cs.ify.webify.Inject;
import pl.poznan.put.cs.ify.webify.SpringContextHelper;
import pl.poznan.put.cs.ify.webify.App;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;

import com.vaadin.ui.Window;

/**
 * Klasa bazowa dla wszystkich okien
 * 
 * 
 */
public abstract class BaseWindow extends Window {

	/**
	 * GUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Obiekt helpera
	 */
	protected SpringContextHelper helper = null;
	/**
	 * Obiekt loggera
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected App application;

	@Autowired
	protected UserSession session;

	public BaseWindow() {

		try {
			Inject.inject(this);
		} catch (Exception e) {
			log.error("Brak kontextu webowego");
		}
		try {
			helper = new SpringContextHelper(this.getApplication());
		} catch (NullPointerException e) {
			log.error("Błąd podczas towrzenia obiektu helpera");
		}

	}

	public void setApplication(App application) {
		this.application = application;
		try {
			helper = new SpringContextHelper(this.application);
		} catch (NullPointerException e) {
			log.error("Błąd podczas towrzenia obiektu helpera");
		}
	}

	public void setUserSession(UserSession session) {
		this.session = session;
	}

	public UserSession getSession() {
		return session;
	}

	protected abstract void tryToAutowire();
}
