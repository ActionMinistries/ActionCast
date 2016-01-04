package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.widgets.dragdrop.PersonTransferHandler;
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
        this.setTransferHandler(new PersonTransferHandler());
    }

    public void assignPerson(Person p) {
        person = p;
        System.out.println("assigned: " + p.getName());
        repaint();
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ((Graphics2D) g).drawString(role.getType().name(), 12, 50);
        ((Graphics2D) g).drawString("X", 50, 15);

        if (person != null) {
            ((Graphics2D) g).drawString(person.getName(), 12, 20);
        }
    }
}
