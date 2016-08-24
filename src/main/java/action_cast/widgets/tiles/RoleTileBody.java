package action_cast.widgets.tiles;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by bmichaud on 8/24/2016.
 */
public class RoleTileBody extends JPanel {

    private final RoleTile parent;

    public RoleTileBody(RoleTile tile) {
        parent = tile;
        this.setBorder(new LineBorder(Color.red, 2));
        this.setLayout(new GridBagLayout());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, parent.getHeight() / 3);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(parent.getWidth() - parent.getInsets().right - parent.getInsets().left, parent.getHeight() / 4);
    }

}
