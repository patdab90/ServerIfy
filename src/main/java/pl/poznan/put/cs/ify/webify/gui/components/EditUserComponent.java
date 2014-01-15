package pl.poznan.put.cs.ify.webify.gui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

import com.vaadin.ui.CustomComponent;

@Component(value = "editUserComponent")
@Scope(value = "session")
public class EditUserComponent extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void refresh(UserEntity user) {

	}
}
