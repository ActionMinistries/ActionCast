package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 1/14/2016.
 */
public class RoleAssignment {
    private Person person;
    private Role role;

    public RoleAssignment(Person person, Role role) {
        this.person = person;
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }
}
