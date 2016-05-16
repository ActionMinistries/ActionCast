package action_cast.view;

import action_cast.controller.Controller;
import action_cast.data_store.DataStore;
import action_cast.model.*;
import action_cast.model.exceptions.InvalidIDException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main implements WindowListener {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private People people1;
    private ManageSessions manageSessions1;
    private Songs songs1;

    private Controller controller;

    public Main() {
        controller = new Controller();

        people1.setController(controller);//.setData(store.getModel());
        manageSessions1.setController(controller);
        songs1.setController(controller);
    }

    public void resetData() throws JAXBException, SAXException, InvalidIDException {
        ClassLoader classLoader = getClass().getClassLoader();
        DataStore store = new DataStore(new DataModel());//new DataStore(classLoader.getResource("main.xml").getFile());
        //store.load();
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        DataModel model = store.getModel();
        model.setCurrentSession(new Session("Fall 2015", new Date(), new Date(endTime)));

        Song song = model.addSong("The First Song", "It goes like this na na na, na na, na na na na");
        Song run = model.addSong("I just wanna run", "");

        song.addRole("mane chericter", "The main character", RoleType.MAIN);
        song.addRole("syde chericter", "The main character's brother", RoleType.SUPPORT);
        song.addRole("support", "support", RoleType.SUPPORT);
        song.addRole("background", "who?", RoleType.BACKGROUND);

        run.addRole("runner", "", RoleType.MAIN);

        model.getCurrentSession().addSong(song);

        Person random_guy = model.addPerson("random guy", "", "");
        model.getCurrentSession().addPerson(random_guy);

        model.addPerson("Random Guy", "", "");

        store.save();
    }

    public static void main(String[] args) throws JAXBException, SAXException, InvalidIDException {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.addWindowListener(main);

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        controller.save();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
