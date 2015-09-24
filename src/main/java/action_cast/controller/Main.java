package action_cast.controller;

import action_cast.model.*;
import action_cast.widgets.SessionSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private Performances performances1;
    private JPanel Performances;
    private People people1;
    private AddPerformance addPerformance1;
    private ManageSessions manageSessions1;

    DataModel model;// = new DataModel();

    public Main() {
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        model = new DataModel(new Session(new Date(), new Date(endTime)));
        //model.addSession(new Performances(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        model.getCurrentSession().addPerformance(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
        model.getCurrentSession().addPerson(new Person("random guy"));

        model.addPerson(new Person("Random Guy"));

        addPerformance1.setData(model.getCurrentSession());
        people1.setData(model);
        manageSessions1.setData(model);
        performances1.setData(model.getCurrentSession());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
