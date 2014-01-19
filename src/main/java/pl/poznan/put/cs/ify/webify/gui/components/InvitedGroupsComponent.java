package pl.poznan.put.cs.ify.webify.gui.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.GroupPermission;
import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.gui.windows.MainWindow;
import pl.poznan.put.cs.ify.webify.service.IGroupService;
import pl.poznan.put.cs.ify.webify.service.IUserService;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;

@Component("invitedGroupsComponent")
@Scope("session")
public class InvitedGroupsComponent extends Panel {

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IGroupPermissionDAO groupPermissionDAO;

	private Button confirmButton;
	private Button deleteButton;

	private ListSelect groupsList;

	private UserEntity user;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3977870901220198025L;

	public InvitedGroupsComponent() {
		super();
	}

	/**
	 * @param session
	 */
	public void init(UserSession session) {
		this.removeAllComponents();
		/*
		 * user = userService.getByUsername(session.getUserName());
		 * List<GroupEntity> groups = groupService.getUserInvitedGroups(user);
		 * 
		 * groupsList = new ListSelect(); groupsList.setRows(groups.size()); for
		 * (GroupEntity groupEntity : groups) {
		 * groupsList.addItem(groupEntity.getName()); }
		 * groupsList.setDescription("Grupy do których zostałeś zaproszony.");
		 * addComponent(groupsList); confirmButton = new
		 * Button("Potwierdź zaproszeni", new ClickListener() {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * @Override
		 * 
		 * @Transactional public void buttonClick(ClickEvent event) { String
		 * groupname = (String) groupsList.getValue(); if (groupname == null)
		 * return; GroupEntity group = groupDAO.findByName(groupname); if (group
		 * == null) return; groupService.addPermission(user, group,
		 * GroupPermission.X, GroupPermission.R);
		 * groupService.removePermission(user, group, GroupPermission.C);
		 * ((MainWindow) InvitedGroupsComponent.this.getWindow()) .refresh(); }
		 * });
		 * 
		 * deleteButton = new Button("Odrzuć zaproszenie", new ClickListener() {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * @Override
		 * 
		 * @Transactional public void buttonClick(ClickEvent event) { String
		 * groupname = (String) groupsList.getValue(); if (groupname == null)
		 * return; GroupEntity group = groupDAO.findByName(groupname); if (group
		 * == null) return; GroupPermissionEntity gp =
		 * groupPermissionDAO.find(user, group); groupPermissionDAO.remove(gp);
		 * } }); addComponent(confirmButton); addComponent(deleteButton);
		 */
	}

}
