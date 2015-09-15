package action_cast.widgets;

import action_cast.model.Person;

import javax.swing.*;
import java.util.List;

/**
 * Created by bmichaud on 9/11/2015.
 */
public class PersonListView extends JList{

    public PersonListView() {
        super(new DefaultListModel());
    }

    public void setData(List<Person> people) {
        if (people != null) {
            ((DefaultListModel) getModel()).clear();
            for (Person person : people) {
                ((DefaultListModel) getModel()).addElement(person.getName());
            }
        }
    }
}
