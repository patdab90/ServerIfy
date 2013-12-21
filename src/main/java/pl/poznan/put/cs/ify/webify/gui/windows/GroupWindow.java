package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.App;

import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

@Component
@Scope(value = "session")
public class GroupWindow extends BaseWindow {
	private Label nameLable;
	private Label usersLabel;
	private TextField nameFiled;
	private ListSelect usersSelect;

	public GroupWindow() {
		super(App.APPLICATION_TITLE);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364195568884864443L;

	@Override
	protected void tryToAutowire() {

	}

}
