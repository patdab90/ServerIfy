package pl.poznan.put.cs.ify.webify.gui.windows;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class GroupWindow extends BaseWindow {
	// private Label nameLable;
	// private Label usersLabel;
	// private TextField nameFiled;
	// private ListSelect usersSelect;

	public GroupWindow() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364195568884864443L;

	@Override
	protected void tryToAutowire() {

	}

}
