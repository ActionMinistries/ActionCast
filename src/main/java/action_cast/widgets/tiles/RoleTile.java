package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.controller.Controller;
import action_cast.controller.events.RoleAssignmentEvent;
import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;
import action_cast.widgets.dragdrop.PersonTransferHandler;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.listeners.RoleAssignmentListener;
import com.google.common.eventbus.Subscribe;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile {

    private final Controller controller;
    Person person = null;
    Role role;

    JLabel mainCount;
    JLabel supportCount;
    JLabel backgroundCount;
    JPanel roleCountPanel;
    private RoleTileHeader header;

    List<RoleAssignmentListener> listeners = new ArrayList<>();

    private static BufferedImage defaultProfileImage;

    public RoleTile(JTileView parent, Controller controller, Person person, Role role) {
        super(parent);
        GridBagConstraints headerConstraints = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        if (defaultProfileImage == null) {
            try {
                defaultProfileImage = ImageIO.read(getClass().getClassLoader().getResource("images/profile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.controller = controller;
        this.person = person;
        this.role = role;
        this.setTransferHandler(new PersonTransferHandler());
        GridBagLayout layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        header = new RoleTileHeader(this);
        this.add(header, headerConstraints);
        this.addRoleTypeCountLabels();
        controller.getEventBus().register(this);
    }

    public void assignPerson(Person p) {
        person = p;
        for (RoleAssignmentListener listener : listeners) {
            listener.roleAssigned(new RoleAssignedEvent(this));
        }
        updateDisplay();
    }

    public Person getAssignedPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }

    @Subscribe public void handleRoleAssignment(RoleAssignmentEvent event) {
        updateDisplay();
    }

    public void updateDisplay() {
        if (person == null) {
            mainCount.setVisible(false);
            supportCount.setVisible(false);
            backgroundCount.setVisible(false);
        } else {
            mainCount.setVisible(true);
            supportCount.setVisible(true);
            backgroundCount.setVisible(true);

            try {
                mainCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.MAIN)));
                supportCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.SUPPORT)));
                backgroundCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.BACKGROUND)));
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
        header.updateDisplay();
        repaint();
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        addProfileImage(g);

        //((Graphics2D) g).drawString("X", 50, 15);

        if (person != null) {
            ((Graphics2D) g).drawString(person.getName(), getInsets().left, getInsets().top + header.getHeight() + defaultProfileImage.getHeight() + g.getFontMetrics().getHeight());
        }
    }



    public void addRoleAssignmentListener(RoleAssignmentListener listener) {
        this.listeners.add(listener);
    }

    private void addProfileImage(Graphics g) {
        if (person == null) {
            g.drawImage(defaultProfileImage, getInsets().left, getInsets().top + header.getHeight(), null);
        } else {
            //TODO use an actual profile image
            g.drawImage(defaultProfileImage, getInsets().left, getInsets().top + header.getHeight(), null);
        }
    }

    private void addRoleTypeCountLabels() {

        roleCountPanel = new JPanel();
        GridBagLayout mgr = new GridBagLayout();
        roleCountPanel.setLayout(mgr);
        roleCountPanel.setBorder(new LineBorder(Color.black, 2));

        GridBagConstraints constraints = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints1 = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints2 = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints3 = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );



        mainCount = new JLabel("0");
        supportCount = new JLabel("0");
        backgroundCount = new JLabel("0");



        if (person == null) {
            mainCount.setVisible(false);
            supportCount.setVisible(false);
            backgroundCount.setVisible(false);
        } else {
            try {
                mainCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.MAIN)));
                supportCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.SUPPORT)));
                backgroundCount.setText(Integer.toString(controller.getRoleTypeCountFor(person, RoleType.BACKGROUND)));
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }

        roleCountPanel.add(mainCount, constraints);
        roleCountPanel.add(supportCount, constraints1);
        roleCountPanel.add(backgroundCount, constraints2);
        this.add(roleCountPanel, constraints3);
    }
}
