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
 * Created by bmichaud on 9/2/2015.
 */
public class Main implements ActionListener {
    private JPanel panel1;
    private JButton button1;
    private PerformanceTableView performanceTableView1;
    private JButton editSessionButton;
    private SessionSelector sessionSelector1;
    private JTable table1;

    private List<Session> sessions = new ArrayList<>();

    public Main() {
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        sessions.add(new Session(new Date(), new Date(endTime)));
        sessions.add(new Session(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        sessions.get(0).addPerformance(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        main.sessionSelector1.setData(main.sessions);
        main.sessionSelector1.addActionListener(main);
        main.performanceTableView1.setData(main.sessions.get(main.sessionSelector1.getSelectedIndex()).getPerformances());
        //main.sessionSelector1.setS
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        performanceTableView1.setData(sessions.get(sessionSelector1.getSelectedIndex()).getPerformances());
    }
}
