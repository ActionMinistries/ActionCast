package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Role;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;
import action_cast.widgets.dragdrop.PersonTransferHandler;
import action_cast.widgets.events.RoleAssignedEvent;
import action_cast.widgets.listeners.RoleAssignmentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile implements ActionListener {

    Person person;
    Role role;

    JButton clearButton;

    List<RoleAssignmentListener> listeners = new ArrayList<>();

    public RoleTile(JTileView parent, Person person, Role role) {
        super(parent);
        this.person = person;
        this.role = role;
        this.setTransferHandler(new PersonTransferHandler());
        GridBagLayout layoutManager = new GridBagLayout();
        setLayout(layoutManager);

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

    public void updateDisplay() {
        if (person == null) {
            clearButton.setVisible(false);
        } else {
            clearButton.setVisible(true);
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
            person = null;
            updateDisplay();
        }
    }

    public void addRoleAssignmentListener(RoleAssignmentListener listener) {
        this.listeners.add(listener);
    }
}
