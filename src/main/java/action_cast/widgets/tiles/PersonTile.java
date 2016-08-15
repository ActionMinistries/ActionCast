package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.widgets.dragdrop.PersonTransferHandler;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bmichaud on 11/4/2015.
 */
public class PersonTile extends Tile {
    Person person;
    public PersonTile(JTileView parent, Person person) {
        super(parent);
        this.person = person;
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ((Graphics2D) g).drawString(person.getName(), getInsets().left, getInsets().top + g.getFontMetrics().getHeight());

    }

    public Person getPerson() {
        return person;
    }
}
