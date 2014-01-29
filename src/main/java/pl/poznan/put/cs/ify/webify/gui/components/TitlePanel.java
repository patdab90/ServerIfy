package pl.poznan.put.cs.ify.webify.gui.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.Application;
import com.vaadin.terminal.ClassResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@Component(value = "titlePanel")
@Scope(value = "session")
public class TitlePanel extends HorizontalLayout {

	/**
	 * Obiekt loggera
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Embedded logo;
	private Label titleLabel;

	public TitlePanel() {
		super();
	}

	public void init(Application app) {
		this.removeAllComponents();
		logo = new Embedded("", new ClassResource("ify-logo.png", app));
		this.addComponent(logo);
		titleLabel = new Label("if{Y}");
		this.addComponent(titleLabel);
	}

}
