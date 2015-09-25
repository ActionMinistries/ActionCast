package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Session;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by bmichaud on 9/15/2015.
 */
public class ManageSessions implements ActionListener {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton assignPeopleButton;
    private JDatePickerImpl startDate;
    private JDatePickerImpl endDate;
    private JTextField nameTextField;
    //    private JDatePanelImpl startDatePanel = null;
//    private JDatePanelImpl endDatePanel = null;

    private DataModel model;

    public ManageSessions() {
        saveButton.addActionListener(this);
        assignPeopleButton.addActionListener(this);
    }

    public void setData(DataModel currentModel) {
        model = currentModel;
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(model.getCurrentSession());
        ((UtilDateModel)startDate.getModel()).setValue(model.getCurrentSession().getStartDate());
        ((UtilDateModel)endDate.getModel()).setValue(model.getCurrentSession().getEndDate());
        nameTextField.setText(model.getCurrentSession().getName());
    }

//    @Override
//    public void rowSelected(RowSelectedEvent e) {
//        if (e.getSource() == sessionTableView1) {
//            saveButton.setEnabled(true);
//            assignPeopleButton.setEnabled(true);
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            model.getCurrentSession().setName(nameTextField.getText());
            model.getCurrentSession().setStart(((UtilDateModel)startDate.getModel()).getValue());
            model.getCurrentSession().setEnd(((UtilDateModel)startDate.getModel()).getValue());
        }
        else if(e.getSource() == assignPeopleButton) {
            AddPeopleToSessionDialog peopleToSessionDialog = new AddPeopleToSessionDialog(model, model.getCurrentSession());
            peopleToSessionDialog.pack();
            peopleToSessionDialog.setVisible(true);
        }
//        else if(e.getSource() == newButton) {
//            CreateSessionDialog sessionDialog = new CreateSessionDialog();
//            Session newSession = sessionDialog.showDialog();
//            if (newSession != null) {
//              //  model.addSession(newSession);
//            }
//            //sessionTableView1.setData(model.getSessions());
//        }
    }

    private void createUIComponents() {
        //if (model != null && model.getCurrentSession() != null) {
        JDatePanelImpl startDatePanel = new JDatePanelImpl(new UtilDateModel(), new Properties());
        JDatePanelImpl endDatePanel = new JDatePanelImpl(new UtilDateModel(), new Properties());
        startDate = new JDatePickerImpl(startDatePanel, new DateComponentFormatter());
        endDate = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());

        // }

    }
}
