package action_cast.widgets.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by bmichaud on 10/29/2015.
 */
public abstract class Tile extends JComponent {

    protected void paintComponent(Graphics g) {
        Ellipse2D ellipse = new Ellipse2D.Float(0, 0, 40, 40);
        ((Graphics2D)g).draw(ellipse);
        //g.drawRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
        g.dispose();
    }

}
