package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.Controller;
import action_cast.controller.events.RoleAssignmentEvent;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;
import action_cast.widgets.dragdrop.PersonTransferHandler;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.events.RoleUnassignedEvent;
import com.google.common.eventbus.Subscribe;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile {

    private final Controller controller;
    private Person person = null;
    private Role role;
    private RoleAssignment roleAssignment = null;

    RoleTileBody lowerPanel;

    private RoleTileHeader header;

    public RoleTile(JTileView parent, Controller controller, Person person, Role role, RoleAssignment roleAssignment) {
        super(parent);
        this.controller = controller;
        this.person = person;
        this.role = role;
        this.roleAssignment = roleAssignment;
        this.setTransferHandler(new PersonTransferHandler());
        ((FlowLayout)this.getLayout()).setHgap(0);
        ((FlowLayout)this.getLayout()).setVgap(0);
        ((FlowLayout)this.getLayout()).setAlignment(FlowLayout.LEFT);
        header = new RoleTileHeader(this);
        lowerPanel = new RoleTileBody(this);
        this.add(header);
        this.add(lowerPanel);

        controller.getEventBus().register(this);
    }

    public void assignPerson(Person p) {
        person = p;
        if (person != null) {
            controller.getEventBus().post(new RoleAssignedEvent(person, role));
        } else {
            controller.getEventBus().post(new RoleUnassignedEvent(role));
        }
        updateDisplay();
    }

    public Person getAssignedPerson() {
        return person;
    }

    public RoleAssignment getRoleAssignment() {
        return roleAssignment;
    }

    public Role getRole() {
        return role;
    }

    @Subscribe public void handleRoleAssignment(RoleAssignmentEvent event) {
        updateDisplay();
    }

    public void updateDisplay() {
        header.updateDisplay();
        lowerPanel.updateDisplay();
    }

    public Controller getController() {
        return controller;
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }
}
