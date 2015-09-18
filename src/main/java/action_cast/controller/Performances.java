package action_cast.controller;

import action_cast.model.Session;
import action_cast.widgets.PerformanceTableView;
import action_cast.widgets.SessionSelector;

import javax.swing.*;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class Performances {
    private PerformanceTableView performanceTableView1;
    private JButton button1;
    private SessionSelector sessionSelector1;
    private JButton editSessionButton;
    private JPanel panel1;

    public void setData(Session session) {
        performanceTableView1.setData(session.getPerformances());
    }
}