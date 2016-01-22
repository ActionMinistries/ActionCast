package action_cast.widgets;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.listeners.RoleAssignmentListener;
import action_cast.widgets.tiles.RoleTile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleAssignmentGrid extends JTileView<RoleTile> implements RoleAssignmentListener {

    private HashMap<Integer, Integer> assignments = new HashMap<>();
    private List<Role> roles = new ArrayList<>();
    private Controller controller;
    private Performance performance;

    public void setData(Controller controller, Performance performance, List<Role> roles, List<RoleAssignment> roleAssignments) {
     //   this.assignments = assignments;
        this.performance = performance;
        this.controller = controller;
        assignments.clear();
        this.roles = roles;
        for (RoleAssignment roleAssignment : roleAssignments) {
            assignments.put(roleAssignment.getRoleId(), roleAssignment.getPersonId());
        }
        updateDisplay();
    }

    public void updateDisplay() {
        removeAll();

        for(Role role : roles) {
            try {

                RoleTile tile;
                if (assignments.containsKey(role.getId())) {
                        tile = new RoleTile(this, controller.getPerson(assignments.get(role.getId())), role);
                } else {
                    tile = new RoleTile(this, null, role);
                }
                tile.addRoleAssignmentListener(this);
                add(tile);
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void roleAssigned(RoleAssignedEvent event) {
        if (event.getSource() instanceof RoleTile) {
            RoleTile source = (RoleTile)event.getSource();
            try {
                System.out.println("Assigning person");
                controller.assignPersonToRole(source.getAssignedPerson(), source.getRole(), performance);
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }
}
