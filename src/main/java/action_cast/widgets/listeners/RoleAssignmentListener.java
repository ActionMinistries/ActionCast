package action_cast.widgets.listeners;

import action_cast.widgets.events.RoleAssignedEvent;

import java.util.EventListener;

/**
 * Created by bmichaud on 1/22/2016.
 */
public interface RoleAssignmentListener extends EventListener {

    void roleAssigned(RoleAssignedEvent event);

}
