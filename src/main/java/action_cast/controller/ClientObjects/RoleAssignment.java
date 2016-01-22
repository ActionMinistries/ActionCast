package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 1/14/2016.
 */
public class RoleAssignment {
    private final int id;
    private final int person;
    private Role role;

    public RoleAssignment(int id, int personId, Role role) {
        this.id = id;
        this.person = personId;
        this.role = role;
    }

    public int getPersonId() {
        return person;
    }

    public Role getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}
