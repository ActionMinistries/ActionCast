package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Person;
import action_cast.widgets.PersonListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bmichaud on 9/11/2015.
 */
public class People implements ActionListener {
    private JTextField textField1;
    private JButton addPersonButton;
    private JPanel mainPanel;
    private PersonListView personListView1;

    private DataModel model = null;

    public People() {
        addPersonButton.addActionListener(this);
    }

    public void setData(DataModel model) {
        this.model = model;
        //TODO replace the following line:
        personListView1.setData(model.getPeople());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonButton) {
            if (model != null) {
                model.addPerson(textField1.getText());
                personListView1.updateDisplay();
                textField1.setText("");
            }
        }
    }
}
