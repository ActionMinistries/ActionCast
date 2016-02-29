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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile implements ActionListener {

    private final Controller controller;
    Person person;
    Role role;

    JButton clearButton;
    JLabel mainCount;
    JLabel supportCount;
    JLabel backgroundCount;

    List<RoleAssignmentListener> listeners = new ArrayList<>();

    public RoleTile(JTileView parent, Controller controller, Person person, Role role) {
        super(parent);
        this.controller = controller;
        this.person = person;
        this.role = role;
        this.setTransferHandler(new PersonTransferHandler());
        GridBagLayout layoutManager = new GridBagLayout();
        setLayout(layoutManager);

        this.addClearButton();
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
            clearButton.setVisible(false);
            mainCount.setVisible(false);
            supportCount.setVisible(false);
            backgroundCount.setVisible(false);
        } else {
            clearButton.setVisible(true);
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
        repaint();
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ((Graphics2D) g).drawString(role.getType().name(), getInsets().left, JTileView.TILE_HEIGHT - getInsets().bottom);
        //((Graphics2D) g).drawString("X", 50, 15);

        if (person != null) {
            ((Graphics2D) g).drawString(person.getName(), getInsets().left, getInsets().top + g.getFontMetrics().getHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            assignPerson(null);
            updateDisplay();
        }
    }

    public void addRoleAssignmentListener(RoleAssignmentListener listener) {
        this.listeners.add(listener);
    }

    private void addRoleTypeCountLabels() {
        GridBagConstraints constraints = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints1 = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints2 = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
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

        this.add(mainCount, constraints);
        this.add(supportCount, constraints1);
        this.add(backgroundCount, constraints2);
    }

    private void addClearButton() {
        GridBagConstraints constraints = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.FIRST_LINE_END,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        clearButton = new JButton("X");
        clearButton.setMargin(new Insets(1,1,1,1));
        if (person == null) {
            clearButton.setVisible(false);
        }
        clearButton.addActionListener(this);
        this.add(clearButton, constraints);
    }
}
