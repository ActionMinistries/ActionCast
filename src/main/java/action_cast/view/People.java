package action_cast.view;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.PersonListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bmichaud on 9/11/2015.
 */
public class People implements ActionListener, ListSelectionListener {
    private JTextField nameField;
    private JButton addPersonButton;
    private JPanel mainPanel;
    private PersonListView personListView1;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton saveButton;
    private Controller controller;


    public People() {
        personListView1.addListSelectionListener(this);
        addPersonButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        personListView1.setData(controller.getPeople());
        updateDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonButton) {
            if (controller != null) {
                controller.addPerson("New Person", "", "");
                personListView1.setData(controller.getPeople());
                updateDisplay();
            }
        } else if (e.getSource() == saveButton) {
            try {
                int index = personListView1.getSelectedIndex();
                Person person = controller.getPerson(index);
                person.setName(nameField.getText());
                person.setEmail(emailField.getText());
                person.setPhoneNumber(phoneField.getText());
                controller.updatePerson(person);
                personListView1.setData(controller.getPeople());
                personListView1.setSelectedIndex(index);
                updateDisplay();
            } catch (InvalidIDException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void updateDisplay() {
        Person selectedPerson = null;
        if (!personListView1.isSelectionEmpty()) {
            selectedPerson = personListView1.getSelectedPerson();
            nameField.setText(selectedPerson.getName());
            emailField.setText(selectedPerson.getEmail());
            phoneField.setText(selectedPerson.getPhoneNumber());
        } else {
            clearDisplay();
        }
    }

    private void clearDisplay() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (personListView1.isSelectionEmpty()) {
            clearDisplay();
        }
        updateDisplay();
    }
}
