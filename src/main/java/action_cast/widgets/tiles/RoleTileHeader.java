package action_cast.widgets.tiles;

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

    public RoleTileHeader(RoleTile tile) {
        parent = tile;
        this.setBorder(new LineBorder(Color.black, 2));
        this.setLayout(new GridBagLayout());
        addClearButton();
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
        return new Dimension(parent.getWidth(), parent.getHeight()/3);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(parent.getWidth(), parent.getHeight()/4);
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
        if (parent.getAssignedPerson() == null) {
            clearButton.setVisible(false);
        }
        clearButton.addActionListener(this);
        this.add(clearButton, constraints);
    }
}
