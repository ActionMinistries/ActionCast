package action_cast.widgets;

import action_cast.model.Person;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.PersonTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 11/18/2015.
 */
public class PersonTileView extends JTileView {

    List<Person> people = new ArrayList<>();

    public void setData(List<Person> people) {
        for(Person person : people) {
            super.add(new PersonTile(this, person));
        }
        this.people = people;
    }

    @Override
    public Component add(Component comp) {
        people.add(((PersonTile)comp).getPerson());
        return super.add(comp);
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
        people.remove(((PersonTile)comp).getPerson());
    }
}
