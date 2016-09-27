package action_cast.widgets;

import action_cast.controller.ClientObjects.Role;
import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.view.dialogs.RoleCreationDialog;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.tiles.RoleCreationTile;
import action_cast.widgets.tiles.RoleTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 1/26/2016.
 */
public class RoleTileView extends JTileView<RoleTile> {

    List<Role> roles = new ArrayList<>();
    private Song song;
    private Controller controller;

    public void setData(Controller controller, Song song) {
        this.controller = controller;
        this.song = song;
        updateDisplay();


    }

    public void updateDisplay() {
        removeAll();
        if (song != null) {
            try {
                this.roles = controller.getSongRoles(song.getId());
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
            for (Role role : roles) {
                add(new RoleTile(this, controller, null, role));
            }
            if (song != null) {
                setCreationTile(new RoleCreationTile(this, controller, song));
                add(getCreationTile());
            }
        } else {
            setCreationTile(null);
            roles = null;
        }
        updateUI();
    }

    @Override
    public void createTile() {
        RoleCreationDialog dialog = new RoleCreationDialog(controller, song);
        dialog.showDialog();
        updateDisplay();
    }
}
