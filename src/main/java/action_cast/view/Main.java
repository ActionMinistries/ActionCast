package action_cast.view;

import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBException;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private People people1;
    private ManageSessions manageSessions1;
    private TileViewTest tileViewTest1;

    private Controller controller;
    public Main() throws JAXBException, SAXException, InvalidIDException {
        controller = new Controller();
        //ClassLoader classLoader = getClass().getClassLoader();
       // DataStore store = new DataStore(classLoader.getResource("main.xml").getFile());
       // store.load();
//        Long startTime = System.currentTimeMillis();
//        Long endTime = System.currentTimeMillis();
//        endTime += 1000000000;
//        model = store.getModel();
//        model.setCurrentSession(new Session("Fall 2015", new Date(), new Date(endTime)));
//        //model.addSession(new Performances(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
//        SongID song = model.addSong("The First Song", "It goes like this na na na, na na, na na na na");
//        model.getCurrentSession().addPerformance(new Performance(song, "First Performance", "First Venue", new Date()));
//
//        PersonID random_guy = model.addPerson("random guy");
//        model.getCurrentSession().addPerson(random_guy);
//        //model.getCurrentSession().addPerson(new Person("random guy"));
//
//        model.addPerson("Random Guy");
//
//        store.save();
        people1.setController(controller);//.setData(store.getModel());
        //manageSessions1.setData(store.getModel());
    }

    public static void main(String[] args) throws JAXBException, SAXException, InvalidIDException {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
