package action_cast.widgets;

import action_cast.model.modelinterface.PersonView;

import javax.swing.*;
import java.util.List;

/**
 * Created by bmichaud on 9/11/2015.
 */
public class PersonListView extends JList{

    List<PersonView> people;

    public PersonListView() {
        super(new DefaultListModel());
        setTransferHandler(new PersonTransferHandler());
        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
    }

    public void setData(List<PersonView> people) {
        this.people = people;
        updateDisplay();
    }

    public void updateDisplay() {
        if (people != null) {
            ((DefaultListModel) getModel()).clear();
            for (PersonView person : people) {
                ((DefaultListModel) getModel()).addElement(person.getName());
            }
        }
    }

    public void addPerson(int index, PersonView person) {
        people.add(index, person);
        updateDisplay();
    }

    public PersonView getSelectedPerson() {
        return people.get(getSelectedIndex());
    }

    public void removeSelectedPerson() {
        people.remove(getSelectedIndex());
        updateDisplay();
    }
}
