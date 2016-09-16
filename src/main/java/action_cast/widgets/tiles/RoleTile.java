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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 12/14/2015.
 */
public class RoleTile extends Tile {

    private final Controller controller;
    Person person = null;
    Role role;

    RoleTileBody lowerPanel;

    private RoleTileHeader header;

    List<RoleAssignmentListener> listeners = new ArrayList<>();

    public RoleTile(JTileView parent, Controller controller, Person person, Role role) {
        super(parent);
//        GridBagConstraints headerConstraints = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
//                1,
//                1,
//                GridBagConstraints.NORTH,
//                GridBagConstraints.NONE,
//                new Insets(1, 1, 1, 1),
//                1,
//                1
//        );
//
        this.controller = controller;
        this.person = person;
        this.role = role;
        this.setTransferHandler(new PersonTransferHandler());
        //GridBagLayout layoutManager = new GridBagLayout();
        //setLayout(layoutManager);
        ((FlowLayout)this.getLayout()).setHgap(0);
        ((FlowLayout)this.getLayout()).setVgap(0);
        ((FlowLayout)this.getLayout()).setAlignment(FlowLayout.LEFT);
        header = new RoleTileHeader(this);
        lowerPanel = new RoleTileBody(this);
       // lowerPanel.setLayout(new GridBagLayout());
       // lowerPanel.setBorder(new LineBorder(Color.green, 2));
        //System.out.println("Width: " + JTileView.TILE_WIDTH);
       // lowerPanel.setMinimumSize(new Dimension(JTileView.TILE_WIDTH, getHeight()-getInsets().top - header.getHeight()));
        //lowerPanel.setPreferredSize(new Dimension(JTileView.TILE_WIDTH, getHeight()-getInsets().top - header.getHeight()));
        //lowerPanel.setPreferredSize(new Dimension(getWidth(), getHeight()-getInsets().top - header.getHeight()));
        //this.setBorder(new LineBorder(Color.MAGENTA, 2));
        this.add(header);//, headerConstraints);
        this.add(lowerPanel);

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
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

        header.updateDisplay();
        lowerPanel.updateDisplay();
    }

    public Controller getController() {
        return controller;
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return new PersonTransferHandler();
    }

//    protected void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        //addProfileImage(g);
//
//        //((Graphics2D) g).drawString("X", 50, 15);
//
//        if (person != null) {
//            ((Graphics2D) g).drawString(person.getName(), getInsets().left, getInsets().top + header.getHeight() + defaultProfileImage.getHeight() + g.getFontMetrics().getHeight());
//        }
//    }

    public void addRoleAssignmentListener(RoleAssignmentListener listener) {
        this.listeners.add(listener);
    }


}
