package action_cast.controller.ClientObjects;

/**
 * Created by Action Ministries on 10/14/2016.
 */
public class CastingSlot {
    private Role role;
    private RoleAssignment roleAssignment;

    public CastingSlot(Role role) {
        this.role = role;
    }

    public CastingSlot(RoleAssignment roleAssignment) {
        this.roleAssignment = roleAssignment;
    }

    public Role getRole() {
        return role;
    }

    public RoleAssignment getRoleAssignment() {
        return roleAssignment;
    }
}
