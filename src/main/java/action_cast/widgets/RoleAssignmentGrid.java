package action_cast.widgets;

import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.ClientObjects.CastingSlot;
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

    private List<CastingSlot> castingSlots;
    //private HashMap<Integer, RoleAssignment> assignments = new HashMap<>();
    //private List<Role> roles = new ArrayList<>();
    private Controller controller;
    private Song song;

    public void setData(Controller controller, Song song) {
     //   this.assignments = assignments;
        this.song = song;
        this.controller = controller;
        //assignments.clear();
        castingSlots = new ArrayList<>();
        List<RoleAssignment> roleAssignments = new ArrayList<>();
        try {
            roleAssignments = controller.getRoleAssignmentsForSong(song);
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        for (RoleAssignment roleAssignment : roleAssignments) {
            castingSlots.add(new CastingSlot(roleAssignment));
            //assignments.put(roleAssignment.getRoleId(), roleAssignment);
        }
        try {
            List<Role> roles = controller.getSongRoles(song.getId());
            for(Role role : roles) {
                if (!controller.isRoleFilled(song, role)) {
                    castingSlots.add(new CastingSlot(role));
                }
            }
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        updateDisplay();
    }

    public void updateDisplay() {
        removeAll();

        for(CastingSlot slot : castingSlots) {
            try {

                RoleTile tile;
                if (slot.getRoleAssignment() != null) {
                        tile = new RoleTile(this, controller, controller.getPerson(slot.getRoleAssignment().getPersonId()), controller.getRole(song.getId(), slot.getRoleAssignment().getRoleId()), slot.getRoleAssignment());
                } else {
                    tile = new RoleTile(this, controller, null, slot.getRole(), null);
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
                if (!controller.isRoleFilled(song, source.getRole())) {
                    castingSlots.add(new CastingSlot(source.getRole()));
                }
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void roleUnassigned(RoleAssignedEvent event) {
        if (event.getSource() instanceof RoleTile) {
            RoleTile source = (RoleTile) event.getSource();
            try {
                if (controller.isRoleFilled(song, source.getRole())) {

                }
                controller.unassign(song, source.getRoleAssignment());
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createTile() {

    }
}
