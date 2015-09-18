package action_cast.widgets;

import action_cast.model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Created by bmichaud on 9/17/2015.
 */
public class PersonDisplayGrid extends DisplayTable {

    int x = 0;
    int y = 0;

    public PersonDisplayGrid(){
        super(2, 2);
        setDropMode(DropMode.ON);
    }

    public void setData(List<Person> people) {

        for (Person person : people) {

            ((DefaultTableModel)getModel()).setValueAt(person.getName(), x, y);
            x++;
            if (x == 2) {
                x = 0;
                y++;
            }
            if (y == 2) {
                break;
            }
        }
    }

    public void addPerson(Person person, int xPos, int yPos) {
        if (xPos < 2 && yPos < 2) {
            ((DefaultTableModel)getModel()).setValueAt(person.getName(), x, y);
        }
    }
}
