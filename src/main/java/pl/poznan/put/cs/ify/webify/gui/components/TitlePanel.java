package pl.poznan.put.cs.ify.webify.gui.components;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class TitlePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TitlePanel() {
		super();
		init();
	}

	public TitlePanel(ComponentContainer content) {
		super(content);
		init();
	}

	public TitlePanel(String caption, ComponentContainer content) {
		super(caption, content);
		init();
	}

	public TitlePanel(String caption) {
		super(caption);
		init();
	}

	protected void init() {
		this.addComponent(new Label("If{Y}"));
	}
}
