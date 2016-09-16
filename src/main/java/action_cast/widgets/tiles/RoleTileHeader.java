package action_cast.widgets.tiles;

import action_cast.widgets.custom.JTileView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bmichaud on 8/15/2016.
 */
public class RoleTileHeader extends JPanel implements ActionListener {

    private final RoleTile parent;
    private JButton clearButton;
    private JLabel roleTypeLabel;
    private JLabel roleNameLabel;
    private JPanel labelPanel;

    public RoleTileHeader(RoleTile tile) {
        parent = tile;
        //this.setBorder(new LineBorder(Color.blue, 2));
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints(0, 2, this.getHeight() / 3, this.getWidth() / 3,
                1,
                1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        labelPanel = new JPanel();
        add(labelPanel, constraints);

        addClearButton();
        addRoleTypeLabel();
        addRoleNameLabel();
    }

    public void updateDisplay() {
        if (parent.getAssignedPerson() == null) {
            clearButton.setVisible(false);
        } else {
            clearButton.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            parent.assignPerson(null);
            parent.updateDisplay();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, parent.getHeight() / 4);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, parent.getHeight() / 4);
    }

/*    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String roleTypeLabel = "";

        ((Graphics2D) g).drawString(roleTypeLabel, getInsets().left, JTileView.TILE_HEIGHT - getInsets().bottom);

    }*/

    private void addClearButton() {
        GridBagConstraints constraints = new GridBagConstraints(0, 2, this.getHeight() / 3, this.getWidth() / 3,
                1,
                1,
                GridBagConstraints.FIRST_LINE_END,
                GridBagConstraints.NONE,
                new Insets(1, 1, 1, 1),
                1,
                1
        );

        clearButton = new JButton("X");
        clearButton.setMargin(new Insets(1, 1, 1, 1));
        if (parent.getAssignedPerson() == null) {
            clearButton.setVisible(false);
        }
        clearButton.addActionListener(this);
        this.add(clearButton, constraints);
    }

    private void addRoleTypeLabel() {
        roleTypeLabel = new JLabel();
        switch (parent.getRole().getType()) {
            case MAIN:
                roleTypeLabel.setText("I");
                break;
            case SUPPORT:
                roleTypeLabel.setText("II");
                break;
            case BACKGROUND:
                roleTypeLabel.setText("III");
                break;
        }
        labelPanel.add(roleTypeLabel);//, constraints);
    }


    private void addRoleNameLabel() {
        roleNameLabel = new JLabel(parent.getRole().getName());

        labelPanel.add(roleNameLabel);//, constraints);
    }
}
