package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 1/14/2016.
 */
public class RoleAssignment {
    private final int id;
    private final int person;
    private final int role;

    public RoleAssignment(int id, int personId, int roleId) {
        this.id = id;
        this.person = personId;
        this.role = roleId;
    }

    public int getPersonId() {
        return person;
    }

    public int getRoleId() {
        return role;
    }

    public int getId() {
        return id;
    }
}
