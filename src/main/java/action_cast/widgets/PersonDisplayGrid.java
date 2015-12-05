package action_cast.widgets;

import action_cast.model.modelinterface.PersonView;

import javax.swing.*;
import java.util.List;

/**
 * Created by bmichaud on 9/17/2015.
 */
public class PersonDisplayGrid extends DisplayTable {

    List<PersonView> people = null;

    public PersonDisplayGrid(){
        super(2, 2);
        setDropMode(DropMode.ON);
        setDragEnabled(true);
        setTransferHandler(new PersonTransferHandler());
        setRowSelectionAllowed(false);
        this.setCellSelectionEnabled(true);
        this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setData(List<PersonView> people) {
        this.people = people;
        updateDisplay();
    }

    public void addPerson(PersonView person, int row, int col) {
        if (row < getRowCount() && col < getColumnCount()) {
            int index = getColumnCount() * row + col;
            if (index < people.size()) {
                people.add(getColumnCount() * row + col, person);
            }
            else {
                people.add(person);
            }
            updateDisplay();
        }
    }

    public void removeSelectedPerson() {
        int row = getSelectedRow();
        int col = getSelectedColumn();
        int index = getColumnCount() * row + col;
        if (index < people.size() && index >= 0) {
            people.remove(index);
        }
        updateDisplay();
    }

    public PersonView getSelectedPerson() {
        int row = getSelectedRow();
        int col = getSelectedColumn();
        int index = getColumnCount() * row + col;
        if (index < people.size()) {
            return people.get(index);
        }
        return null;
    }

    private void updateDisplay() {
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColumnCount(); col++) {
                int index = getColumnCount() * row + col;
                if (index < people.size()) {
                    ((UneditableTableModel) getModel()).setValueAt(people.get(index).getName(), row, col);
                }
                else {
                    ((UneditableTableModel) getModel()).setValueAt("", row, col);
                }
            }
        }
    }
}

