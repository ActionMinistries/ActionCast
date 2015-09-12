package action_cast.controller;

import action_cast.model.Person;
import action_cast.model.Session;
import action_cast.views.PersonListView;

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

    private Session currentSession = null;

    public People() {
        addPersonButton.addActionListener(this);
    }

    public void setData(Session session) {
        currentSession = session;
        personListView1.setData(session.getPeople());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonButton) {
            if (currentSession != null) {
                currentSession.addPerson(new Person(textField1.getText()));
                personListView1.setData(currentSession.getPeople());
                textField1.setText("");
            }
        }
    }
}
