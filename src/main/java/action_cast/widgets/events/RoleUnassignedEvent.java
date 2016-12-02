package action_cast.widgets.events;

import action_cast.controller.ClientObjects.RoleAssignment;

/**
 * Created by bmichaud on 1/22/2016.
 */
public class RoleUnassignedEvent {

    private final RoleAssignment roleAssignment;

    public RoleUnassignedEvent(RoleAssignment roleAssignment) {
        this.roleAssignment = roleAssignment;
    }

    public RoleAssignment getRoleAssignment() {
        return roleAssignment;
    }
}
