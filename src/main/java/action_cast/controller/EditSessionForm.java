package action_cast.controller;

import action_cast.model.Performance;
import action_cast.model.Session;
import action_cast.model.Song;
import action_cast.views.PerformanceTableView;
import action_cast.views.SessionSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class EditSessionForm implements ActionListener {
    private PerformanceTableView performanceTableView1;
    private JButton button1;
    private SessionSelector sessionSelector1;
    private JButton editSessionButton;
    private JPanel panel1;

    private List<Session> sessions = new ArrayList<>();

    public EditSessionForm() {
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        sessions.add(new Session(new Date(), new Date(endTime)));
        sessions.add(new Session(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        sessions.get(0).addPerformance(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));

        sessionSelector1.setData(sessions);
        sessionSelector1.addActionListener(this);
        performanceTableView1.setData(sessions.get(sessionSelector1.getSelectedIndex()).getPerformances());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sessionSelector1) {
            performanceTableView1.setData(sessions.get(sessionSelector1.getSelectedIndex()).getPerformances());
        }
        else if (e.getSource() == editSessionButton) {

        }
    }

}
