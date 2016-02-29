package action_cast.widgets.events;

import java.util.EventObject;

/**
 * Created by bmichaud on 1/22/2016.
 */
public class RoleAssignedEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RoleAssignedEvent(Object source) {
        super(source);
    }
}
