package action_cast.widgets.custom;

import action_cast.model.Person;

import java.awt.*;

/**
 * Created by bmichaud on 11/4/2015.
 */
public class PersonTile extends Tile {
    Person person;
    public PersonTile(Person person) {
        this.person = person;
    }

    protected void paintComponent(Graphics g) {
        ((Graphics2D)g).drawString(person.getName(), 5, 20);

        super.paintComponent(g);
    }

    public Person getPerson() {
        return person;
    }
}
