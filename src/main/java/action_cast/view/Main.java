package action_cast.view;

import action_cast.config.ApplicationConfiguration;
import action_cast.config.WindowConfiguration;
import action_cast.controller.Controller;
import action_cast.data_store.DataStore;
import action_cast.model.*;
import action_cast.model.exceptions.InvalidIDException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main implements WindowListener, INamedWindow{
    private final ApplicationConfiguration configuration;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private People people1;
    private ManageSessions manageSessions1;
    private Songs songs1;

    private Controller controller;

    public Main() {
        this.configuration = ApplicationConfiguration.load();
        controller = new Controller();
        setupUI();

        people1.setController(controller);//.setData(store.getModel());
        manageSessions1.setController(controller);
        songs1.setController(controller);
    }

    public void resetData() throws JAXBException, SAXException, InvalidIDException {
        DataStore store = new DataStore(new DataModel());//new DataStore(classLoader.getResource("main.xml").getFile());
        //store.loadWithValidation();
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

    public void resetDataClean() throws JAXBException {
        DataStore store = new DataStore(new DataModel());
        store.save();
    }

    public static void main(String[] args) throws JAXBException, SAXException, InvalidIDException {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.addWindowListener(main);
        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowConfiguration mainConfig = ApplicationConfiguration.getInstance().getWindowConfiguration(main.getName());
        frame.pack();

        if (mainConfig != null) {
            frame.setSize(mainConfig.getWidth(), mainConfig.getHeight());
            frame.setLocation(mainConfig.getX(), mainConfig.getY());
        }
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveWindowConfiguration(e.getWindow());
        controller.save();
        try {
            configuration.save(ApplicationConfiguration.getConfigurationFilePath());
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
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

    private void setupUI() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new CardLayout(0, 0));
        tabbedPane1.addTab("Session", panel2);
        manageSessions1 = new ManageSessions();
        panel2.add(manageSessions1.getRootComponent(), "Card1");
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("People", panel3);
        people1 = new People();
        panel3.add(people1.getRootComponent(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Songs", panel4);
        songs1 = new Songs();
        panel4.add(songs1.getRootComponent(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    public JComponent getRootComponent() {
        return panel1;
    }

    @Override
    public String getName() {
        return "main";
    }

    private void saveWindowConfiguration(Window window) {
        WindowConfiguration config = new WindowConfiguration(getName());
        config.setHeight(window.getHeight());
        config.setWidth(window.getWidth());
        config.setX(window.getX());
        config.setY(window.getY());
        ApplicationConfiguration.getInstance().addWindowConfiguration(config);
    }
}
