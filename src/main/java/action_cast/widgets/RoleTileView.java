package action_cast.widgets;

import action_cast.controller.ClientObjects.Role;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.tiles.RoleTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 1/26/2016.
 */
public class RoleTileView extends JTileView<RoleTile> {

    List<Role> roles = new ArrayList<>();

    public void setData(List<Role> roles) {
        System.out.println("setData");
        this.roles = roles;
        removeAll();
        for (Role role : roles) {
            add(new RoleTile(this, null, role));
        }
        for (RoleTile tile : getTiles()) {
            tile.updateDisplay();
        }
        updateDisplay();

    }

    public void updateDisplay() {
        for (Component c : getComponents()) {
            c.repaint();
        }
        repaint();
        //this.
    }
}
