package action_cast.widgets;

import action_cast.controller.ClientObjects.CastingSlot;
import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.events.RoleUnassignedEvent;
import action_cast.widgets.tiles.RoleTile;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleAssignmentGrid extends JTileView<RoleTile> {

    private List<CastingSlot> castingSlots;
    private Controller controller;
    private Song song;

    public void setData(Controller controller, Song song) {
        this.song = song;
        this.controller = controller;
        controller.getEventBus().register(this);
        castingSlots = new ArrayList<>();
        List<RoleAssignment> roleAssignments = new ArrayList<>();
        try {
            roleAssignments = controller.getRoleAssignmentsForSong(song);
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        for (RoleAssignment roleAssignment : roleAssignments) {
            castingSlots.add(new CastingSlot(roleAssignment));
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
                add(tile);
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
        updateUI();
    }

    @Subscribe
    public void roleAssigned(RoleAssignedEvent event) {
        try {
            controller.assignPersonToRole(event.getPerson(), event.getRole(), song);
            if (!controller.isRoleFilled(song, event.getRole())) {
                castingSlots.add(new CastingSlot(event.getRole()));
            }
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        updateDisplay();
    }

    @Subscribe
    public void roleUnassigned(RoleUnassignedEvent event) {
        try {
            if (controller.isRoleFilled(song, controller.getRole(song.getId(), event.getRoleAssignment().getRoleId()))) {

            }
            controller.unassign(song, event.getRoleAssignment());
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTile() {

    }
}
