package action_cast.controller;

import action_cast.data_store.DataStore;
import action_cast.model.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.util.Date;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private People people1;
    private ManageSessions manageSessions1;

    DataModel model;// = new DataModel();

    public Main() throws JAXBException, SAXException {
        DataStore store = new DataStore("main.xml");
        store.load();
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        model = store.getModel();
        model.setCurrentSession(new Session("Fall 2015", new Date(), new Date(endTime)));
        //model.addSession(new Performances(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        model.getCurrentSession().addPerformance(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
        model.getCurrentSession().addPerson(new Person("random guy"));

        model.addPerson(new Person("Random Guy"));

        people1.setData(model);
        manageSessions1.setData(model);
        store.save();
    }

    public static void main(String[] args) throws JAXBException, SAXException {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
