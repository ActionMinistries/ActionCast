package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.model.Role;
import action_cast.widgets.PersonTransferHandler;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile {

    Person person;
    Role role;

    public RoleTile(JTileView parent, Person person, Role role) {
        super(parent);
        this.person = person;
        this.role = role;
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ((Graphics2D)g).drawString(person.getName(), 12, 20);

    }
}
