package action_cast.widgets.tiles;

import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by bmichaud on 8/24/2016.
 */
public class RoleTileBody extends JPanel {

    private final RoleTile parent;
    private static BufferedImage defaultProfileImage;

    JPanel roleCountPanel;
    JLabel profileImageDisplay;

    JLabel mainCount;
    JLabel supportCount;
    JLabel backgroundCount;

    JLabel name;

    public RoleTileBody(RoleTile tile) {
        if (defaultProfileImage == null) {
            try {
                defaultProfileImage = ImageIO.read(getClass().getClassLoader().getResource("images/profile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setLayout(new GridBagLayout());
        parent = tile;

        addProfileImage();
        addRoleTypeCountLabels();
        addNameLabel();
    }

    public void updateDisplay() {
        updateRoleCounts();
        if (parent.getAssignedPerson() == null) {
            name.setText("");
        } else {
            name.setText(parent.getAssignedPerson().getName());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, ((3*parent.getHeight())/4) - (parent.getInsets().bottom + parent.getInsets().top));
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, (2*parent.getHeight()) / 3);
    }

    private void updateRoleCounts() {
        if (parent.getAssignedPerson() == null) {
            mainCount.setVisible(false);
            supportCount.setVisible(false);
            backgroundCount.setVisible(false);
        } else {
            mainCount.setVisible(true);
            supportCount.setVisible(true);
            backgroundCount.setVisible(true);

            try {
                mainCount.setText("I:   " + Integer.toString(parent.getController().getRoleTypeCountFor(parent.getAssignedPerson(), RoleType.MAIN)));
                supportCount.setText("II:  " + Integer.toString(parent.getController().getRoleTypeCountFor(parent.getAssignedPerson(), RoleType.SUPPORT)));
                backgroundCount.setText("III: " + Integer.toString(parent.getController().getRoleTypeCountFor(parent.getAssignedPerson(), RoleType.BACKGROUND)));
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        }
    }

    private void addProfileImage() {
        GridBagConstraints profileImageConstraints = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.NORTHWEST,
                0,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        profileImageDisplay = new JLabel(new ImageIcon(defaultProfileImage));
        add(profileImageDisplay, profileImageConstraints);
    }

    private void addNameLabel() {
        GridBagConstraints nameLabelConstraints = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.SOUTHWEST,
                0,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        name = new JLabel();
        if (parent.getAssignedPerson() != null) {
            name.setText(parent.getAssignedPerson().getName());
        }
        add(name, nameLabelConstraints);
    }

    private void addRoleTypeCountLabels() {

        roleCountPanel = new JPanel();
        GridBagLayout mgr = new GridBagLayout();
        roleCountPanel.setLayout(mgr);
        roleCountPanel.setBorder(new LineBorder(Color.black, 2));

        GridBagConstraints constraints = new GridBagConstraints(0, 0, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.NORTHEAST,
                0,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints1 = new GridBagConstraints(0, 1, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.EAST,
                0,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        GridBagConstraints constraints2 = new GridBagConstraints(0, 2, this.getHeight()/3, this.getWidth()/3,
                1,
                1,
                GridBagConstraints.SOUTHEAST,
                0,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        mainCount = new JLabel();
        supportCount = new JLabel();
        backgroundCount = new JLabel();

        updateRoleCounts();

        add(mainCount, constraints);
        add(supportCount, constraints1);
        add(backgroundCount, constraints2);
    }
}
