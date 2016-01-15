package action_cast.widgets;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.tiles.RoleTile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleAssignmentGrid extends JTileView<RoleTile> {

    private HashMap<Integer, Person> assignments = new HashMap<>();
    private List<Role> roles = new ArrayList<>();

    public void setData(List<Role> roles, List<RoleAssignment> roleAssignments) {
     //   this.assignments = assignments;
        assignments.clear();
        this.roles = roles;
        for (RoleAssignment roleAssignment : roleAssignments) {
            assignments.put(roleAssignment.getRole().getId(), roleAssignment.getPerson());
        }
        updateDisplay();
    }

    public void updateDisplay() {
        removeAll();

        for(Role role : roles) {
            RoleTile tile;
            if (assignments.containsKey(role.getId())) {
                tile = new RoleTile(this, assignments.get(role.getId()), role);
            } else {
                tile = new RoleTile(this, null, role);
            }
            add(tile);
        }
    }

    public void assignPerson(Person person, Point p) {
        getTileAt(p).assignPerson(person);
    }
}
