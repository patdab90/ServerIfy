package pl.poznan.put.cs.ify.webify.gui.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.SpringContextHelper;

import com.vaadin.Application;
import com.vaadin.terminal.ClassResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;

@Component(value = "titlePanel")
@Scope(value = "session")
public class TitlePanel extends Panel {

	/**
	 * Obiekt helpera
	 */
	protected SpringContextHelper helper = null;
	/**
	 * Obiekt loggera
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Embedded logo;

	public TitlePanel() {
		super("If{Y}");
	}

	public void init(Application app) {
		if (logo == null) {
			logo = new Embedded("", new ClassResource("ify-logo.png", app));
			this.addComponent(logo);
		}
	}

}
