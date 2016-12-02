package action_cast.widgets.events;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;

/**
 * Created by bmichaud on 1/22/2016.
 */
public class RoleAssignedEvent {

    private final Role role;
    private final Person person;

    public RoleAssignedEvent(Person person, Role role) {
        this.role = role;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }
}
