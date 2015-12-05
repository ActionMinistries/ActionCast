package action_cast.widgets.custom;

import action_cast.model.modelinterface.PersonView;

import java.awt.*;

/**
 * Created by bmichaud on 11/4/2015.
 */
public class PersonTile extends Tile {
    PersonView person;
    public PersonTile(JTileView parent, PersonView person) {
        super(parent);
        this.person = person;
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ((Graphics2D)g).drawString(person.getName(), 12, 20);

    }

    public PersonView getPerson() {
        return person;
    }
}
