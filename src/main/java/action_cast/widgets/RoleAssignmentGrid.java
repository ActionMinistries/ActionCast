package action_cast.widgets;

import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.listeners.RoleAssignmentListener;
import action_cast.widgets.tiles.RoleTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleAssignmentGrid extends JTileView<RoleTile> implements RoleAssignmentListener {

    private HashMap<Integer, Integer> assignments = new HashMap<>();
    private List<Role> roles = new ArrayList<>();
    private Controller controller;
    private Song song;

    public void setData(Controller controller, Song song) {
     //   this.assignments = assignments;
        this.song = song;
        this.controller = controller;
        assignments.clear();
        List<RoleAssignment> roleAssignments = null;
        try {
            roleAssignments = controller.getRoleAssignmentsForSong(song);
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        for (RoleAssignment roleAssignment : roleAssignments) {
            assignments.put(roleAssignment.getRoleId(), roleAssignment.getPersonId());
        }
        try {
            roles = controller.getSongRoles(song.getId());
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        updateDisplay();
    }

    public void updateDisplay() {
        removeAll();

        for(Role role : roles) {
            try {

                RoleTile tile;
                if (assignments.containsKey(role.getId())) {
                        tile = new RoleTile(this, controller, controller.getPerson(assignments.get(role.getId())), role);
                } else {
                    tile = new RoleTile(this, controller, null, role);
                }
                tile.addRoleAssignmentListener(this);
                add(tile);
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
        updateUI();
    }

    @Override
    public void roleAssigned(RoleAssignedEvent event) {
        if (event.getSource() instanceof RoleTile) {
            RoleTile source = (RoleTile)event.getSource();
            try {
                controller.assignPersonToRole(source.getAssignedPerson(), source.getRole(), song);
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createTile() {

    }
}
