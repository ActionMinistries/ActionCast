package action_cast.controller;

import action_cast.model.Session;
import action_cast.views.PerformanceTableView;
import action_cast.views.SessionSelector;

import javax.swing.*;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class EditSessionForm {
    private PerformanceTableView performanceTableView1;
    private JButton button1;
    private SessionSelector sessionSelector1;
    private JButton editSessionButton;
    private JPanel panel1;

    public void setData(Session session) {
        performanceTableView1.setData(session.getPerformances());
    }
}
