package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Session;
import action_cast.widgets.RowSelectedEvent;
import action_cast.widgets.RowSelectedListener;
import action_cast.widgets.SessionTableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bmichaud on 9/15/2015.
 */
public class ManageSessions implements RowSelectedListener, ActionListener {
    private JPanel mainPanel;
    private SessionTableView sessionTableView1;
    private JButton editButton;
    private JButton assignPeopleButton;
    private JButton newButton;

    private DataModel model;

    public ManageSessions() {
        sessionTableView1.addRowSelectedListener(this);
        editButton.addActionListener(this);
        assignPeopleButton.addActionListener(this);
        newButton.addActionListener(this);
    }

    public void setData(DataModel currentModel) {
        model = currentModel;
        sessionTableView1.setData(model.getSessions());
    }

    @Override
    public void rowSelected(RowSelectedEvent e) {
        if (e.getSource() == sessionTableView1) {
            editButton.setEnabled(true);
            assignPeopleButton.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            //TODO edit
        }
        else if(e.getSource() == assignPeopleButton) {
            AddPeopleToSessionDialog peopleToSessionDialog = new AddPeopleToSessionDialog(model, model.getSessions().get(sessionTableView1.getSelectedRow()));
            peopleToSessionDialog.pack();
            peopleToSessionDialog.setVisible(true);
        }
        else if(e.getSource() == newButton) {
            CreateSessionDialog sessionDialog = new CreateSessionDialog();
            Session newSession = sessionDialog.showDialog();
            if (newSession != null) {
                model.addSession(newSession);
            }
            sessionTableView1.setData(model.getSessions());
        }
    }
}
