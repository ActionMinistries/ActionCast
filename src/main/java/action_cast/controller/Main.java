package action_cast.controller;

import action_cast.model.*;
import action_cast.views.SessionSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main implements ActionListener {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private EditSessionForm editSessionForm1;
    private JPanel Session;
    private People people1;
    private SessionSelector sessionSelector1;

    DataModel model = new DataModel();

    public Main() {
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        model.addSession(new Session(new Date(), new Date(endTime)));
        model.addSession(new Session(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        model.getSessions().get(0).addPerformance(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
        model.getSessions().get(0).addPerson(new Person("random guy"));

        sessionSelector1.setData(model.getSessions());
        sessionSelector1.addActionListener(this);
        people1.setData(model);
        editSessionForm1.setData(model.getSessions().get(0));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sessionSelector1) {
            editSessionForm1.setData(model.getSessions().get(sessionSelector1.getSelectedIndex()));
            people1.setData(model);
        }
    }
}
